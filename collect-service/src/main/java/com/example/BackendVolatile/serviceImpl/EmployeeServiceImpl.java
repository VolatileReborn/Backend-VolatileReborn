package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.reportDAO.*;
import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dto.employeeDTO.*;
import com.example.BackendVolatile.dto.reportDTO.AugmentationRequestDTO;
import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.mapper.report.*;
import com.example.BackendVolatile.mapper.task.ExecutableFileMapper;
import com.example.BackendVolatile.mapper.task.RequirementDescriptionFileMapper;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.EmployeeService;
import com.example.BackendVolatile.service.ReportService;
import com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil.GetSimilarReportsDTO;
import com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil.GetSimilarReportsUtil;
import com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil.GetSimilarReportsVO;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.ScoreUtil;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.util.pythonUtil.PythonUtil;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataDTO;
import com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData.PrepareReportTrainingDataUtil;
import com.example.BackendVolatile.vo.employeeVO.*;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.reportVO.AugmentedReportsVO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    ReportService reportService;

    @Resource
    TaskMapper taskMapper;

    @Resource
    ExecutableFileMapper executableFileMapper;

    @Resource
    RequirementDescriptionFileMapper requirementDescriptionFileMapper;

    @Resource
    ReportMapper reportMapper;

    @Resource
    AlgorithmMapper algorithmMapper;

    @Resource
    DefectPictureMapper defectPictureMapper;

    @Resource
    CooperationReportMapper cooperationReportMapper;

    @Resource
    CooperationDefectPictureMapper cooperationDefectPictureMapper;

    @Resource
    SelectTaskMapper selectTaskMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    ReportScoreMapper reportScoreMapper;

    @Resource
    CooperationMapper cooperationMapper;

    @Resource
    ScoreUtil scoreUtil;

    @Resource
    GetSimilarReportsUtil getSimilarReportsUtil;

    @Resource
    RestTemplate restTemplate;

    @Resource
    PythonUtil pythonUtil;

    @Resource
    PrepareReportTrainingDataUtil prepareReportTrainingDataUtil;

    /**
     * ????????????employee
     * ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @return
     */
    @Override
    public BrowserUndertakingTasksVO browserUndertakingTasks(){
        BrowserUndertakingTasksVO browserUndertakingTasksVO = new BrowserUndertakingTasksVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserUndertakingTasksVO.setResponse(resultVO);
            return browserUndertakingTasksVO;
        }

        List<Long> taskIdList = selectTaskMapper.select_task_id_by_userid(userId);
        List<Task> taskList = new ArrayList<>();
        for(int i = 0;i < taskIdList.size(); i++){
            taskList.add(taskMapper.get_by_task_id(taskIdList.get(i)));
        }
        browserUndertakingTasksVO.setUndertakingTaskList(taskList);
        browserUndertakingTasksVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return browserUndertakingTasksVO;
    }

    /**
     * ????????????employee
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @return
     */
    @Override
    public BrowserFinishedTasksVO browserFinishedTasks(){
        BrowserFinishedTasksVO browserFinishedTasksVO = new BrowserFinishedTasksVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserFinishedTasksVO.setResponse(resultVO);
            return browserFinishedTasksVO;
        }

        List<Long> taskIdList = selectTaskMapper.select_task_id_by_userid(userId);
        List<Task> taskList = new ArrayList<>();
        for(int i = 0;i < taskIdList.size(); i++){
            taskList.add(taskMapper.get_by_task_id(taskIdList.get(i)));
        }
        browserFinishedTasksVO.setFinishedTaskList(taskList);
        browserFinishedTasksVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return browserFinishedTasksVO;
    }

    /**
     * ????????????employee
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????id????????????
     * @param browserTaskDetailDTO
     * @return
     */
    @Override
    public BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO){
        BrowserTaskDetailVO browserTaskDetailVO = new BrowserTaskDetailVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserTaskDetailVO.setResponse(resultVO);
            return browserTaskDetailVO;
        }

        Long taskId = browserTaskDetailDTO.getTaskId();
        //isSelected????????????????????????????????????????????????????????????????????????????????????
        Integer isSelected = selectTaskMapper.assert_is_selected(taskId,userId);
        Task task = taskMapper.get_by_task_id(taskId);
        browserTaskDetailVO.setPartBrowserTaskDetailVO(task);

        Integer isSubmitted = reportMapper.report_id_in_it_by_user_id_and_task_id(userId,taskId);
        browserTaskDetailVO.setIsSubmitted(isSubmitted);
        if(isSubmitted == BooleanValue.TRUE){
            List<Report> reportList = reportMapper.get_all_by_user_id_and_task_id(userId,taskId);
            browserTaskDetailVO.setReportInfo(reportList);
        }

        List<ExecutableFile> executableFileList = executableFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setExecutableFileList(executableFileList);

        List<RequirementDescriptionFile> requirementDescriptionFileList =
                requirementDescriptionFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setRequirementDescriptionFileList(requirementDescriptionFileList);

        browserTaskDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return browserTaskDetailVO;
    }


    /**
     * ????????????employee
     * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @param uploadTestReportDTO
     * @return
     */
    @Override
    public UploadTestReportVO uploadTestReport(UploadTestReportDTO uploadTestReportDTO){
        UploadTestReportVO uploadTestReportVO = new UploadTestReportVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            uploadTestReportVO.setResponse(resultVO);
            return uploadTestReportVO;
        }
        Long taskId = uploadTestReportDTO.getTaskId();
        //isSelected????????????????????????????????????????????????????????????????????????????????????
        Integer isSelected = selectTaskMapper.assert_is_selected(taskId,userId);
        //isNotSubmitted?????????????????????????????????????????????????????????????????????????????????
//        Integer isNotSubmitted = reportMapper.assert_report_not_submitted(userId,taskId);

        Report report = new Report(uploadTestReportDTO,userId);

//        report.setSimilarity(0);// ???????????????,??????????????????????????????????????????
//        report.setSimilar_report_id(1024L);

        Long time = System.currentTimeMillis();
        Integer utilToday = (int) (time / (24 * 60 *60 * 1000) - 19100);
        report.setReport_state(ReportStateConstant.TO_BE_REVIEWED.getCode());
        System.err.println(report);
        reportMapper.insert(report);

        Long reportId = reportMapper.max_id();
        List<File> files = uploadTestReportDTO.getDefectPictureList();
        for(int i = 0;i < files.size(); i++){
            DefectPicture defectPicture = new DefectPicture(files.get(i));
            defectPicture.setReport_id(reportId);
            defectPictureMapper.insert(defectPicture);
        }


        AugmentedReportsVO augmentedReportsVO=reportService.getAugmentedReports(new AugmentationRequestDTO(
                reportId,1
        ),false);
        if(augmentedReportsVO.getResponse().getCode()%100!=0){
            System.err.println("?????????????????????"+augmentedReportsVO);
        }



//        System.out.println("??????????????????");
//
        try {
            String url = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/prepareReportTrainingData";
            System.out.println(url);
            PrepareReportTrainingDataDTO pDTO = prepareReportTrainingDataUtil.getPrepareReportTrainingDataDTO(taskId,"DeepPrior");
            System.out.println("????????????");
            Long time1 = System.currentTimeMillis();
            String s = restTemplate.postForObject(url, pDTO, String.class);
            Long time2 = System.currentTimeMillis();
            System.out.println("????????????, ??????" + (time2 - time1) / 1000 + "??????");

            String url2 = "http://"+PythonServerConstant.IP+ ":"+
                    PythonServerConstant.PORT+"/getSimilarReports";

            String s2 = restTemplate.postForObject(url2, pythonUtil.getSimilarReportsDTO(reportId,taskId,1,"DeepSimilarity"), String.class);
            System.out.println("s2" + s2);

            GetSimilarReportsVO getSimilarReportsVO = getSimilarReportsUtil.parseResult(s2);
            if (getSimilarReportsVO.getReport_id() == -1) {
                System.out.println("-1" + getSimilarReportsVO.getReport_id());
                reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
                reportMapper.update_similarity_by_report_id(reportId, 0);
            } else {
                System.out.println("zc" + getSimilarReportsVO.getReport_id());
                Integer similar = (int) (getSimilarReportsVO.getSimilarity() * 100);
                reportMapper.update_similar_report_id_by_report_id(reportId, getSimilarReportsVO.getReport_id());
                reportMapper.update_similarity_by_report_id(reportId, similar);
                if (similar > 20) {//?????????????????????????????????
                    List<Long> userIdList = selectTaskMapper.get_user_id_by_task_id(taskId);
                    if (userIdList.size() < 10) {
                        for (int i = 0; i < userIdList.size(); i++) {
                            if (userIdList.get(i).equals(userId)) {
                                continue;
                            } else {
                                cooperationMapper.insert(reportId, userIdList.get(i), CooperationReportStateConstant.COOPERATING.getCode());
                            }
                        }
                    } else {
                        int p = userIdList.size() / 10;
                        for (int i = 0; i < 10; i++) {
                            cooperationMapper.insert(reportId, userIdList.get(i * p), CooperationReportStateConstant.COOPERATING.getCode());
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("????????????????????????????????????");
            e.printStackTrace();
            reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
            reportMapper.update_similarity_by_report_id(reportId, 0);
        }
        uploadTestReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_UPLOAD_REPORT_SUCCEEDED));


        return uploadTestReportVO;

    }

    /**
     * ????????????employee
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @param reportDetailDTO
     * @return
     */
    @Override
    public ReportDetailVO reportDetail(ReportDetailDTO reportDetailDTO){
        ReportDetailVO reportDetailVO = new ReportDetailVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            reportDetailVO.setResponse(resultVO);
            return reportDetailVO;
        }
        Long taskId = reportDetailDTO.getTaskId();
        Long reportId = reportDetailDTO.getReportId();
        System.out.println("taskId:" + taskId);
        System.out.println("reportId:" + reportId);
        //isSelected????????????????????????????????????????????????????????????????????????????????????
        Integer isSelected = selectTaskMapper.assert_is_selected(taskId,userId);
        System.out.println(isSelected);

        Report report = reportMapper.get_by_report_id(reportId);
        reportDetailVO.setPartReportDetailVO(report);
        List<DefectPicture> defectPictureList = defectPictureMapper.get_all_by_report_id(reportId);
        System.out.println(defectPictureList.size());
        reportDetailVO.setDefectPictureList(defectPictureList);

        Integer scoreInIt = reportScoreMapper.score_in_it_by_user_id_and_report_id(userId,reportId);
        reportDetailVO.setIsScored(scoreInIt);

        if(scoreInIt == BooleanValue.TRUE){
            ReportScore reportScore = reportScoreMapper.get_by_user_id_and_report_id(userId, reportId);
            reportDetailVO.setScoreInfo(reportScore);
        }

        List<Integer> scoreTemp = reportScoreMapper.get_score_by_report_id(reportId);
        System.out.println("scoreTemp" + scoreTemp.size());
        Integer totalScore = scoreUtil.calTotalScore(scoreTemp);
        System.out.println("totalScore" + totalScore);
        reportDetailVO.setTotalScore(totalScore);
        //??????
        Integer isCooperated = cooperationMapper.is_cooperated(reportId,userId);
        System.out.println("isCooperated" + isCooperated);
        reportDetailVO.setIsCooperated(isCooperated);
        if(isCooperated == BooleanValue.TRUE){
            Integer cooperationReportIsSubmitted = cooperationMapper.get_cooperation_report_state(reportId,userId);
            System.out.println("cooperationReportIsSubmitted" + cooperationReportIsSubmitted);
            reportDetailVO.setIsSubmitted(cooperationReportIsSubmitted);
            if(cooperationReportIsSubmitted == BooleanValue.TRUE){
                Long cooperationReportId = cooperationReportMapper.get_report_id_by_user_id_and_parent_report_id(userId,reportId);
                reportDetailVO.setCooperationReportId(cooperationReportId);
            }
        }
        else{
            reportDetailVO.setIsSubmitted(BooleanValue.FALSE);
        }

        //????????????
        Integer reportState = report.getReport_state();
        Long time = System.currentTimeMillis();
        Integer today = (int) (time / (24 * 60 *60 * 1000) - 19100);
        Integer changeableTime = algorithmMapper.get_day().get(0);
        System.out.println("reportState:" + reportState + ", changeableTime: " + changeableTime + "today:" + today);
        if(reportState + changeableTime < today){
            reportDetailVO.setIsChangeable(1);
        }
        else{
            reportDetailVO.setIsChangeable(0);
        }
        reportDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return reportDetailVO;
    }

    /**
     * ????????????employee
     * ?????????????????????????????????????????????????????????????????????
     * @param uploadCooperationReportDTO
     * @return
     */
    @Override
    public UploadCooperationReportVO uploadCooperationReport(UploadCooperationReportDTO uploadCooperationReportDTO){
        System.out.println("service------------------");
        System.out.println(uploadCooperationReportDTO == null);
        System.out.println(uploadCooperationReportDTO.getReportName()==null);
        System.out.println(uploadCooperationReportDTO.getDefectReproductionStep());
        UploadCooperationReportVO uploadCooperationReportVO = new UploadCooperationReportVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            uploadCooperationReportVO.setResponse(resultVO);
            return uploadCooperationReportVO;
        }
        Long parentReportId = uploadCooperationReportDTO.getParentReportId();
        //isCooperated??????????????????????????????????????????????????????????????????????????????????????????
        Integer isCooperated = cooperationMapper.assert_is_cooperated(parentReportId,userId);

        //??????????????????????????????????????????????????????????????????????????????????????????
        Integer cooperationReportNotExist = cooperationMapper.assert_cooperation_report_not_submitted(parentReportId,userId);

        CooperationReport cooperationReport = new CooperationReport(uploadCooperationReportDTO, userId);
        cooperationReportMapper.insert(cooperationReport);
        cooperationMapper.update_report_state_by_user_id_and_report_id(
                CooperationReportStateConstant.COOPERATED.getCode(),parentReportId,userId);
        List<File> files = uploadCooperationReportDTO.getDefectPictureList();
        Long cooperationReportId = cooperationReportMapper.max_id();
        for(int i = 0; i < files.size(); i++){
            CooperationDefectPicture cooperationDefectPicture = new CooperationDefectPicture(files.get(i));
            cooperationDefectPicture.setReport_id(cooperationReportId);
            cooperationDefectPictureMapper.insert(cooperationDefectPicture);
        }

        uploadCooperationReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_UPLOAD_REPORT_SUCCEEDED));

        AugmentedReportsVO augmentedReportsVO=reportService.getAugmentedReports(new AugmentationRequestDTO(
                cooperationReportId,1
        ),false);
        if(augmentedReportsVO.getResponse().getCode()%100!=0){
            System.err.println("?????????????????????"+augmentedReportsVO);
        }

        return uploadCooperationReportVO;
    }



    /**
     * ????????????employee
     * ??????????????????????????????????????????????????????????????????
     * @param cooperationReportDetailDTO
     * @return
     */
    @Override
    public CooperationReportDetailVO cooperationReportDetail(CooperationReportDetailDTO cooperationReportDetailDTO){

        CooperationReportDetailVO cooperationReportDetailVO = new CooperationReportDetailVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperationReportDetailVO.setResponse(resultVO);
            return cooperationReportDetailVO;
        }
        Long cooperationReportId = cooperationReportDetailDTO.getCooperationReportId();
//        //isSubmitted????????????????????????????????????????????????????????????????????????????????????
//        Integer isSubmitted = cooperationReportMapper.assert_report_is_submitted(userId,cooperationReportId);

        CooperationReport cooperationReport = cooperationReportMapper.get_by_report_id(cooperationReportId);
        cooperationReportDetailVO.setPartCooperationReportDetailVO(cooperationReport);

        List<CooperationDefectPicture> defectPictureList = cooperationDefectPictureMapper.get_all_by_report_id(cooperationReportId);
        cooperationReportDetailVO.setDefectPictureList(defectPictureList);
        cooperationReportDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return cooperationReportDetailVO;
    }

    /**
     * ???????????????????????????????????????????????????
     * @param cooperateReportDTO
     * @return
     */
    @Override
    public CooperateReportVO cooperateReport(CooperateReportDTO cooperateReportDTO){
        CooperateReportVO cooperateReportVO = new CooperateReportVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperateReportVO.setResponse(resultVO);
            return cooperateReportVO;
        }
        Long reportId = cooperateReportDTO.getReportId();

        //isNotSelected?????????????????????????????????????????????????????????????????????????????????
        Integer isNotCooperated = cooperationMapper.assert_is_not_cooperated(reportId,userId);
        System.out.println("isNotCooperated" + isNotCooperated);

        Long taskId = reportMapper.get_task_id_by_report_id(reportId);
        //?????????????????????
        Integer isSelected = selectTaskMapper.is_selected(taskId,userId);
        System.out.println("isSelected" +isSelected);

        cooperationMapper.insert(reportId,userId, CooperationReportStateConstant.COOPERATING.getCode());

        cooperateReportVO.setResponse(new ResultVO(ResponseConstant.REQUEST_ACCEPT_TASK_SUCCEEDED));
        return cooperateReportVO;
    }

    /**
     * ??????????????????????????????
     * @return
     */
    @Override
    public CooperatingListVO cooperatingList(){
        CooperatingListVO cooperatingListVO = new CooperatingListVO();
        //????????????
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperatingListVO.setResponse(resultVO);
            return cooperatingListVO;
        }

        //??????reportId??????
        List<Long> reportIdList = cooperationMapper.select_report_id_by_user_id_and_report_state(
                userId, CooperationReportStateConstant.COOPERATING.getCode());
        List<Integer> scoreTemp;
        List<Integer> totalScoreList = new ArrayList<>();
        List<Report> reportList = new ArrayList<>();
        for( int i = 0; i< reportIdList.size(); i++){
            //??????????????????
            reportList.add(reportMapper.get_by_report_id(reportIdList.get(i)));
            //????????????
            scoreTemp = reportScoreMapper.get_score_by_report_id(reportIdList.get(i));
            totalScoreList.add(scoreUtil.calTotalScore(scoreTemp));

        }
        cooperatingListVO.setReportList(reportList,totalScoreList);
        cooperatingListVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));

        return cooperatingListVO;
    }


    /**
     * ????????????employee
     * ????????????????????????????????????????????????
     * @return
     */
    @Override
    public CooperatedListVO cooperatedList(){
        CooperatedListVO cooperatedListVO = new CooperatedListVO();
        //????????????
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperatedListVO.setResponse(resultVO);
            return cooperatedListVO;
        }

        //??????reportId??????
        List<Long> reportIdList = cooperationMapper.select_report_id_by_user_id_and_report_state(
                userId, CooperationReportStateConstant.COOPERATED.getCode());
        List<Integer> scoreTemp;
        List<Integer> totalScoreList = new ArrayList<>();
        List<Report> reportList = new ArrayList<>();
        for( int i = 0; i< reportIdList.size(); i++){
            //??????????????????
            reportList.add(reportMapper.get_by_report_id(reportIdList.get(i)));
            //????????????
            scoreTemp = reportScoreMapper.get_score_by_report_id(reportIdList.get(i));
            totalScoreList.add(scoreUtil.calTotalScore(scoreTemp));

        }
        cooperatedListVO.setReportList(reportList,totalScoreList);
        cooperatedListVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));

        return cooperatedListVO;
    }

    @Override
    public ChangeReportVO changeReport(ChangeReportDTO changeReportDTO){
        System.out.println("changeReportDTO:" + changeReportDTO.toString());
        System.out.println("reportId" + changeReportDTO.getReportId());
        System.out.println("taksId" + changeReportDTO.getTaskId());
        ChangeReportVO changeReportVO = new ChangeReportVO();
        //????????????
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());
        Long reportId = changeReportDTO.getReportId();
        Long taskId = changeReportDTO.getTaskId();

        if(valid != BooleanValue.TRUE){
            changeReportVO.setResponse(resultVO);
            return changeReportVO;
        }
        Long time = System.currentTimeMillis();
        Integer utilToday = (int) (time / (24 * 60 *60 * 1000) - 19100);
        System.out.println("???"+ reportId);
        Integer reportState = reportMapper.get_report_state_by_report_id(reportId);
        System.out.println("???1");
        Integer changeableTime = algorithmMapper.get_day().get(0);
        if(reportState + changeableTime <= utilToday){
            changeReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_CANNOT_MODIFY_REPORT));
            return changeReportVO;
        }
        Report report = new Report();
        report.setReport_name(changeReportDTO.getReportName());
        report.setDefect_reproduction_step(changeReportDTO.getDefectReproductionStep());
        report.setTest_equipment_information(changeReportDTO.getTestEquipmentInformation());
        report.setDefect_explain(changeReportDTO.getDefectExplain());

        report.setReport_state(utilToday);
        reportMapper.update_report_by_report_id(report,reportId);

        defectPictureMapper.delete_all_by_report_id(reportId);
        List<File> files = changeReportDTO.getDefectPictureList();
        for(int i = 0;i < files.size(); i++){
            DefectPicture defectPicture = new DefectPicture(files.get(i));
            defectPicture.setReport_id(reportId);
            defectPictureMapper.insert(defectPicture);
        }

        //???????????????
        System.out.println("????????????");
        try {
            String url = "http://" + PythonServerConstant.IP + ":" +
                    PythonServerConstant.PORT + "/getSimilarReports";

            String s = restTemplate.postForObject(url, pythonUtil.getSimilarReportsDTO(reportId, taskId, 1, "DeepSimilarity"), String.class);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("?????????????????????????????????????????????");
            try{
                String url = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/prepareReportTrainingData";
                System.out.println(url);
                PrepareReportTrainingDataDTO pDTO = prepareReportTrainingDataUtil.getPrepareReportTrainingDataDTO(taskId,"DeepPrior");
                String s = restTemplate.postForObject(url, pDTO, String.class);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        try {

            String url = "http://"+PythonServerConstant.IP+ ":"+
                    PythonServerConstant.PORT+"/getSimilarReports";

            String s = restTemplate.postForObject(url, pythonUtil.getSimilarReportsDTO(reportId,taskId,1,"DeepSimilarity"), String.class);
            System.out.println("s" + s);

            GetSimilarReportsVO getSimilarReportsVO = getSimilarReportsUtil.parseResult(s);
            if (getSimilarReportsVO.getReport_id() == -1) {
                System.out.println("-1" + getSimilarReportsVO.getReport_id());
                reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
                reportMapper.update_similarity_by_report_id(reportId, 0);
            } else {
                System.out.println("zc" + getSimilarReportsVO.getReport_id());
                Integer similar = (int) (getSimilarReportsVO.getSimilarity() * 100);
                reportMapper.update_similar_report_id_by_report_id(reportId, getSimilarReportsVO.getReport_id());
                reportMapper.update_similarity_by_report_id(reportId, similar);
            }
        }catch (Exception e){
            reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
            reportMapper.update_similarity_by_report_id(reportId, 0);
        }
        changeReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_UPLOAD_REPORT_SUCCEEDED));
        return changeReportVO;
    }

    @Override
    public GetAllMyReportVO getAllMyReport(GetAllMyReportDTO getAllMyReportDTO){
        GetAllMyReportVO getAllMyReportVO = new GetAllMyReportVO();
        //????????????
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            getAllMyReportVO.setResponse(resultVO);
            return getAllMyReportVO;
        }
        Long taskId = getAllMyReportDTO.getTaskId();
        List<Report> reportList = reportMapper.get_all_by_user_id_and_task_id(userId,taskId);
        List<ReportBriefInfo> infoList = new ArrayList<>();
        for(int i=0;i<reportList.size();i++){
            Report report = reportList.get(i);
            ReportBriefInfo reportBriefInfo = new ReportBriefInfo(report);
            infoList.add(reportBriefInfo);
        }
        getAllMyReportVO.setReportList(infoList);
        getAllMyReportVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        return getAllMyReportVO;
    }
}


