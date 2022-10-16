package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositeTaskPublishDTO {

    @Valid
    public CompositeTaskDTO task;

//    public CompositeTaskPublishDTO(String taskName, String taskIntroduction, List<SubTaskDTO> subTasks, List<TaskOrderPairDTO> timingRel)
//    {
//        this.subTasks = subTasks;
//        this.taskName = taskName;
//        this.taskIntroduction = taskIntroduction;
//        this.timingRel = timingRel;
//    }

//    public CompositeTaskPublishDTO()
//    {
//        ;
//    }

}
