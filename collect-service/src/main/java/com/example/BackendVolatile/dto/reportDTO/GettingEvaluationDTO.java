package com.example.BackendVolatile.dto.reportDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GettingEvaluationDTO {
    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long reportId;

    @NotNull(message = "需要指定是否为协作报告")
    @Min(value = 0, message = "isCoop非法")
    @Max(value = 1, message = "isCoop非法")
    private Integer isCoop;
}
