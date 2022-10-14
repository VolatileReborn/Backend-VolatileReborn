package com.example.BackendVolatile.dto.employeeDTO;

import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadTestReportDTO {

    @NotNull(message = ParamFormatErrorConstant.TASK_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.TASK_ID_DONT_EXIST)
    private Long taskId;

    @Valid
    private TestReport testReport;

    @NotNull
    public String getDefectExplain(){
        return testReport.getDefectExplain();
    }

    @NotNull
    public String getReportName(){
        return testReport.getReportName();
    }

    @NotNull
    public String getDefectReproductionStep(){
        return testReport.getDefectReproductionStep();
    }

    @NotNull
    public String getTestEquipmentInformation(){
        return testReport.getTestEquipmentInformation();
    }

    @NotNull
    public List<File> getDefectPictureList(){
        return testReport.getDefectPictureList();
    }

}
