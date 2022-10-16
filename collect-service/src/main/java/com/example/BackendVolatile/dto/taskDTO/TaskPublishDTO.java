package com.example.BackendVolatile.dto.taskDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPublishDTO{
    @Valid
    public TaskDTO task;


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
}




