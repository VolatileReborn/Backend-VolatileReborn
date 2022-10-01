package com.example.BackendVolatile.dto.userDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import com.example.BackendVolatile.util.constant.ParamRegexp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = ParamFormatErrorConstant.PHONE_NUMBER_CANNOT_BE_NULL)
    @Pattern(regexp= ParamRegexp.PHONE_NUMBER_FORMAT, message = ParamFormatErrorConstant.WRONG_PHONE_NUMBER_FORMAT)
    private String phone_number;

    @NotBlank(message = ParamFormatErrorConstant.PASSWORD_CANNOT_BE_NULL)
    // @Size()//长度限制
    private String password;


}
