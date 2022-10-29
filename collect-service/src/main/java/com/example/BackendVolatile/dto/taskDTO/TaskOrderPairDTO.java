package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskOrderPairDTO {

    /**
     * 前序子任务在子任务列表中的索引
     */
//    @NotNull(message = ParamFormatErrorConstant.TASK_INTRODUCTION_CANNOT_BE_EMPTY)
//    @Min(value = 0, message = ParamFormatErrorConstant.TASK_INDEX_CANNOT_BE_NEGATIVE)
    private Integer preTaskIndex;

    /**
     * 后序子任务在子任务列表中的索引
     */
//    @NotNull(message = ParamFormatErrorConstant.TASK_INTRODUCTION_CANNOT_BE_EMPTY)
//    @Min(value = 0, message = ParamFormatErrorConstant.TASK_INDEX_CANNOT_BE_NEGATIVE)
    private Integer postTaskIndex;
}
