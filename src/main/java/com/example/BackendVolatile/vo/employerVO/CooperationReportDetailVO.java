package com.example.BackendVolatile.vo.employerVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationDefectPicture;
import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.employeeVO.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CooperationReportDetailVO {

    private ResultVO response;
    private List<File> defectPictureList;
    private String defectExplain;
    private String defectReproduction;
    private String testEquipmentInfo;
    private String reportName;
    private Long parentId;

    public void setPartCooperationReportDetail(CooperationReport cooperationReport){
        this.defectExplain = cooperationReport.getDefect_explain();
        this.defectReproduction = cooperationReport.getDefect_reproduction_step();
        this.testEquipmentInfo = cooperationReport.getTest_equipment_information();
        this.reportName = cooperationReport.getReport_name();
        this.parentId = cooperationReport.getParent_report_id();
    }

    public void setDefectPictureList(List<CooperationDefectPicture> cooperationDefectPictureList){
        this.defectPictureList = new ArrayList<>();
        for(int i = 0; i < cooperationDefectPictureList.size(); i++){
            this.defectPictureList.add(new File(cooperationDefectPictureList.get(i)));
        }
    }
}
