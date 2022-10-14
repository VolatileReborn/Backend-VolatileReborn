package com.example.BackendVolatile.dao.taskDAO;
import com.example.BackendVolatile.dto.taskDTO.SubTaskDTO;
import com.example.BackendVolatile.dto.taskDTO.TaskPublishDTO;
import com.example.BackendVolatile.util.constant.TaskStateConstant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Task implements Serializable {
    private Long task_id;

    private Long user_id;

    /**
     * 测试类型
     */
    private Integer task_type;

    /**
     * 任务状态， 1：finished
     */
    private Integer task_state;

    private String task_name;

    private Long begin_time;

    private Long end_time;

    private String introduction;

    private Integer worker_num_total;

    private Integer worker_num_left;
    private Integer task_difficulty;
    private Integer task_urgency;
    private Boolean android;
    private Boolean ios;
    private Boolean linux;


    public Task(TaskPublishDTO taskPublishDTO, Long userId) {
        this.user_id = userId;
        this.introduction = taskPublishDTO.getIntroduction();
        this.task_type = taskPublishDTO.getTaskType();
        this.begin_time = taskPublishDTO.getTaskStartTime();
        this.end_time = taskPublishDTO.getEndTime();
        this.worker_num_total = taskPublishDTO.getWorkerNumTotal();
        this.worker_num_left = taskPublishDTO.getWorkerNumTotal();
        this.task_name = taskPublishDTO.getTaskName();
        this.task_state = TaskStateConstant.UNDERTAKING.getCode();
        this.task_difficulty = taskPublishDTO.getTaskDifficulty();
        this.task_urgency = taskPublishDTO.getTaskUrgency();
        this.android = taskPublishDTO.getAndroid();
        this.ios = taskPublishDTO.getIOS();
        this.linux = taskPublishDTO.getLinux();
    }

    public Task(SubTaskDTO subTaskDTO, Long publisherId) {
        this.user_id = publisherId;
        this.introduction = subTaskDTO.getTaskIntroduction();
        this.task_type = subTaskDTO.getTaskType();
        this.begin_time = null;
        this.end_time = null;
        this.worker_num_total = subTaskDTO.getWorkerNumTotal();
        this.worker_num_left = subTaskDTO.getWorkerNumTotal();
        this.task_name = subTaskDTO.getTaskName();
        this.task_state = TaskStateConstant.UNDERTAKING.getCode();
        this.task_difficulty = subTaskDTO.getTaskDifficulty();
        this.task_urgency = subTaskDTO.getTaskUrgency();
        this.android = subTaskDTO.getAndroid();
        this.ios = subTaskDTO.getIos();
        this.linux = subTaskDTO.getLinux();
    }


}
