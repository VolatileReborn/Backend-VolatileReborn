package com.example.BackendVolatile.dao.taskDAO.compositetask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskOrderPair {
    private Long ctask_id;
    private Long pre_task_id;
    private Long post_task_id;
}
