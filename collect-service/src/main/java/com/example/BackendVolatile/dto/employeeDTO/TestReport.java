package com.example.BackendVolatile.dto.employeeDTO;

import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestReport {

    @NotBlank(message = ParamFormatErrorConstant.DEFECT_EXPLAIN_CANNOT_BE_NULL)
    private String defectExplain;

    @NotBlank(message = ParamFormatErrorConstant.DEFECT_REPRODUCTION_STEP_CANNOT_BE_NULL)
    private String defectReproductionStep;

    @NotBlank(message = ParamFormatErrorConstant.TEST_EQUIPMENT_INFORMATION_CANNOT_BE_NULL)
    private String testEquipmentInformation;

    @NotNull(message = ParamFormatErrorConstant.DEFECT_PICTURE_LIST_CANNOT_BE_NULL)
    private List<File> defectPictureList;

    @NotBlank(message = ParamFormatErrorConstant.REPORT_NAME_CANNOT_BE_NULL)
    private String reportName;
}
