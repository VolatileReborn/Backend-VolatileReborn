package com.example.BackendVolatile.vo.employerVO;

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
    private List<InnerFile> defectPictureList;
    private String defectExplain;
    private String defectReproduction;
    private String testEquipmentInfo;
    private Long taskId;
    private Long workerId;
    private String reportName;
    private Integer isScored;
    private String comment;
    private Integer score;
    private Integer totalScore;

    public void setPartReportInfo(Report report){
        this.defectExplain = report.getDefect_explain();
        this.defectReproduction = report.getDefect_reproduction_step();
        this.testEquipmentInfo = report.getTest_equipment_information();
        this.workerId = report.getUser_id();
        this.taskId = report.getTask_id();
        this.reportName = report.getReport_name();
    }

    public void setDefectPictureList(List<DefectPicture> defectPictureList) {
        this.defectPictureList = new ArrayList<>();
        for(int i = 0;i < defectPictureList.size(); i++){
            InnerFile temp = new InnerFile(defectPictureList.get(i).getPicture_url(),
                    defectPictureList.get(i).getPicture_name());
            this.defectPictureList.add(temp);
        }
    }

    public void setScoreInfo(ReportScore reportScore){
        this.comment = reportScore.getComment();
        this.score = reportScore.getScore();
    }
}
