package com.example.BackendVolatile.dao.taskDAO.compositetask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private Long taskId;
    private String taskName;
    private Integer taskType;
    private Integer workerNumTotal;
    private Integer workerNumLeft;
    private Integer reportNum; // 完成的工人数量
}
