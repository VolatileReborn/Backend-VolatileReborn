package com.example.BackendVolatile.dao.reportDAO;

import com.example.BackendVolatile.dto.employeeDTO.UploadCooperationReportDTO;
import com.example.BackendVolatile.dto.employeeDTO.UploadTestReportDTO;
import com.example.BackendVolatile.util.constant.ReportStateConstant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Report implements Serializable {
    @Setter(AccessLevel.NONE)
    private Long report_id;

    private String report_name;

    private Long user_id;//众包工人的id

    private Integer report_state;

    private String defect_explain;

    private String defect_reproduction_step;

    private String test_equipment_information;

    private Long task_id; // 报告对应的任务id

    private Integer similarity;

    private Long similar_report_id;


    public Report(UploadTestReportDTO uploadTestReportDTO, long userId){
        this.task_id = uploadTestReportDTO.getTaskId();
        this.defect_explain = uploadTestReportDTO.getDefectExplain();
        this.defect_reproduction_step = uploadTestReportDTO.getDefectReproductionStep();
        this.test_equipment_information = uploadTestReportDTO.getTestEquipmentInformation();
        this.user_id = userId;
        this.report_name = uploadTestReportDTO.getReportName();
        this.report_state = ReportStateConstant.TO_BE_REVIEWED.getCode(); //0为审核中

    }


    public Report(UploadCooperationReportDTO uploadCooperationReportDTO, long userId){
        this.task_id = uploadCooperationReportDTO.getTaskId();
        this.defect_explain = uploadCooperationReportDTO.getDefectExplain();
        this.defect_reproduction_step = uploadCooperationReportDTO.getDefectReproductionStep();
        this.test_equipment_information = uploadCooperationReportDTO.getTestEquipmentInformation();
        this.user_id = userId;
        this.report_name = uploadCooperationReportDTO.getReportName();
        this.report_state = ReportStateConstant.TO_BE_REVIEWED.getCode(); //0为审核中
    }

}
