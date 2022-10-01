package com.example.BackendVolatile.dto.adminDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetAlgorithmDTO {

    @NotBlank(message = ParamFormatErrorConstant.RULE_CANT_BE_NULL)
    String rule;
}
