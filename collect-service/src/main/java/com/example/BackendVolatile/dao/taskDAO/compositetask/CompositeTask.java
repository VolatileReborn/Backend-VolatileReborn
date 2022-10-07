package com.example.BackendVolatile.dao.taskDAO.compositetask;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CompositeTask {

    @Setter(AccessLevel.NONE)
    private Long id;

    private Date publish_time;

    private List<Long> subtask_id_list;

    private List<TaskOrderPair> timing_rel;
}
