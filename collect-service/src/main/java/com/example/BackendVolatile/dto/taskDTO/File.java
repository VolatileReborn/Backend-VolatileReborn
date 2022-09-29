package com.example.BackendVolatile.dto.taskDTO;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class File{

    @NotBlank(message = ParamFormatErrorConstant.FILE_NAME_CANNOT_BE_NULL)
    private String fileName;

    @NotBlank(message = ParamFormatErrorConstant.FILE_URL_CANNOT_BE_NULL)
//    @Pattern() //验证URL格式：^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$
    private String fileURL;
}
