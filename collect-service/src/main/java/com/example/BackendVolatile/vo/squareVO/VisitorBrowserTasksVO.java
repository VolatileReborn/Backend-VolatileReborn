package com.example.BackendVolatile.vo.squareVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VisitorBrowserTasksVO {
    private ResultVO response;
    private List<TaskBriefInfo> taskList;

    public void setTaskList(List<Task> taskList){
        for(int i = 0; i < taskList.size(); i++){
            TaskBriefInfo temp = new TaskBriefInfo(taskList.get(i));
            this.taskList.add(temp);
        }
    }

    public VisitorBrowserTasksVO(){
        this.response = new ResultVO();
        this.taskList = new ArrayList<>();
    }

}
