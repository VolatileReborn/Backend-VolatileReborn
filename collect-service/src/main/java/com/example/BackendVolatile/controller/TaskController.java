package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.taskDTO.AcceptTaskDTO;
import com.example.BackendVolatile.dto.taskDTO.CompositeTaskPublishDTO;
import com.example.BackendVolatile.dto.taskDTO.TaskPublishDTO;
import com.example.BackendVolatile.service.TaskService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.taskVO.AcceptTaskVO;
import com.example.BackendVolatile.vo.taskVO.PublishTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(path = "/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * Iter2新增
     * 发包方发布复合（可拆分的）任务
     * 发布时需要同时传入所有子任务的信息、子任务之间的所有时序关系
     * @param compositeTaskPublishDTO 复合任务信息
     * @return 发布结果
     * @see CompositeTaskPublishDTO
     * @see PublishTaskVO
     */
    @PostMapping(value = "/compositeTask/publish")
    @UserLoginToken
    public PublishTaskVO publishCompositeTask(@Valid @RequestBody CompositeTaskPublishDTO compositeTaskPublishDTO){
        return null;
    }


    @PostMapping(value = "/publishTask")
    @UserLoginToken
    public PublishTaskVO publishTask(@Valid @RequestBody TaskPublishDTO taskPublishDTO){
        System.out.println(taskPublishDTO.getTaskDifficulty());
        System.out.println(taskPublishDTO.getAndroid());
        return taskService.publishTask(taskPublishDTO);
    }

    @PostMapping(value="/acceptTask")
    @UserLoginToken
    public AcceptTaskVO acceptTask(@Valid @RequestBody AcceptTaskDTO acceptTaskDTO){
        return taskService.acceptTask(acceptTaskDTO);
    }



}
