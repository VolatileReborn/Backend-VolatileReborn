package com.example.BackendVolatile.dto.userDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import com.example.BackendVolatile.util.constant.ParamRegexp;
import com.example.BackendVolatile.util.constant.RoleConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotBlank(message = ParamFormatErrorConstant.PHONE_NUMBER_CANNOT_BE_NULL)
    @Pattern(regexp= ParamRegexp.PHONE_NUMBER_FORMAT, message = ParamFormatErrorConstant.WRONG_PHONE_NUMBER_FORMAT)
    private String phone_number;

    @NotBlank(message = ParamFormatErrorConstant.PASSWORD_CANNOT_BE_NULL)
    private String password;

    @NotNull(message = ParamFormatErrorConstant.ROLE_CANNOT_BE_NULL)
    @Max(value = RoleConstant.ROLE_MAX, message = ParamFormatErrorConstant.WRONG_ROLE_FORMAT)
    @Min(value = RoleConstant.ROLE_MIN, message = ParamFormatErrorConstant.WRONG_ROLE_FORMAT)
    private Integer role;

    @NotBlank(message = ParamFormatErrorConstant.NICKNAME_CANNOT_BE_NULL)
    private String nickname;
}
