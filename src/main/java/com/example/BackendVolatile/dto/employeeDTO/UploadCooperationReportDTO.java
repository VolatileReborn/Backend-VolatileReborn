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
public class UploadCooperationReportDTO {

    @NotNull(message = ParamFormatErrorConstant.TASK_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.TASK_ID_DONT_EXIST)
    private Long taskId;

    @Valid
    private CooperationReport taskReport;

    public String getDefectExplain(){
        return taskReport.getDefectExplain();
    }

    public String getReportName(){
        System.out.println("getReportName-----------");
        System.out.println(taskReport == null);
        System.out.println("taskId:" + taskId );
        return taskReport.getReportName();
    }

    public String getDefectReproductionStep(){
        System.out.println("getDefectReproductionStep-----------");
        System.out.println(taskReport == null);
        return taskReport.getDefectReproductionStep();
    }

    public String getTestEquipmentInformation(){
        return taskReport.getTestEquipmentInformation();
    }

    public List<File> getDefectPictureList(){
        return taskReport.getDefectPictureList();
    }

    public Long getParentReportId(){
        return taskReport.getParentReportId();
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CooperationReport {

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

    @NotNull(message = ParamFormatErrorConstant.REPORT_ID_CANNOT_BE_NULL)
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_ID_DONT_EXIST)
    private Long parentReportId;
}

