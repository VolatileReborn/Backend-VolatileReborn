package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.reportDAO.*;
import com.example.BackendVolatile.dto.reportDTO.*;
import com.example.BackendVolatile.mapper.report.*;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.ReportService;
import com.example.BackendVolatile.util.JSONUtils;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.util.pythonUtil.ClusterReportVO;
import com.example.BackendVolatile.util.pythonUtil.PythonUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataDTO;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataUtil;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.reportVO.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    CooperationDefectPictureMapper cooperationDefectPictureMapper;

    @Resource
    ReportMapper reportMapper;

    @Resource
    TaskMapper taskMapper;

    @Resource
    ReportScoreMapper reportScoreMapper;

    @Resource
    CooperationReportMapper cooperationReportMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    RestTemplate restTemplate;

    @Resource
    PythonUtil pythonUtil;

    @Resource
    PrepareReportTrainingDataUtil prepareReportTrainingDataUtil;

    @Resource
    DefectPictureMapper defectPictureMapper;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AugmentedReport implements Serializable {
        private String defect_explanation;
        private String defect_reproduction_step;
        private String test_equipment_information;
        private List<ReqDefectPicture> defect_picture_list;
        private String report_name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ReqDefectPicture implements Serializable {
        private String img_name;
        private String img_url;
        private String img_id;

        public ReqDefectPicture(DefectPicture defectPicture) {
            this.img_name = defectPicture.getPicture_name();
            this.img_url = defectPicture.getPicture_url();
            this.img_id = String.valueOf(defectPicture.getPicture_id());
        }

        public ReqDefectPicture(CooperationDefectPicture defectPicture){
            this.img_name=defectPicture.getPicture_name();
            this.img_url = defectPicture.getPicture_url();
            this.img_id = String.valueOf(defectPicture.getPicture_id());
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AugmentationRequest {
        private Integer augmented_report_num;
        private ReqReport report;
        private String algorithm = null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class EvaluationRequest {
        private ReqReport report;
        private String algorithm = null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ReqReport {
        private Long report_id;
        private Long task_id;
        private String report_name;
        private String defect_explanation;
        private String defect_reproduction_step;
        private String test_equipment_information;
        private Boolean is_augmented;
        private List<ReqDefectPicture> defect_picture_list;

        public ReqReport(Report report, Boolean isAugmented, List<ReqDefectPicture> defect_picture_list) {
            this.report_id = report.getReport_id();
            this.task_id = report.getTask_id();
            this.report_name = report.getReport_name();
            this.defect_explanation = report.getDefect_explain();
            this.defect_reproduction_step = report.getDefect_reproduction_step();
            this.test_equipment_information = report.getTest_equipment_information();
            this.is_augmented = isAugmented;
            this.defect_picture_list=defect_picture_list;
        }
    }

    public AugmentedReportsVO getAugmentedReports(AugmentationRequestDTO augmentationRequestDTO,boolean isCoop) {
        AugmentedReportsVO res = new AugmentedReportsVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(), RoleConstant.ADMIN.getRole(), RoleConstant.EMPLOYEE.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
        if (valid != BooleanValue.TRUE) {
            res.setResponse(resultVO);
            return res;
        }

        Report report;
        List<ReqDefectPicture> defectPictureList;
        if(!isCoop){
            report = reportMapper.get_by_report_id(augmentationRequestDTO.getReportId());
            defectPictureList=defectPictureMapper.get_all_by_report_id(augmentationRequestDTO.getReportId())
                    .stream().map(ReqDefectPicture::new).collect(Collectors.toList());
        } else {
            report = new Report(cooperationReportMapper.get_by_report_id(augmentationRequestDTO.getReportId()));
            defectPictureList = cooperationDefectPictureMapper
                    .get_all_by_report_id(augmentationRequestDTO.getReportId())
                    .stream().map(ReqDefectPicture::new).collect(Collectors.toList());
        }

        if(report.getUser_id()==null){
            res.setResponse(new ResultVO(ResponseConstant.REPORT_CANNOT_BE_AUGMENTED));
        }

        AugmentationRequest augmentationRequest = new AugmentationRequest(
                augmentationRequestDTO.getAugmentedReportNum(),
                new ReqReport(report, false, defectPictureList),
                null
        );

        try {
            System.err.println(augmentationRequest);
            String augmentedReportsJson = restTemplate.postForObject(
                    "http://" + PythonServerConstant.IP + ":" + PythonServerConstant.PORT + "/getAugmentedReports"
                    , augmentationRequest, String.class);

            List<AugmentedReport> augmentedReports = JSONUtils.jsonToList(augmentedReportsJson, AugmentedReport.class);

            for (AugmentedReport augmentedReport : Objects.requireNonNull(augmentedReports)) {
                reportMapper.insert(new Report(augmentedReport, report.getTask_id()));
            }
            res.setResponse(new ResultVO(ResponseConstant.REPORT_AUGMENTATION_SUCCEED));
            res.setAugmentedReports(augmentedReports);
        } catch (Exception e) {
            res.setResponse(new ResultVO(ResponseConstant.REPORT_AUGMENTATION_SYSTEM_ERR));
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public ReportEvaluationVO getQualityEvaluation(GettingEvaluationDTO gettingEvaluationDTO, boolean isCoop) {
        ReportEvaluationVO res = new ReportEvaluationVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(), RoleConstant.ADMIN.getRole(), RoleConstant.EMPLOYEE.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
        if (valid != BooleanValue.TRUE) {
            res.setResponse(resultVO);
            return res;
        }

        Report report;
        List<ReqDefectPicture> defectPictureList;
        if(!isCoop){
            report = reportMapper.get_by_report_id(gettingEvaluationDTO.getReportId());
            defectPictureList=defectPictureMapper.get_all_by_report_id(gettingEvaluationDTO.getReportId())
                    .stream().map(ReqDefectPicture::new).collect(Collectors.toList());
        } else {
            report = new Report(cooperationReportMapper.get_by_report_id(gettingEvaluationDTO.getReportId()));
            defectPictureList = cooperationDefectPictureMapper
                    .get_all_by_report_id(gettingEvaluationDTO.getReportId())
                    .stream().map(ReqDefectPicture::new).collect(Collectors.toList());
        }

        EvaluationRequest evaluationRequest = new EvaluationRequest(
                new ReqReport(report, report.getUser_id() == null, defectPictureList),
                null
        );
        try {
            String url = "http://" + PythonServerConstant.IP + ":" + PythonServerConstant.PORT + "/getReportEvaluation";

            System.err.println(url);
            System.err.println(evaluationRequest);
            String evaluationJson = restTemplate.postForObject(url, evaluationRequest, String.class);

            ReportEvaluation reportEvaluation = JSONUtils.jsonToPojo(evaluationJson, ReportEvaluation.class);

            res.setEvaluationValue(Objects.requireNonNull(reportEvaluation).getReport_evaluation_value());
            res.setEvaluated(reportEvaluation.getIs_evaluated());
            if (reportEvaluation.is_evaluated) {
                res.setResponse(new ResultVO(ResponseConstant.REPORT_EVALUATION_SUCCEED));
            } else {
                res.setResponse(new ResultVO(ResponseConstant.REPORT_EVALUATION_FAILED));
            }
        } catch (Exception e) {
            res.setResponse(new ResultVO(ResponseConstant.REPORT_EVALUATION_SYSTEM_ERR));
            e.printStackTrace();
        }

        return res;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ReportEvaluation {
        private Double report_evaluation_value;
        private Boolean is_evaluated;
    }


    /**
     * 调用者：所有人
     * 业务逻辑：发包方在任务界面查看提交的报告列表, 后端给前端返回reportid，userid，reportname的列表
     *
     * @param checkReportsDTO
     * @return
     */
    @Override
    public CheckReportsVO checkReports(CheckReportsDTO checkReportsDTO) {
        CheckReportsVO checkReportsVO = new CheckReportsVO();
        Long taskId = checkReportsDTO.getTaskId();
        List<Long> ids = new ArrayList<>();
//        try{
//            String url = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/getRecommendedReports";
//            System.out.println(url);
//            String s = restTemplate.postForObject(url, pythonUtil.getRecommendedReportsDTO(1L,taskId,10,"DeepRecommendation"), String.class);
//            ids = JSONUtils.jsonToList(s,Long.class);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        List<Report> reportList = reportMapper.get_all_by_task_id(taskId);
        checkReportsVO.setReportList(reportList);
//        checkReportsVO.setReportList2(reportList,ids);
        checkReportsVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return checkReportsVO;
    }

    /**
     * 调用者：All
     * 业务逻辑：给参与的任务的报告评分，分值在1-5
     *
     * @param scoreReportDTO
     * @return
     */
    @Override
    public ScoreReportVO scoreReport(ScoreReportDTO scoreReportDTO) {
        ScoreReportVO scoreReportVO = new ScoreReportVO();
        int[] roleList = new int[]
                {RoleConstant.EMPLOYEE.getRole(), RoleConstant.EMPLOYER.getRole(), RoleConstant.ADMIN.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long) tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if (valid != BooleanValue.TRUE) {
            scoreReportVO.setResponse(resultVO);
            return scoreReportVO;
        }
        Long reportId = scoreReportDTO.getReportId();

        //返回值notScoreTheReport没有用处，但是在调用时会检验reportId存在，如果不存在就会报错报告不存在
        Integer reportExist = reportMapper.assert_report_id_exist(reportId);

        //返回值notScoreTheReport没有用处，但是在调用时会检验没有提交过评分，如果提交过就会报错已经交过了
        Integer notScoreTheReport =
                reportScoreMapper.assert_have_not_submitted_report_score_by_user_id_and_report_id(userId, reportId);

        ReportScore reportScore = new ReportScore(scoreReportDTO);
        reportScore.setUser_id(userId);
        reportScoreMapper.insert(reportScore);
        scoreReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SCORE_THE_REPORT_SUCCEEDED));
        return scoreReportVO;
    }

    /**
     * 调用者：employer/admin
     * 业务逻辑：根据reportId获得其协作报告的信息列表
     *
     * @param cooperationReportListDTO
     * @return
     */
    @Override
    public CooperationReportListVO cooperationReportList(CooperationReportListDTO cooperationReportListDTO) {

        CooperationReportListVO cooperationReportListVO = new CooperationReportListVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(), RoleConstant.EMPLOYEE.getRole(), RoleConstant.ADMIN.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if (valid != BooleanValue.TRUE) {
            cooperationReportListVO.setResponse(resultVO);
            return cooperationReportListVO;
        }

        Long parentReportId = cooperationReportListDTO.getReportId();
        //判断是否存在
        Integer reportIdExist = reportMapper.assert_report_id_exist(parentReportId);
        List<CooperationReport> cooperationReportList = cooperationReportMapper.get_by_parent_report_id(parentReportId);
        cooperationReportListVO.setCooperationReportList(cooperationReportList);
        cooperationReportListVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return cooperationReportListVO;
    }

    /**
     * 调用者：all
     * 业务逻辑：根据userId和reportId获取一个用户对某个测试报告的评论和评分
     *
     * @param showReportScoreDTO
     * @return
     */
    @Override
    public ShowReportScoreVO showReportScore(ShowReportScoreDTO showReportScoreDTO) {
        ShowReportScoreVO showReportScoreVO = new ShowReportScoreVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(), RoleConstant.ADMIN.getRole(), RoleConstant.EMPLOYEE.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long) tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if (valid != BooleanValue.TRUE) {
            showReportScoreVO.setResponse(resultVO);
            return showReportScoreVO;
        }

        ReportScore reportScore = reportScoreMapper.get_by_user_id_and_report_id(userId, showReportScoreDTO.getReportId());
        showReportScoreVO.setPartShowReportScoreVO(reportScore);
        showReportScoreVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return showReportScoreVO;
    }

    /**
     * 提供信息展示可视化图
     */
    public GetSimilarityGraphVO getSimilarityGraph(GetSimilarityGraphDTO getSimilarityGraphDTO) {
        List<Report> reportList = reportMapper.get_all_by_task_id(getSimilarityGraphDTO.getTaskId());
        int parents = reportList.size();
        List<TempNode> tempNodeList = new ArrayList<>();
        for (int i = 0; i < reportList.size(); i++) {
            tempNodeList.add(new TempNode(reportList.get(i), (long) i));
        }
        for (int i = 0; i < tempNodeList.size(); i++) {
            for (int j = 0; j < tempNodeList.size(); j++) {
                if (tempNodeList.get(i).getSimilarId().equals(tempNodeList.get(j).getReportId())) {
                    tempNodeList.get(i).setTarget(tempNodeList.get(j).getId());
                    break;
                }
            }
        }
        Long index = (long) parents;
        for (int i = 0; i < parents; i++) {
            Long parentId = tempNodeList.get(i).getId();
            String parentName = tempNodeList.get(i).getValue();
            Long parentReportId = tempNodeList.get(i).getReportId();
            List<CooperationReport> cooperationReportList = cooperationReportMapper.get_by_parent_report_id(parentReportId);
            for (int j = 0; j < cooperationReportList.size(); j++) {
                tempNodeList.add(new TempNode(cooperationReportList.get(j), parentId, parentName, index));
                index++;
            }
        }
        GetSimilarityGraphVO getSimilarityGraphVO = new GetSimilarityGraphVO(tempNodeList, parents);
        getSimilarityGraphVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getSimilarityGraphVO;
    }

    /**
     * 节点数数据
     */
    public GetCooperationTreeVO getCooperationTree(GetCooperationTreeDTO getCooperationTreeDTO) {
        Long index = 0L;
        GetCooperationTreeVO getCooperationTreeVO = new GetCooperationTreeVO();
        Long taskId = getCooperationTreeDTO.getTaskId();
        String taskName = taskMapper.get_name_by_task_id(taskId);
        getCooperationTreeVO.setName(index);
        index++;
        getCooperationTreeVO.setValue(taskName);
        List<Report> reportList = reportMapper.get_all_by_task_id(taskId);
        List<TreeNode> children = new ArrayList<>();
        for (int i = 0; i < reportList.size(); i++) {
            TreeNode tempNode = new TreeNode();
            tempNode.setName(index);
            index++;
            tempNode.setValue(reportList.get(i).getReport_name());

            List<CooperationReport> cooperationReportList =
                    cooperationReportMapper.get_by_parent_report_id(reportList.get(i).getReport_id());
            tempNode.setChildren(cooperationReportList, index);
            index += cooperationReportList.size();
            children.add(tempNode);
        }
        getCooperationTreeVO.setChildren(children);
        return getCooperationTreeVO;
    }

    @Override
    public GetClusterScatterVO getClusterScatter(GetClusterScatterDTO getClusterScatterDTO) {
        GetClusterScatterVO getClusterScatterVO = new GetClusterScatterVO();
        Long taskId = getClusterScatterDTO.getTaskId();
        List<Info> clusterList = new ArrayList<>();//返回的结果
        List<ClusterReportVO> clusterReportVOList = new ArrayList<>();//算法返回值
        String s = "";
        try {
            String url = "http://" + PythonServerConstant.IP + ":" +
                    PythonServerConstant.PORT + "/clusterReports";
            s = restTemplate.postForObject(url, pythonUtil.getClusterReportsDTO(5L, taskId), String.class);
            System.out.println("python服务器返回数据：" + s);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求报错，未准备数据，开始准备");
            try {
                String url = "http://" + PythonServerConstant.IP + ":" + PythonServerConstant.PORT + "/prepareReportTrainingData";
                System.out.println(url);
                PrepareReportTrainingDataDTO pDTO = prepareReportTrainingDataUtil.getPrepareReportTrainingDataDTO(taskId, "DeepPrior");
                s = restTemplate.postForObject(url, pDTO, String.class);
                String url2 = "http://" + PythonServerConstant.IP + ":" +
                        PythonServerConstant.PORT + "/clusterReports";
                s = restTemplate.postForObject(url2, pythonUtil.getClusterReportsDTO(5L, taskId), String.class);
                System.out.println("python服务器返回数据：" + s);
            } catch (Exception e2) {
                e2.printStackTrace();
            }


        }
        clusterReportVOList = pythonUtil.parseCluster(s);
        ArrayList<Long> indexList = new ArrayList<>();
        //假装到这里已经得到了列表
        for (int i = 0; i < clusterReportVOList.size(); i++) {
            ClusterReportVO clusterReportVO = clusterReportVOList.get(i);
            Info info = new Info();
            info.setReportId(clusterReportVO.getReport_id());
            if (!indexList.contains(clusterReportVO.getCluster_id())) {
                indexList.add(clusterReportVO.getCluster_id());
            }
            info.setIndex(indexList.indexOf(clusterReportVO.getCluster_id()));
            info.setX(clusterReportVO.getCoordinate().get(0));
            info.setY(clusterReportVO.getCoordinate().get(1));
            clusterList.add(info);
        }
        getClusterScatterVO.setData(clusterList);
        getClusterScatterVO.setClusterCount(indexList.size());
        getClusterScatterVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getClusterScatterVO;
    }
}


