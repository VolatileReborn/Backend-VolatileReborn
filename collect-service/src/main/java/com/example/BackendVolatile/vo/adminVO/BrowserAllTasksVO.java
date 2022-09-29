package com.example.BackendVolatile.vo.adminVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrowserAllTasksVO {

    private ResultVO response;

    private List<InnerTask> taskList;

    public void setTaskList(List<Task> taskList){
        this.taskList = new ArrayList<>();
        for(int i = 0; i <taskList.size(); i++){
            this.taskList.add(new InnerTask(taskList.get(i)));
        }
    }

}

@Data
@NoArgsConstructor
class InnerTask {
    private Long taskId;
    private String taskName;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumTotal;
    private Integer taskType;
    private Integer workerNumLeft;

    public InnerTask(Task task){
        this.taskEndTime = task.getEnd_time();
        this.taskId = task.getTask_id();
        this.taskName = task.getTask_name();
        this.taskStartTime = task.getBegin_time();
        this.workerNumTotal = task.getWorker_num_total();
        this.taskType = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
    }

}

