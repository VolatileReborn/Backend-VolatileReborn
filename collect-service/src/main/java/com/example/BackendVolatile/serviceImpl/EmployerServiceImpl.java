package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.reportDAO.*;
import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dao.taskDAO.compositetask.CompositeTask;
import com.example.BackendVolatile.dao.taskDAO.compositetask.SubTask;
import com.example.BackendVolatile.dao.taskDAO.compositetask.TaskOrderPair;
import com.example.BackendVolatile.dto.employerDTO.*;
import com.example.BackendVolatile.mapper.report.*;
import com.example.BackendVolatile.mapper.task.ExecutableFileMapper;
import com.example.BackendVolatile.mapper.task.RequirementDescriptionFileMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.EmployerService;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.ScoreUtil;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.vo.employerVO.*;
import com.example.BackendVolatile.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    ExecutableFileMapper executableFileMapper;

    @Resource
    RequirementDescriptionFileMapper requirementDescriptionFileMapper;

    @Resource
    ReportMapper reportMapper;

    @Resource
    DefectPictureMapper defectPictureMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;

    @Resource
    CooperationReportMapper cooperationReportMapper;

    @Resource
    CooperationDefectPictureMapper cooperationDefectPictureMapper;

    @Resource
    ReportScoreMapper reportScoreMapper;

    @Resource
    ScoreUtil scoreUtil;

    /**
     * 调用者：employer
     * 业务逻辑：发包方在个人中心浏览正在进行中的任务，后端返回该发包方发布的，所有正在进行中的任务
     * @return
     */
    @Override
    public BrowserUndertakingTasksVO browserUndertakingTasks(){
        BrowserUndertakingTasksVO browserUndertakingTasksVO = new BrowserUndertakingTasksVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYER.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserUndertakingTasksVO.setResponse(resultVO);
            return browserUndertakingTasksVO;
        }

        List<Task> taskList = taskMapper.get_one_state_tasks_by_user_id_without_paging(userId,
                TaskStateConstant.UNDERTAKING.getCode());
        browserUndertakingTasksVO.setUndertakingTaskList(taskList);
        browserUndertakingTasksVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return browserUndertakingTasksVO;

    }

    /**
     * 调用者：employer
     * 业务逻辑：发包方在个人中心浏览停止招募的任务列表，后端返回该用户发布的所有停止招募的任务的信息
     * @return
     */
    @Override
    public BrowserFinishedTasksVO browserFinishedTasks(){
        BrowserFinishedTasksVO browserFinishedTasksVO = new BrowserFinishedTasksVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYER.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserFinishedTasksVO.setResponse(resultVO);
            return browserFinishedTasksVO;
        }

        List<Task> taskList = taskMapper.get_one_state_tasks_by_user_id_without_paging(userId,
                TaskStateConstant.FINISHED.getCode());
        browserFinishedTasksVO.setFinishedTaskList(taskList);
        browserFinishedTasksVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return browserFinishedTasksVO;
    }

    /**
     * 调用者：employer/admin
     * 业务逻辑：发包方在个人中心查看任务详情，后端校验该任务是不是该用户发布的，并返回对应任务的详细数据
     * @param browserTaskDetailDTO
     * @return
     */
    @Override
    public BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO){
        BrowserTaskDetailVO browserTaskDetailVO = new BrowserTaskDetailVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(),RoleConstant.ADMIN.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            browserTaskDetailVO.setResponse(resultVO);
            return browserTaskDetailVO;
        }

        Long taskId = browserTaskDetailDTO.getTaskId();
        Task task = taskMapper.get_by_task_id(taskId);
        browserTaskDetailVO.setTaskPartDetail(task);

        List<ExecutableFile> executableFileList =
                executableFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setExecutableFileList(executableFileList);

        List<RequirementDescriptionFile> requirementDescriptionFileList =
                requirementDescriptionFileMapper.get_all_by_task_id_without_paging(taskId);
        browserTaskDetailVO.setRequirementDescriptionFileList(requirementDescriptionFileList);

        browserTaskDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return browserTaskDetailVO;
    }

    /**
     * 调用者：employer
     * 业务逻辑：发包方将招募中的任务状态改变为停止招募，在此之前要检验该任务是不是进行中，是不是该用户发布的
     * @param browserCheckedDTO
     * @return
     */
    @Override
    public BrowserCheckedVO browserChecked(BrowserCheckedDTO browserCheckedDTO){
        BrowserCheckedVO browserCheckedVO = new BrowserCheckedVO();

        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYER.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            browserCheckedVO.setResponse(resultVO);
            return browserCheckedVO;
        }

        Long taskId = browserCheckedDTO.getTaskId();
        //taskExistenceAndBelonging的值无用，但在调用时会判断该任务是否属于userId，如果不属于就会报错
        Integer taskExistenceAndBelonging = taskMapper.get_task_existence_by_user_id_and_task_id(taskId,userId);
        //isUndertaking 的值无用，但会在调用时判断taskId是否是进行中，如果不是就会报错
        Integer isUndertaking = taskMapper.task_is_undertaking(taskId,TaskStateConstant.UNDERTAKING.getCode());
        taskMapper.update_task_state(taskId,TaskStateConstant.FINISHED.getCode());
        browserCheckedVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_UPDATE_TASK_STATE_SUCCEEDED));
        return browserCheckedVO;
    }

    /**
     * 调用者：employer/admin
     * 业务逻辑：发包方在个人中心查看报告细节，后端验证报告存在，且属于对应任务，然后返回报告的相关信息
     * @param reportDetailDTO
     * @return
     */
    @Override
    public ReportDetailVO reportDetail(ReportDetailDTO reportDetailDTO){
        ReportDetailVO reportDetailVO = new ReportDetailVO();

//        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(),RoleConstant.ADMIN.getRole()};
//        Map<String, Object> tokenVerification =
//                parameterValidityVerification.tokenVerification(roleList);
//        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
//        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
//        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());
//
//        if(valid != BooleanValue.TRUE){
//            reportDetailVO.setResponse(resultVO);
//            return reportDetailVO;
//        }
        Long userId=28L;
        Long reportId = reportDetailDTO.getReportId();

        Report report = reportMapper.get_by_report_id(reportId);
        reportDetailVO.setPartReportInfo(report);
        List<DefectPicture> defectPictureList = defectPictureMapper.get_all_by_report_id(reportId);
        reportDetailVO.setDefectPictureList(defectPictureList);

        Integer scoreInIt = reportScoreMapper.score_in_it_by_user_id_and_report_id(userId,reportId);
        if(scoreInIt == 0){
            reportDetailVO.setIsScored(0);
        }
        else{
            reportDetailVO.setIsScored(1);
            ReportScore reportScore = reportScoreMapper.get_by_user_id_and_report_id(userId, reportId);
            reportDetailVO.setScoreInfo(reportScore);
        }

        List<Integer> scoreTemp = reportScoreMapper.get_score_by_report_id(reportId);
        Integer totalScore = scoreUtil.calTotalScore(scoreTemp);
        reportDetailVO.setTotalScore(totalScore);


        reportDetailVO.setResponse(new ResultVO(ResponseConstant.EMPLOYER_SUCCEEDED));
        return reportDetailVO;
    }

    /**
     * 调用者：employer/admin
     * 业务逻辑：根据协作报告id查看协作报告细节
     * @param cooperationReportDetailDTO
     * @return
     */
    @Override
    public CooperationReportDetailVO cooperationReportDetail(CooperationReportDetailDTO cooperationReportDetailDTO){
        CooperationReportDetailVO cooperationReportDetailVO = new CooperationReportDetailVO();

        int[] roleList = new int[]{RoleConstant.EMPLOYER.getRole(),RoleConstant.ADMIN.getRole()};
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(roleList);
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());

        if(valid != BooleanValue.TRUE){
            cooperationReportDetailVO.setResponse(resultVO);
            return cooperationReportDetailVO;
        }

        Long reportId = cooperationReportDetailDTO.getCooperationReportId();

        CooperationReport cooperationReport = cooperationReportMapper.get_by_report_id(reportId);

        cooperationReportDetailVO.setPartCooperationReportDetail(cooperationReport);
        List<CooperationDefectPicture> cooperationDefectPictureList =
                cooperationDefectPictureMapper.get_all_by_report_id(reportId);
        cooperationReportDetailVO.setDefectPictureList(cooperationDefectPictureList);
        cooperationReportDetailVO.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));

        return cooperationReportDetailVO;
    }

    @Override
    public BrowserCompositeTasksVO getCompositeTasksWithoutValidation(Integer pageNum, Integer pageSize, Long publisherId) {
        BrowserCompositeTasksVO res = new BrowserCompositeTasksVO();

        List<CompositeTask> compositeTasks = publisherId==null?
                taskMapper.get_all_composite_tasks():taskMapper.get_composite_tasks_by_publisher_id(publisherId);
        res.setCurrSumSize(compositeTasks.size());

        compositeTasks=compositeTasks.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize,compositeTasks.size()));
        List<CompositeTaskStateVO> compositeTaskStateVOList=new ArrayList<>();
        for (CompositeTask compositeTask : compositeTasks) {

            CompositeTaskStateVO compositeTaskStateVO = new CompositeTaskStateVO(compositeTask);

            List<SubTask> subTasks = taskMapper.get_subtasks(compositeTask.getId());
            List<TaskOrderPair> taskOrderPairs = taskMapper.get_task_order_pairs(compositeTask.getId());
            Map<Long, Integer> idIndexMap = new HashMap<>();
            for (int index = 0; index < subTasks.size(); index++) {
                idIndexMap.put(subTasks.get(index).getTaskId(), index);
            }
            List<CompositeTaskStateVO.TaskOrderPairVO> timingRel = new ArrayList<>();
            for (TaskOrderPair taskOrderPair : taskOrderPairs)
                timingRel.add(new CompositeTaskStateVO.TaskOrderPairVO(
                        idIndexMap.get(taskOrderPair.getPre_task_id()),
                        idIndexMap.get(taskOrderPair.getPost_task_id())
                ));
            compositeTaskStateVO.setTimingRel(timingRel);

            Map<Long, Boolean> isStartTask = new HashMap<>();
            for (SubTask subTask : subTasks) isStartTask.put(subTask.getTaskId(), Boolean.TRUE);
            for (TaskOrderPair taskOrderPair : taskOrderPairs)
                isStartTask.put(taskOrderPair.getPost_task_id(), Boolean.FALSE);

            Map<Long, List<Long>> laterTaskMap = new HashMap<>();
            for (TaskOrderPair taskOrderPair : taskOrderPairs) {
                Long earlierTaskId = taskOrderPair.getPre_task_id();
                if (laterTaskMap.get(earlierTaskId) == null) {
                    laterTaskMap.put(earlierTaskId,
                            new ArrayList<>(Collections.singletonList(taskOrderPair.getPost_task_id())));
                } else laterTaskMap.get(earlierTaskId).add(taskOrderPair.getPost_task_id());
            }

            //List<Long> subTaskIds=subTaskStates.stream().map(SubTask::getTaskId).collect(Collectors.toList());
            Map<Long, CompositeTaskStateVO.InnerSubTaskVO> subTaskVOMap = new HashMap<>();
            for (SubTask subTask : subTasks) {
                subTaskVOMap.put(subTask.getTaskId(), new CompositeTaskStateVO.InnerSubTaskVO(subTask));
                if (subTask.getWorkerNumTotal().equals(subTask.getReportNum())) {
                    subTaskVOMap.get(subTask.getTaskId()).setTaskState(CompositeTaskStateVO.SubTaskState.COMPLETED);
                }
            }
            for (SubTask subTask : subTasks) {
                Long taskId = subTask.getTaskId();
                if (subTask.getWorkerNumTotal().equals(subTask.getReportNum())) {
                    List<Long> laterTaskIds = laterTaskMap.get(taskId);
                    if(laterTaskIds!=null) for (Long laterTaskId : laterTaskIds) {
                        CompositeTaskStateVO.InnerSubTaskVO laterTask = subTaskVOMap.get(laterTaskId);
                        if (laterTask.getTaskState() == null) {
                            if (laterTask.getWorkerNumLeft() > 0) {
                                laterTask.setTaskState(CompositeTaskStateVO.SubTaskState.RECRUITING);
                            } else laterTask.setTaskState(CompositeTaskStateVO.SubTaskState.IN_PROGRESS);
                        }
                    }
                }
            }
            for (SubTask subTask : subTasks) {
                Long taskId = subTask.getTaskId();
                if (subTaskVOMap.get(taskId).getTaskState() == null) {
                    if (isStartTask.get(taskId)) {
                        if (subTask.getWorkerNumLeft() > 0) {
                            subTaskVOMap.get(taskId).setTaskState(CompositeTaskStateVO.SubTaskState.RECRUITING);
                        } else subTaskVOMap.get(taskId).setTaskState(CompositeTaskStateVO.SubTaskState.IN_PROGRESS);
                    } else
                        subTaskVOMap.get(subTask.getTaskId()).setTaskState(CompositeTaskStateVO.SubTaskState.NOT_STARTED);
                }
            }

            List<CompositeTaskStateVO.InnerSubTaskVO> subTaskVOList = new ArrayList<>();
            for (SubTask subTask : subTasks) subTaskVOList.add(subTaskVOMap.get(subTask.getTaskId()));
            compositeTaskStateVO.setSubTasks(subTaskVOList);


            CompositeTaskStateVO.CompositeTaskState compositeTaskState = CompositeTaskStateVO.CompositeTaskState.COMPLETED;
            for (CompositeTaskStateVO.InnerSubTaskVO subTask : subTaskVOList) {
                if (subTask.getTaskState() != CompositeTaskStateVO.SubTaskState.COMPLETED) {
                    compositeTaskState = CompositeTaskStateVO.CompositeTaskState.IN_PROGRESS;
                    break;
                }
            }
            compositeTaskStateVO.setState(compositeTaskState);

            compositeTaskStateVOList.add(compositeTaskStateVO);

        }

        res.setResponse(new ResultVO(ResponseConstant.REQUEST_DATA_SUCCEEDED));
        res.setCompositeTaskStateList(compositeTaskStateVOList);

        return res;
    }
    @Override
    public BrowserCompositeTasksVO browserCompositeTasks(Integer pageNum, Integer pageSize) {
        BrowserCompositeTasksVO res = new BrowserCompositeTasksVO();
        ValidationResult validationResult = validatePermission(RoleConstant.EMPLOYER.getRole());
        if (validationResult.valid != BooleanValue.TRUE) {
            res.setResponse(validationResult.resultVO);
            return res;
        }
        return getCompositeTasksWithoutValidation(pageNum,pageSize,validationResult.userId);
    }

    static class ValidationResult {
        ResultVO resultVO;
        Long valid;
        Long userId;

        ValidationResult(Long valid, ResultVO resultVO, Long userId) {
            this.resultVO = resultVO;
            this.valid = valid;
            this.userId = userId;
        }
    }

    private ValidationResult validatePermission(int role) {
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(role);
        return new ValidationResult((Long) tokenVerification.get(VerificationMapConstant.VALID.getStr()),
                (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr()),
                (Long) tokenVerification.get(VerificationMapConstant.USERID.getStr()));
    }


}
