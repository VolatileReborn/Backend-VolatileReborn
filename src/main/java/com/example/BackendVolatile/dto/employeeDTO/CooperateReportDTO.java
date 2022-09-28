package com.example.BackendVolatile.dto.employeeDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CooperateReportDTO {

    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long reportId;

}
