package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import com.example.BackendVolatile.util.constant.TaskTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TaskPublishDTO{
    @Valid
    public InnerTask task;

    @NotNull
    public Long getTaskStartTime(){
        return task.getTaskStartTime();
    }

    @NotNull
    public  Long getEndTime(){
        return task.getTaskEndTime();
    }

    @NotNull
    public String getIntroduction(){
        return task.getTaskIntroduction();
    }

    @NotNull
    public Integer getWorkerNumTotal(){
        return task.getWorkerNumTotal();
    }

    @NotNull
    public Integer getTaskType(){
        return task.getTaskType();
    }

    @NotNull
    public String getTaskName(){
        return task.getTaskName();
    }

    @NotNull
    public List<File> getRequirementDescriptionFileList(){
        return task.getRequirementDescriptionFileList();
    }

    @NotNull
    public List<File> getExecutableFileList(){
        return task.getExecutableFileList();
    }

    @NotNull
    public Boolean getAndroid(){
        return task.getAndroid();
    }

    @NotNull
    public Boolean getIOS(){
        return task.getIos();
    }

    @NotNull
    public Boolean getLinux(){
        return task.getLinux();
    }

    @NotNull
    public Integer getTaskDifficulty(){
        return task.getTaskDifficulty();
    }

    @NotNull
    public Integer getTaskUrgency(){
        return task.getTaskUrgency();
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class InnerTask{
        @NotNull(message = ParamFormatErrorConstant.REQUIREMENT_DESCRIPTION_FILE_LIST_CANNOT_BE_NULL)
        public List<File> requirementDescriptionFileList;

        @NotNull(message = ParamFormatErrorConstant.EXECUTABLE_FILE_LIST_CANNOT_BE_NULL)
        public List<File> executableFileList;

        @NotBlank(message = ParamFormatErrorConstant.TASK_INTRODUCTION_CANNOT_BE_NULL)
        public String taskIntroduction;

        @NotNull(message = ParamFormatErrorConstant.TASK_START_TIME_CANNOT_BE_NULL)
        @Min(value = 0, message = ParamFormatErrorConstant.TASK_START_TIME_MUST_BE_POSITIVE)
        private Long taskStartTime;

        @NotNull(message = ParamFormatErrorConstant.TASK_END_TIME_CANNOT_BE_NULL)
        @Min(value = 1, message = ParamFormatErrorConstant.TASK_END_TIME_MUST_BE_POSITIVE)
        private Long taskEndTime;

        @NotNull(message = ParamFormatErrorConstant.WORKER_NUM_TOTAL_CANNOT_BE_NULL)
        @Min(value = 1, message = ParamFormatErrorConstant.WORK_NUM_TOTAL_MUST_BE_POSITIVE)
        private Integer workerNumTotal;

        @NotNull(message = ParamFormatErrorConstant.WORK_TYPE_CANNOT_BE_NULL)
        @Min(value = TaskTypeConstant.TASK_TYPE_MIN, message = ParamFormatErrorConstant.WRONG_WORK_TYPE_FORMAT)
        @Max(value = TaskTypeConstant.TASK_TYPE_MAX, message = ParamFormatErrorConstant.WRONG_WORK_TYPE_FORMAT)
        private Integer taskType;

        @NotBlank(message = ParamFormatErrorConstant.TASK_NAME_CANNOT_BE_NULL)
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

    }

}




