package com.example.BackendVolatile.dto.employeeDTO;

import com.example.BackendVolatile.dto.taskDTO.File;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ChangeReportDTO {

    @NotNull(message = ParamFormatErrorConstant.TASK_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.TASK_ID_DONT_EXIST)
    private Long taskId;

    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long reportId;

    @Valid
    private TestReport taskReport;

    @NotNull
    public String getDefectExplain(){
        return taskReport.getDefectExplain();
    }

    @NotNull
    public String getReportName(){
        return taskReport.getReportName();
    }

    @NotNull
    public String getDefectReproductionStep(){
        return taskReport.getDefectReproductionStep();
    }

    @NotNull
    public String getTestEquipmentInformation(){
        return taskReport.getTestEquipmentInformation();
    }

    @NotNull
    public List<File> getDefectPictureList(){
        return taskReport.getDefectPictureList();
    }
}
