package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TaskIdDTO {
    /**
     * 复合任务或普通任务或复合任务的子任务的id
     */
    @NotNull(message = ParamFormatErrorConstant.TASK_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.TASK_ID_DONT_EXIST)
    private Long taskId;
}
