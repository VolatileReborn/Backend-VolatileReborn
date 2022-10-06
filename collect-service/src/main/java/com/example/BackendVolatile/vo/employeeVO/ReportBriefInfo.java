package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.dao.reportDAO.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportBriefInfo {

    private Long reportId;

    private String reportName;

    private Long taskId;

    private Long workerId;

    private Integer totalScore;

    private Integer similarity;

    public ReportBriefInfo(Report report){
        this.reportId = report.getReport_id();
        this.reportName = report.getReport_name();
        this.taskId = report.getTask_id();
        this.workerId = report.getUser_id();
        this.similarity = report.getSimilarity();
    }

}
