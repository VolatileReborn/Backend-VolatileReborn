package com.example.BackendVolatile.vo.employerVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrowserUndertakingTasksVO {
    private ResultVO response;
    private List<InnerUndertakingTask> undertakingTaskList;

    public BrowserUndertakingTasksVO(){
        this.response = new ResultVO();
        this.undertakingTaskList = new ArrayList<>();
    }

    public void setUndertakingTaskList(List<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getTask_state() == 0){//0 是进行中
                InnerUndertakingTask temp = new InnerUndertakingTask(taskList.get(i));
                this.undertakingTaskList.add(temp);
            }
        }
    }
}

@Data
@NoArgsConstructor
class InnerUndertakingTask {
    private Long taskId;
    private String taskName;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumTotal;
    private Integer taskType;
    private Integer workerNumLeft;

    public InnerUndertakingTask(Task task){
        this.taskEndTime = task.getEnd_time();
        this.taskId = task.getTask_id();
        this.taskName = task.getTask_name();
        this.taskStartTime = task.getBegin_time();
        this.workerNumTotal = task.getWorker_num_total();
        this.taskType = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
    }

}
