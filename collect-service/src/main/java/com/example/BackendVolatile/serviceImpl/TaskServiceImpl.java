package com.example.BackendVolatile.serviceImpl;

import com.example.BackendVolatile.dao.taskDAO.ExecutableFile;
import com.example.BackendVolatile.dao.taskDAO.RequirementDescriptionFile;
import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.dto.taskDTO.AcceptTaskDTO;
import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.dto.taskDTO.TaskPublishDTO;
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


    /**
     * 调用者：employer
     * 业务逻辑：发包方发布任务，填写相应信息，后端对数据进行校验，然后写入数据库
     * @param taskPublishDTO
     * @return
     */
    @Override
    public PublishTaskVO publishTask(TaskPublishDTO taskPublishDTO) {
        PublishTaskVO publishTaskVO = new PublishTaskVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYER.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            publishTaskVO.setResponse(resultVO);
            return publishTaskVO;
        }

        Task task = new Task(taskPublishDTO,userId);
        taskMapper.insert(task);
        Long task_id = taskMapper.max_id();

        List<File> executableFileList = taskPublishDTO.getExecutableFileList();
        for(int i = 0; i < executableFileList.size(); i++){
            ExecutableFile executableFile = new ExecutableFile(executableFileList.get(i));
            executableFile.setTask_id(task_id);
            executableFileMapper.insert(executableFile);
        }

        List<File> requirementDescriptionFileList = taskPublishDTO.getRequirementDescriptionFileList();
        for(int i = 0; i < requirementDescriptionFileList.size(); i++){
            RequirementDescriptionFile requirementDescriptionFile =
                    new RequirementDescriptionFile(requirementDescriptionFileList.get(i));
            requirementDescriptionFile.setTask_id(task_id);
            requirementDescriptionFileMapper.insert(requirementDescriptionFile);
        }

        publishTaskVO.setResponse(new ResultVO(ResponseConstant.REQUEST_PUBLISH_TASK_SUCCEEDED));
        return publishTaskVO;
    }

    /**
     * 调用者：employee
     * 业务逻辑：众包工人在任务大厅看到任务后接受任务，后端校验用户身份，任务是否存在，是否已经被选择，没有的话允许用户选择人物并写入数据库，
     * 同时对应任务的需求人数减一，如果剩余人数已经为0，则提示无法人数已满
     * @param acceptTaskDTO
     * @return
     */
    @Override
    public AcceptTaskVO acceptTask(AcceptTaskDTO acceptTaskDTO){
        AcceptTaskVO acceptTaskVO = new AcceptTaskVO();
        Map<String, Object> tokenVerification =
                parameterValidityVerification.tokenVerification(RoleConstant.EMPLOYEE.getRole());
        ResultVO resultVO = (ResultVO)tokenVerification.get(VerificationMapConstant.RESULTVO.getStr());
        Long valid = (Long)tokenVerification.get(VerificationMapConstant.VALID.getStr());
        Long userId = (Long)tokenVerification.get(VerificationMapConstant.USERID.getStr());

        if(valid != BooleanValue.TRUE){
            acceptTaskVO.setResponse(resultVO);
            return acceptTaskVO;
        }
        Long taskId = acceptTaskDTO.getTaskId();

        //isNotSelected的值无用，但是在调用时判断是否已经选择过，选择过会报错
        Integer isNotSelected = selectTaskMapper.assert_is_not_selected(taskId,userId);
        Integer worker_num_left = taskMapper.get_worker_num_left_by_task_id(taskId);
        if(worker_num_left ==0){
            acceptTaskVO.setResponse(new ResultVO(ResponseConstant.TASK_FULL));
            return acceptTaskVO;
        }
        selectTaskMapper.insert(taskId,userId);
//            该任务的的所缺工人数-1
        taskMapper.update_task_worker_num_left(taskId);
        acceptTaskVO.setResponse(new ResultVO(ResponseConstant.REQUEST_ACCEPT_TASK_SUCCEEDED));
        return acceptTaskVO;

    }
}
