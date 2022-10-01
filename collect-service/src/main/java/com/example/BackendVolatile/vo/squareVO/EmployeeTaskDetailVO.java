package com.example.BackendVolatile.vo.squareVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmployeeTaskDetailVO {
    private ResultVO response;
    private EmployeeTaskDetail task;
    private Integer isSelected;

    public EmployeeTaskDetailVO(){
        this.response = new ResultVO();
        this.task = new EmployeeTaskDetail();
        this.isSelected = -1;
    }

    public void setTask(Task task){
        this.task = new EmployeeTaskDetail(task);
    }
}

@Data
@NoArgsConstructor
class EmployeeTaskDetail {
    private String taskName;
    private Long taskId;
    private Integer workerNumTotal;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumLeft;
    private Integer taskType;
    private Integer taskState;
    private String taskIntroduction;
    private Integer taskDifficulty;
    private Integer taskUrgency;
    private Boolean android;
    private Boolean linux;
    private Boolean ios;


    public EmployeeTaskDetail(Task task){
        this.taskId = task.getTask_id();
        this.taskEndTime = task.getEnd_time();
        this.taskName = task.getTask_name();
        this.workerNumTotal = task.getWorker_num_total();
        this.workerNumLeft = task.getWorker_num_left();
        this.taskStartTime = task.getBegin_time();
        this.taskType = task.getTask_type();
        this.taskState = task.getTask_state();
        this.taskIntroduction = task.getIntroduction();
        this.taskDifficulty = task.getTask_difficulty();
        this.taskUrgency = task.getTask_urgency();
        this.ios = task.getIos();
        this.linux = task.getLinux();
        this.android = task.getAndroid();

    }
}