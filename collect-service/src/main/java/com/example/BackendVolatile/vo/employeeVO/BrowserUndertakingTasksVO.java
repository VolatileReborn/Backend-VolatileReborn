package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.util.constant.TaskStateConstant;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrowserUndertakingTasksVO {
    private ResultVO response;
    private List<TaskBriefInfo> undertakingTaskList;

    public BrowserUndertakingTasksVO(){
        this.response = new ResultVO();
        this.undertakingTaskList = new ArrayList<>();
    }

    public void setUndertakingTaskList(List<Task> taskList) {
        for(int i = 0; i < taskList.size(); i++){
            Task task = taskList.get(i);
            if (task.getTask_state() == TaskStateConstant.UNDERTAKING.getCode()) {//0 是进行中
                TaskBriefInfo temp = new TaskBriefInfo(taskList.get(i));
                this.undertakingTaskList.add(temp);
            }
        }
    }
}

@Data
@NoArgsConstructor
class TaskBriefInfo {
    private Long taskId;
    private String taskName;
    private Long taskStartTime;
    private Long taskEndTime;
    private Integer workerNumTotal;
    private Integer taskType;
    private Integer workerNumLeft;

    public TaskBriefInfo(Task task){
        this.taskEndTime = task.getEnd_time();
        this.taskId = task.getTask_id();
        this.taskName = task.getTask_name();
        this.taskStartTime = task.getBegin_time();
        this.workerNumTotal = task.getWorker_num_total();
        this.taskType = task.getTask_type();
        this.workerNumLeft = task.getWorker_num_left();
    }

}
