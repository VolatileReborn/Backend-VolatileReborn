package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationDefectPicture;
import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.dao.reportDAO.ReportScore;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ReportDetailVO {
    private ResultVO response;
    private List<File> defectPictureList;
    private String defectExplain;
    private String defectReproduction;
    private String testEquipmentInfo;
    private String reportName;
    private Integer isCooperated;
    private Integer isScored;
    private String comment;
    private Integer score;
    private Integer totalScore;
    private Long workerId;
    private Integer isSubmitted;
    private Long cooperationReportId;
    private Integer isChangeable;

    public void setPartReportDetailVO(Report report){
        this.defectExplain = report.getDefect_explain();
        this.defectReproduction = report.getDefect_reproduction_step();
        this.testEquipmentInfo = report.getTest_equipment_information();
        this.reportName = report.getReport_name();
        this.workerId = report.getUser_id();
    }

    public void setDefectPictureList(List<DefectPicture> defectPictureList) {
        this.defectPictureList = new ArrayList<>();
        for(int i = 0; i < defectPictureList.size(); i++){
            File temp = new File(defectPictureList.get(i).getPicture_url(),
                    defectPictureList.get(i).getPicture_name());
            this.defectPictureList.add(temp);
        }
    }

    public void setScoreInfo(ReportScore reportScore){
        this.comment = reportScore.getComment();
        this.score = reportScore.getScore();
    }
}
