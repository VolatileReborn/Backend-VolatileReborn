package com.example.BackendVolatile.dto.userDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetUserProfileDTO {

    @NotBlank(message = ParamFormatErrorConstant.PROFESSIONAL_SKILL_CANNOT_BE_NULL)
    private String professionalSkill;

    @NotBlank(message = ParamFormatErrorConstant.NICKNAME_CANNOT_BE_NULL)
    private String nickname;

    @NotNull(message = ParamFormatErrorConstant.TASK_FAVOR_LIST_CANNOT_BE_NULL)
    private List<@Valid String> taskFavorList;

    @NotBlank(message = ParamFormatErrorConstant.DEVICE_CANNOT_BE_NULL)
    private String device;

}
