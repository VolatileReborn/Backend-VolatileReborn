package com.example.BackendVolatile.dao.taskDAO.compositetask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentChildTaskPair {
    private Long parent_task_id;
    private Long child_task_id;
}
