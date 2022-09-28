package com.example.BackendVolatile.vo.squareVO;


import com.example.BackendVolatile.dao.taskDAO.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskBriefInfo {
    private String taskName;
    private Long taskId;
    private Integer workerNumTotal;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumLeft;
    private Integer taskType;
    //add
    private String taskIntroduction;
    private Integer taskState;
    private Integer taskDifficulty;
    private Integer taskUrgency;
    private Boolean android;
    private Boolean linux;
    private Boolean ios;


    TaskBriefInfo(Task task){
        this.taskId = task.getTask_id();
        this.taskEndTime = task.getEnd_time();
        this.workerNumTotal = task.getWorker_num_total();
        this.workerNumLeft = task.getWorker_num_left();
        this.taskName = task.getTask_name();
        this.taskStartTime = task.getBegin_time();
        this.taskType = task.getTask_type();
        this.taskIntroduction = task.getIntroduction();
        this.taskState = task.getTask_state();
        this.taskDifficulty = task.getTask_difficulty();
        this.taskUrgency = task.getTask_urgency();
        this.ios = task.getIos();
        this.linux = task.getLinux();
        this.android = task.getAndroid();
    }

}