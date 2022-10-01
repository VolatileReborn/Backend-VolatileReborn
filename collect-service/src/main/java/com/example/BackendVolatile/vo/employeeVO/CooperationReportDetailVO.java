package com.example.BackendVolatile.vo.employeeVO;


import com.example.BackendVolatile.dao.reportDAO.CooperationDefectPicture;
import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CooperationReportDetailVO {

    private ResultVO response;
    private List<File> defectPictureList;
    private String defectExplain;
    private String defectReproduction;
    private String testEquipmentInfo;
    private String reportName;
    private Long parentId;


    public void setPartCooperationReportDetailVO(CooperationReport cooperationReport){
        this.defectExplain = cooperationReport.getDefect_explain();
        this.defectReproduction = cooperationReport.getDefect_reproduction_step();
        this.reportName = cooperationReport.getReport_name();
        this.testEquipmentInfo = cooperationReport.getTest_equipment_information();
        this.parentId = cooperationReport.getParent_report_id();
    }

    public void setDefectPictureList(List<CooperationDefectPicture> cooperationDefectPictureList) {
        this.defectPictureList = new ArrayList<>();
        for(int i = 0; i < cooperationDefectPictureList.size(); i++){
            File temp = new File(cooperationDefectPictureList.get(i).getPicture_url(),
                    cooperationDefectPictureList.get(i).getPicture_name());
            this.defectPictureList.add(temp);
        }
    }

}
