package com.example.BackendVolatile.dto.reportDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AugmentationRequestDTO {
    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long reportId;

    @NotNull(message = ParamFormatErrorConstant.AUGMENTED_REPORT_NUM_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.AUGMENTED_REPORT_NUM_MUST_BE_POSITIVE)
    private Integer augmentedReportNum;
}
