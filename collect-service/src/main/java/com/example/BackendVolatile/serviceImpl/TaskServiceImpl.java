package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dao.taskDAO.compositetask.CompositeTask;
import com.example.BackendVolatile.dao.taskDAO.compositetask.ParentChildTaskPair;
import com.example.BackendVolatile.dao.taskDAO.compositetask.TaskOrderPair;
import com.example.BackendVolatile.dto.taskDTO.*;
import com.example.BackendVolatile.mapper.task.ExecutableFileMapper;
import com.example.BackendVolatile.mapper.task.RequirementDescriptionFileMapper;
import com.example.BackendVolatile.mapper.task.SelectTaskMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.service.TaskService;
import com.example.BackendVolatile.util.ParameterValidityVerification;
import com.example.BackendVolatile.util.constant.*;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.taskVO.AcceptTaskVO;
import com.example.BackendVolatile.vo.taskVO.PublishTaskVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    SelectTaskMapper selectTaskMapper;

    @Resource
    ExecutableFileMapper executableFileMapper;

    @Resource
    RequirementDescriptionFileMapper requirementDescriptionFileMapper;

    @Resource
    ParameterValidityVerification parameterValidityVerification;


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

    private PublishTaskVO publishTask(Task task, List<File> exeFileList, List<File> reqFileList) {
        PublishTaskVO publishTaskVO = new PublishTaskVO();
        taskMapper.insert(task);
        Long task_id = taskMapper.max_id();
        task.setTask_id(task_id);

        for (int i = 0; i < exeFileList.size(); i++) {
            ExecutableFile executableFile = new ExecutableFile(exeFileList.get(i));
            executableFile.setTask_id(task_id);
            executableFileMapper.insert(executableFile);
        }

        for (int i = 0; i < reqFileList.size(); i++) {
            RequirementDescriptionFile requirementDescriptionFile = new RequirementDescriptionFile(reqFileList.get(i));
            requirementDescriptionFile.setTask_id(task_id);
            requirementDescriptionFileMapper.insert(requirementDescriptionFile);
        }

        publishTaskVO.setResponse(new ResultVO(ResponseConstant.REQUEST_PUBLISH_TASK_SUCCEEDED));
        return publishTaskVO;
    }

    /**
     * ????????????employer
     * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param taskPublishDTO
     * @return
     */
    @Override
    public PublishTaskVO publishTask(TaskPublishDTO taskPublishDTO) {
        PublishTaskVO publishTaskVO = new PublishTaskVO();
        ValidationResult validationResult = validatePermission(RoleConstant.EMPLOYER.getRole());
        if (validationResult.valid != BooleanValue.TRUE) {
            publishTaskVO.setResponse(validationResult.resultVO);
            return publishTaskVO;
        }

        Long userId = validationResult.userId;
        Task task = new Task(taskPublishDTO, userId);
        return publishTask(task, taskPublishDTO.getExecutableFileList(), taskPublishDTO.getRequirementDescriptionFileList());
    }

    /**
     * ????????????employee
     * ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ?????????????????????????????????????????????????????????????????????0??????????????????????????????
     *
     * @param acceptTaskDTO
     * @return
     */
    @Override
    public AcceptTaskVO acceptTask(AcceptTaskDTO acceptTaskDTO) {
        AcceptTaskVO acceptTaskVO = new AcceptTaskVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO) tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long) tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long) tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if (valid != BooleanValue.TRUE) {
            acceptTaskVO.setResponse(resultVO);
            return acceptTaskVO;
        }
        Long taskId = acceptTaskDTO.getTaskId();

        //isNotSelected?????????????????????????????????????????????????????????????????????????????????
        Integer isNotSelected = selectTaskMapper.assert_is_not_selected(taskId, userId);
        Integer worker_num_left = taskMapper.get_worker_num_left_by_task_id(taskId);
        if (worker_num_left == 0) {
            acceptTaskVO.setResponse(new ResultVO(ResponseConstant.TASK_FULL));
            return acceptTaskVO;
        }
        selectTaskMapper.insert(taskId, userId);
//            ??????????????????????????????-1
        taskMapper.update_task_worker_num_left(taskId);
        acceptTaskVO.setResponse(new ResultVO(ResponseConstant.REQUEST_ACCEPT_TASK_SUCCEEDED));
        return acceptTaskVO;

    }

    private void formalizeCompositeTask(CompositeTaskPublishDTO compositeTaskPublishDTO){
        CompositeTaskDTO compositeTask=compositeTaskPublishDTO.task;
        List<SubTaskDTO> subTasks=compositeTask.getSubTasks();
        List<TaskOrderPairDTO> taskOrderPairs=new ArrayList<>();
        for(int i=0;i<subTasks.size();i++){
            SubTaskDTO subTask=subTasks.get(i);
            if(subTask.getPreTask()!=-1) taskOrderPairs.add(new TaskOrderPairDTO(subTask.getPreTask(),i));
        }
        compositeTask.setTimingRel(taskOrderPairs);
    }

    @Override
    public PublishTaskVO publishCompositeTask(@Valid CompositeTaskPublishDTO compositeTaskPublishDTO) {
        formalizeCompositeTask(compositeTaskPublishDTO);

        PublishTaskVO res = new PublishTaskVO();
        ValidationResult validationResult = validatePermission(RoleConstant.EMPLOYER.getRole());
        if (validationResult.valid != BooleanValue.TRUE) {
            res.setResponse(validationResult.resultVO);
            return res;
        }

        Long publisherId = validationResult.userId;

        List<SubTaskDTO> subTasks = compositeTaskPublishDTO.getTask().getSubTasks();
        List<Long> subTaskIds = new ArrayList<>();
        for (SubTaskDTO subTaskDTO : subTasks) {
            Task subTask = new Task(subTaskDTO, publisherId);
            publishTask(subTask, subTaskDTO.getExecutableFileList(), subTaskDTO.getRequirementDescriptionFileList());
            subTaskIds.add(subTask.getTask_id());
        }

        System.err.println(compositeTaskPublishDTO);
        taskMapper.insertCompositeTask(new CompositeTask(compositeTaskPublishDTO, publisherId));
        Long compositeTaskId = taskMapper.max_composite_task_id();

        List<ParentChildTaskPair> parentChildTaskPairs = new ArrayList<>();
        for (Long subTaskId : subTaskIds) parentChildTaskPairs.add(new ParentChildTaskPair(compositeTaskId, subTaskId));
        if(parentChildTaskPairs.size()!=0) taskMapper.insertCompositeSubTaskBatch(parentChildTaskPairs);

        List<TaskOrderPairDTO> taskOrderPairDTOs = compositeTaskPublishDTO.getTask().getTimingRel();
        List<TaskOrderPair> taskOrderPairs = new ArrayList<>();
        for (TaskOrderPairDTO taskOrderPairDTO : taskOrderPairDTOs)
            taskOrderPairs.add(new TaskOrderPair(compositeTaskId,
                    subTaskIds.get(taskOrderPairDTO.getPreTaskIndex()), subTaskIds.get(taskOrderPairDTO.getPostTaskIndex())));
        if(taskOrderPairs.size()!=0) taskMapper.insertTaskOrderPairBatch(taskOrderPairs);

        res.setResponse(new ResultVO(ResponseConstant.REQUEST_PUBLISH_TASK_SUCCEEDED));
        return res;
    }
}
