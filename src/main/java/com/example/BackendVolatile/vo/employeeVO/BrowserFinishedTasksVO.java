package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.util.constant.TaskStateConstant;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrowserFinishedTasksVO {
    private ResultVO response;
    private List<FinishedTaskBriefInfo> finishedTaskList;

    public void setFinishedTaskList(List<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            if(task.getTask_state() == TaskStateConstant.FINISHED.getCode()){
                FinishedTaskBriefInfo temp = new FinishedTaskBriefInfo(taskList.get(i));
                this.finishedTaskList.add(temp);
            }

        }
    }

    public BrowserFinishedTasksVO(){
        this.finishedTaskList = new ArrayList<>();
        this.response = new ResultVO();
    }
}

@Data
@NoArgsConstructor
class FinishedTaskBriefInfo {
    private Long taskId;
    private String taskName;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumTotal;
    private Integer taskType;
    private Integer workerNumLeft;

    public FinishedTaskBriefInfo(Task task){
        this.taskId = task.getTask_id();
        this.taskName = task.getTask_name();
        this.taskStartTime = task.getBegin_time();
        this.taskEndTime = task.getEnd_time();
        this.taskType = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
        this.workerNumTotal = task.getWorker_num_total();
    }

}
