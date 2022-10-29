package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import com.example.BackendVolatile.util.constant.TaskTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 子任务信息
 * 与一般任务信息的结构基本一致，去掉了开始时间和结束时间属性
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskDTO {
    @NotNull(message = ParamFormatErrorConstant.REQUIREMENT_DESCRIPTION_FILE_LIST_CANNOT_BE_NULL)
    public List<File> requirementDescriptionFileList;

    @NotNull(message = ParamFormatErrorConstant.EXECUTABLE_FILE_LIST_CANNOT_BE_NULL)
    public List<File> executableFileList;

    @NotBlank(message = ParamFormatErrorConstant.TASK_INTRODUCTION_CANNOT_BE_EMPTY)
    public String taskIntroduction;

    @NotNull(message = ParamFormatErrorConstant.WORKER_NUM_TOTAL_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.WORK_NUM_TOTAL_MUST_BE_POSITIVE)
    private Integer workerNumTotal;

    @NotNull(message = ParamFormatErrorConstant.WORK_TYPE_CANNOT_BE_NULL)
    @Min(value = TaskTypeConstant.TASK_TYPE_MIN, message = ParamFormatErrorConstant.WRONG_WORK_TYPE_FORMAT)
    @Max(value = TaskTypeConstant.TASK_TYPE_MAX, message = ParamFormatErrorConstant.WRONG_WORK_TYPE_FORMAT)
    private Integer taskType;

    @NotBlank(message = ParamFormatErrorConstant.TASK_NAME_CANNOT_BE_EMPTY)
    private String taskName;

    @Max(value = ParamFormatErrorConstant.TASK_DIFFICULTY_MAX, message = ParamFormatErrorConstant.WRONG_TASK_DIFFICULTY_FORMAT)
    @Min(value = ParamFormatErrorConstant.TASK_DIFFICULTY_MIN, message = ParamFormatErrorConstant.WRONG_TASK_DIFFICULTY_FORMAT)
    @NotNull(message = ParamFormatErrorConstant.TASK_DIFFICULTY_CANNOT_BE_NULL)
    private Integer taskDifficulty;

    @NotNull(message = ParamFormatErrorConstant.TASK_URGENCY_CANNOT_BE_NULL)
    @Max(value = ParamFormatErrorConstant.TASK_URGENCY_MAX, message = ParamFormatErrorConstant.WRONG_TASK_URGENCY_FORMAT)
    @Min(value = ParamFormatErrorConstant.TASK_URGENCY_MIN, message = ParamFormatErrorConstant.WRONG_TASK_URGENCY_FORMAT)
    private Integer taskUrgency;

    @NotNull(message = ParamFormatErrorConstant.DEVICE_REQUIREMENT_ANDROID_CANNOT_BE_NULL)
    private Boolean android;

    @NotNull(message = ParamFormatErrorConstant.DEVICE_REQUIREMENT_LINUX_CANNOT_BE_NULL)
    private Boolean linux;

    @NotNull(message = ParamFormatErrorConstant.DEVICE_REQUIREMENT_IOS_CANNOT_BE_NULL)
    private Boolean ios;

    private Integer preTask;
}

