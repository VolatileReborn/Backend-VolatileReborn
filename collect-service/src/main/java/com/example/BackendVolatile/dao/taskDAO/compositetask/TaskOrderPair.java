package com.example.BackendVolatile.dao.taskDAO.compositetask;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskOrderPair {
    private Integer earlier_task_id;
    private Integer later_task_id;
}
