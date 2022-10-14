package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.taskDTO.AcceptTaskDTO;
import com.example.BackendVolatile.dto.taskDTO.CompositeTaskPublishDTO;
import com.example.BackendVolatile.dto.taskDTO.TaskPublishDTO;
import com.example.BackendVolatile.vo.taskVO.AcceptTaskVO;
import com.example.BackendVolatile.vo.taskVO.PublishTaskVO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;

public interface TaskService {
    PublishTaskVO publishTask(TaskPublishDTO taskPublishDTO);

    AcceptTaskVO acceptTask(AcceptTaskDTO acceptTaskDTO);

    PublishTaskVO publishCompositeTask(@Valid @RequestBody CompositeTaskPublishDTO compositeTaskPublishDTO);
}
