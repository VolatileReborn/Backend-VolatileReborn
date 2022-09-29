package com.example.BackendVolatile.vo.squareVO;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;

@Data
public class TaskDetailVO {
    private ResultVO response;
    private TaskBriefInfo task;

    public void setTask(Task task) {
        this.task = new TaskBriefInfo(task);

    }
}
