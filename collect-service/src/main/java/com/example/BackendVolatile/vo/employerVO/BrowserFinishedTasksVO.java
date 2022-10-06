package com.example.BackendVolatile.vo.employerVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BrowserFinishedTasksVO implements Serializable{
    private ResultVO response;
    private List<InnerFinishedTask> finishedTaskList;

    public void setFinishedTaskList(List<Task> taskList) {
        for(int i=0;i<taskList.size();i++){
            if(taskList.get(i).getTask_state() == 1){
                InnerFinishedTask temp = new InnerFinishedTask(taskList.get(i));
                this.finishedTaskList.add(temp);
            }
        }
    }

    public BrowserFinishedTasksVO(){
        this.finishedTaskList = new ArrayList<>();
        this.response = new ResultVO();
    }

    @Data
    @NoArgsConstructor
    static class InnerFinishedTask implements Serializable{
        private Long taskId;
        private String taskName;
        private Long taskStartTime;
        private Long taskEndTime;
        private Integer workerNumTotal;
        private Integer taskType;
        private Integer workerNumLeft;

        public InnerFinishedTask(Task task){
            this.taskId = task.getTask_id();
            this.taskName = task.getTask_name();
            this.taskStartTime = task.getBegin_time();
            this.taskEndTime = task.getEnd_time();
            this.taskType = task.getTask_type();
            this.workerNumTotal = task.getWorker_num_total();
            this.workerNumLeft = task.getWorker_num_left();

    }
}

}

