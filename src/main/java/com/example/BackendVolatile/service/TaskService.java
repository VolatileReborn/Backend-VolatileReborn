package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.taskDTO.AcceptTaskDTO;
import com.example.BackendVolatile.dto.taskDTO.TaskPublishDTO;
import com.example.BackendVolatile.vo.taskVO.AcceptTaskVO;
import com.example.BackendVolatile.vo.taskVO.PublishTaskVO;

import java.io.IOException;

public interface TaskService {
    PublishTaskVO publishTask(TaskPublishDTO taskPublishDTO);

    AcceptTaskVO acceptTask(AcceptTaskDTO acceptTaskDTO);
}
