package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class CheckReportsVO {
    private ResultVO response;
    private List<ReportBriefInfo> reportList;

    public CheckReportsVO(){
        this.reportList = new ArrayList<>();

    }

    public void setReportList(List<Report> reportList) {
        this.reportList = new ArrayList<>();
        for(int i = 0; i < reportList.size(); i++){
            ReportBriefInfo tp = new ReportBriefInfo();
            tp.setReportId(reportList.get(i).getReport_id());
            tp.setWorkerId(reportList.get(i).getUser_id());
            tp.setReportName(reportList.get(i).getReport_name());
            tp.setSimilarity(reportList.get(i).getSimilarity());
            this.reportList.add(tp);
        }
    }

    public void setReportList2(List<Report> reportListArg,List<Long> ids) {
        List<Long> reportIdList = new ArrayList<>();
        this.reportList = new ArrayList<>();
        for(int i = 0; i < reportListArg.size(); i++){
            reportIdList.add(reportListArg.get(i).getReport_id());
        }
        for(int i=0;i<ids.size();i++){
            if(reportIdList.contains(ids.get(i))){
                int index = reportIdList.indexOf(ids.get(i));
                Report report = reportListArg.get(index);
                ReportBriefInfo tp = new ReportBriefInfo();
                tp.setReportId(report.getReport_id());
                tp.setWorkerId(report.getUser_id());
                tp.setReportName(report.getReport_name());
                tp.setSimilarity(report.getSimilarity());
                this.reportList.add(tp);
                reportListArg.remove(index);
                reportIdList.remove(index);
            }
        }

        for(int i = 0; i < reportListArg.size(); i++){
            ReportBriefInfo tp = new ReportBriefInfo();
            tp.setReportId(reportListArg.get(i).getReport_id());
            tp.setWorkerId(reportListArg.get(i).getUser_id());
            tp.setReportName(reportListArg.get(i).getReport_name());
            tp.setSimilarity(reportListArg.get(i).getSimilarity());
            this.reportList.add(tp);
        }
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ReportBriefInfo {
    private Long reportId;
    private Long workerId;
    private String reportName;
    private Integer similarity;
}