package com.example.BackendVolatile.dto.reportDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreReportDTO {

    @NotNull(message = ParamFormatErrorConstant.REPORT_SCORE_CANT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.WRONG_REPORT_SCORE_FORMAT)
    @Max(value = 5, message = ParamFormatErrorConstant.WRONG_REPORT_SCORE_FORMAT)
    private Integer score;

    @NotBlank(message = ParamFormatErrorConstant.REPORT_COMMENT_CANT_BE_NULL)
    private String comment;

    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long reportId;

}
