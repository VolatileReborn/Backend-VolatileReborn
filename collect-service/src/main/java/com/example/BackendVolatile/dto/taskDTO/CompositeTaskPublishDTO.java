package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositeTaskPublishDTO {

    /**
     * 复合任务名称
     */
    @NotBlank(message = ParamFormatErrorConstant.TASK_NAME_CANNOT_BE_NULL)
    private String taskName;

    /**
     * 复合任务简介
     */
    @NotBlank(message = ParamFormatErrorConstant.TASK_INTRODUCTION_CANNOT_BE_NULL)
    private String taskIntroduction;


    /**
     * 子任务列表
     * @see SubTaskDTO
     */
    @Valid
    private List<SubTaskDTO> subtasks;

    /**
     * 子任务的时序关系，即前后子任务索引对的集合
     * 例如子任务列表subtasks={A,B,C,D}，A/B/C/D在列表中的索引为0/1/2/3
     * 且时序关系为{A在B前，A在C前，B在D前，C在D前}
     * 则timingRel={(0,1),(0,2),(1,3),(2,3)}
     */
    @Valid
    private List<TaskOrderPairDTO> timingRel;
}
