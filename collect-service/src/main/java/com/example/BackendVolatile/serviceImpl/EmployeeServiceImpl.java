package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.reportDAO.*;
import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dto.employeeDTO.*;
import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.mapper.report.*;
import com.example.BackendVolatile.mapper.task.ExecutableFileMapper;
import com.example.BackendVolatile.mapper.task.RequirementDescriptionFileMapper;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.EmployeeService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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
     * 调用者：employee
     * 业务逻辑：众包工人在个人中心查看招募中的任务，后端返回该用户参与的所有正在进行中的任务
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
     * 调用者：employee
     * 业务逻辑：众包工人在个人中心查看所有停止招募的任务，后端返回该用户参与的所有正在停止招募的任务列表
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
     * 调用者：employee
     * 业务逻辑：众包工人在个人中心查看一个任务的细节，后端校验该用户已经参与了该任务，然后返回该任务的详细信息，以及报告提交状态（和报告id等信息）
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
        //isSelected的值无用，但是在调用时判断是否已经选择过，未选择过会报错
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
     * 调用者：employee
     * 业务逻辑：众包工人在个人中心查看任务那里提交测试报告，后端校验身份、已参加任务、报告未提交，和报告内容信息，然后写入数据库并返回成功
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
        //isSelected的值无用，但是在调用时判断是否已经选择过，未选择过会报错
        Integer isSelected = selectTaskMapper.assert_is_selected(taskId,userId);
        //isNotSubmitted的值无用，但是在调用时判断是否已经提交过，提交过会报错
//        Integer isNotSubmitted = reportMapper.assert_report_not_submitted(userId,taskId);

        Report report = new Report(uploadTestReportDTO,userId);

        report.setSimilarity(0);// 计算相似度,并邀请其他工人协作低质量报告
        report.setSimilar_report_id(1024L);

        Long time = System.currentTimeMillis();
        Integer utilToday = (int) (time / (24 * 60 *60 * 1000) - 19100);
        report.setReport_state(utilToday);
        reportMapper.insert(report);

        Long reportId = reportMapper.max_id();
        List<File> files = uploadTestReportDTO.getDefectPictureList();
        for(int i = 0;i < files.size(); i++){
            DefectPicture defectPicture = new DefectPicture(files.get(i));
            defectPicture.setReport_id(reportId);
            defectPictureMapper.insert(defectPicture);
        }
//        System.out.println("开始准备数据");
//
//        try {
//            String url = "http://"+PythonServerConstant.IP+ ":"+PythonServerConstant.PORT+"/prepareReportTrainingData";
//            System.out.println(url);
//            PrepareReportTrainingDataDTO pDTO = prepareReportTrainingDataUtil.getPrepareReportTrainingDataDTO(taskId,"DeepPrior");
//            System.out.println("开始连接");
//            Long time1 = System.currentTimeMillis();
//            String s = restTemplate.postForObject(url, pDTO, String.class);
//            Long time2 = System.currentTimeMillis();
//            System.out.println("准备完成, 用时" + (time2 - time1) / 1000 + "秒。");
//
//            String url2 = "http://"+PythonServerConstant.IP+ ":"+
//                    PythonServerConstant.PORT+"/getSimilarReports";
//
//            String s2 = restTemplate.postForObject(url2, pythonUtil.getSimilarReportsDTO(reportId,taskId,1,"DeepSimilarity"), String.class);
//            System.out.println("s2" + s2);
//
//            GetSimilarReportsVO getSimilarReportsVO = getSimilarReportsUtil.parseResult(s2);
//            if (getSimilarReportsVO.getReport_id() == -1) {
//                System.out.println("-1" + getSimilarReportsVO.getReport_id());
//                reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
//                reportMapper.update_similarity_by_report_id(reportId, 0);
//            } else {
//                System.out.println("zc" + getSimilarReportsVO.getReport_id());
//                Integer similar = (int) (getSimilarReportsVO.getSimilarity() * 100);
//                reportMapper.update_similar_report_id_by_report_id(reportId, getSimilarReportsVO.getReport_id());
//                reportMapper.update_similarity_by_report_id(reportId, similar);
//                if (similar > 20) {//相似度过高推给别人协作
//                    List<Long> userIdList = selectTaskMapper.get_user_id_by_task_id(taskId);
//                    if (userIdList.size() < 10) {
//                        for (int i = 0; i < userIdList.size(); i++) {
//                            if (userIdList.get(i).equals(userId)) {
//                                continue;
//                            } else {
//                                cooperationMapper.insert(reportId, userIdList.get(i), CooperationReportStateConstant.COOPERATING.getCode());
//                            }
//                        }
//                    } else {
//                        int p = userIdList.size() / 10;
//                        for (int i = 0; i < 10; i++) {
//                            cooperationMapper.insert(reportId, userIdList.get(i * p), CooperationReportStateConstant.COOPERATING.getCode());
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){
//            System.out.println("连接失败，无法计算相似度");
//            e.printStackTrace();
//            reportMapper.update_similar_report_id_by_report_id(reportId, reportId);
//            reportMapper.update_similarity_by_report_id(reportId, 0);
//        }
        uploadTestReportVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_UPLOAD_REPORT_SUCCEEDED));
        return uploadTestReportVO;

    }

    /**
     * 调用者：employee
     * 业务逻：众包工人在个人中心查看已提交的报告的细节，后端校验身份，任务，报告已提交，并返回报告的信息
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
        //isSelected的值无用，但是在调用时判断是否已经选择过，未选择过会报错
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
        //协作
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

        //限制修改
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
     * 调用者：employee
     * 业务逻辑：接包工人对报告进行协作，提交协作报告
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
        //isCooperated的值无用，但是在调用时判断是否已经加入协作关系，未加入会报错
        Integer isCooperated = cooperationMapper.assert_is_cooperated(parentReportId,userId);

        //值无用但是会判断是否已经提交过，如果提交过就会报错报告已提交
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
        return uploadCooperationReportVO;
    }



    /**
     * 调用者：employee
     * 业务逻辑：众包工人在个人中心查看协作报告细节
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
//        //isSubmitted的值无用，但是在调用时判断是否已经提交过，未提交过会报错
//        Integer isSubmitted = cooperationReportMapper.assert_report_is_submitted(userId,cooperationReportId);

        CooperationReport cooperationReport = cooperationReportMapper.get_by_report_id(cooperationReportId);
        cooperationReportDetailVO.setPartCooperationReportDetailVO(cooperationReport);

        List<CooperationDefectPicture> defectPictureList = cooperationDefectPictureMapper.get_all_by_report_id(cooperationReportId);
        cooperationReportDetailVO.setDefectPictureList(defectPictureList);
        cooperationReportDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYEE_SUCCEEDED));
        return cooperationReportDetailVO;
    }

    /**
     * 业务逻辑：众包工人主动加入协作关系
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

        //isNotSelected的值无用，但是在调用时判断是否已经选择过，选择过会报错
        Integer isNotCooperated = cooperationMapper.assert_is_not_cooperated(reportId,userId);
        System.out.println("isNotCooperated" + isNotCooperated);

        Long taskId = reportMapper.get_task_id_by_report_id(reportId);
        //同样原因的校验
        Integer isSelected = selectTaskMapper.is_selected(taskId,userId);
        System.out.println("isSelected" +isSelected);

        cooperationMapper.insert(reportId,userId, CooperationReportStateConstant.COOPERATING.getCode());

        cooperateReportVO.setResponse(new ResultVO(ResponseConstant.REQUEST_ACCEPT_TASK_SUCCEEDED));
        return cooperateReportVO;
    }

    /**
     * 接包方获取协作中列表
     * @return
     */
    @Override
    public CooperatingListVO cooperatingList(){
        CooperatingListVO cooperatingListVO = new CooperatingListVO();
        //身份验证
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperatingListVO.setResponse(resultVO);
            return cooperatingListVO;
        }

        //获取reportId列表
        List<Long> reportIdList = cooperationMapper.select_report_id_by_user_id_and_report_state(
                userId, CooperationReportStateConstant.COOPERATING.getCode());
        List<Integer> scoreTemp;
        List<Integer> totalScoreList = new ArrayList<>();
        List<Report> reportList = new ArrayList<>();
        for( int i = 0; i< reportIdList.size(); i++){
            //获取报告信息
            reportList.add(reportMapper.get_by_report_id(reportIdList.get(i)));
            //计算评分
            scoreTemp = reportScoreMapper.get_score_by_report_id(reportIdList.get(i));
            totalScoreList.add(scoreUtil.calTotalScore(scoreTemp));

        }
        cooperatingListVO.setReportList(reportList,totalScoreList);
        cooperatingListVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));

        return cooperatingListVO;
    }


    /**
     * 调用者：employee
     * 业务逻辑：众包工人获取已协作列表
     * @return
     */
    @Override
    public CooperatedListVO cooperatedList(){
        CooperatedListVO cooperatedListVO = new CooperatedListVO();
        //身份验证
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperatedListVO.setResponse(resultVO);
            return cooperatedListVO;
        }

        //获取reportId列表
        List<Long> reportIdList = cooperationMapper.select_report_id_by_user_id_and_report_state(
                userId, CooperationReportStateConstant.COOPERATED.getCode());
        List<Integer> scoreTemp;
        List<Integer> totalScoreList = new ArrayList<>();
        List<Report> reportList = new ArrayList<>();
        for( int i = 0; i< reportIdList.size(); i++){
            //获取报告信息
            reportList.add(reportMapper.get_by_report_id(reportIdList.get(i)));
            //计算评分
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
        //身份验证
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
        System.out.println("前"+ reportId);
        Integer reportState = reportMapper.get_report_state_by_report_id(reportId);
        System.out.println("后1");
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

        //计算相似度
        System.out.println("开始连接");
        try {
            String url = "http://" + PythonServerConstant.IP + ":" +
                    PythonServerConstant.PORT + "/getSimilarReports";

            String s = restTemplate.postForObject(url, pythonUtil.getSimilarReportsDTO(reportId, taskId, 1, "DeepSimilarity"), String.class);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("请求报错，未准备数据，开始准备");
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
        //身份验证
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


