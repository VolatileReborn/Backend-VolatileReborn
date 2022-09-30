package com.example.BackendVolatile.dto;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PageMsgDTO {
    // 页号
    @NotNull(message = ParamFormatErrorConstant.PAGE_NUM_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.PAGE_NUM_MUST_BE_POSITIVE)
    private Integer pageNum;

    // 每页大小
    @NotNull(message = ParamFormatErrorConstant.PAGE_SIZE_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.PAGE_SIZE_MUST_BE_POSITIVE)
    private Integer pageSize;
}
