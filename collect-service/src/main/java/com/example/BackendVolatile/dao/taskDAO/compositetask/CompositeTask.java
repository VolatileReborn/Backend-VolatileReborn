package com.example.BackendVolatile.dao.taskDAO.compositetask;

import com.example.BackendVolatile.dto.taskDTO.CompositeTaskPublishDTO;
import com.example.BackendVolatile.vo.employerVO.CompositeTaskStateVO;
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

    private Long publisherId;

    private Date publishTime;

    private String taskName;

    private String taskIntroduction;

//    private List<Long> subtask_id_list;
//
//    private List<TaskOrderPair> timing_rel;

    public CompositeTask(CompositeTaskPublishDTO compositeTaskPublishDTO, Long userId){
        this.publisherId=userId;
        this.publishTime=new Date();
        this.taskName=compositeTaskPublishDTO.getTask().getTaskName();
        this.taskIntroduction=compositeTaskPublishDTO.getTask().getTaskIntroduction();
    }
}
