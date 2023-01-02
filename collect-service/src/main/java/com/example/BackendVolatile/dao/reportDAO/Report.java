package com.example.BackendVolatile.dao.reportDAO;

import com.example.BackendVolatile.dto.employeeDTO.UploadCooperationReportDTO;
import com.example.BackendVolatile.dto.employeeDTO.UploadTestReportDTO;
import com.example.BackendVolatile.serviceImpl.ReportServiceImpl;
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


    public Report(ReportServiceImpl.AugmentedReport augmentedReport, long taskId) {
        this.task_id = taskId;
        this.defect_explain = augmentedReport.getDefect_explanation();
        this.defect_reproduction_step = augmentedReport.getDefect_reproduction_step();
        this.test_equipment_information = augmentedReport.getTest_equipment_information();
        this.user_id = null;
        this.report_name = augmentedReport.getReport_name();
        this.report_state = ReportStateConstant.COOPERATED.getCode();

    }

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

    public Report(CooperationReport cooperationReport){
        this.task_id=cooperationReport.getTask_id();
        this.defect_explain=cooperationReport.getDefect_explain();
        this.defect_reproduction_step=cooperationReport.getDefect_reproduction_step();
        this.report_id=cooperationReport.getReport_id();
        this.report_name=cooperationReport.getReport_name();
        this.user_id=cooperationReport.getUser_id();
        this.report_state=cooperationReport.getReport_state();
        this.test_equipment_information=cooperationReport.getTest_equipment_information();
    }

}
