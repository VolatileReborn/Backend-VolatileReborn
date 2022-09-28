package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CooperationReportListVO {

    private ResultVO response;

    private List<CooperationReportInfo> cooperationReportList;

    public void setCooperationReportList(List<CooperationReport> cooperationReportList) {
        this.cooperationReportList = new ArrayList<>();
        for(int i = 0; i < cooperationReportList.size(); i++){
            this.cooperationReportList.add(new CooperationReportInfo(cooperationReportList.get(i)));
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class CooperationReportInfo{

    private Long reportId;

    private Long workerId;

    private String reportName;

    public CooperationReportInfo(CooperationReport cooperationReport){
        this.reportId = cooperationReport.getReport_id();
        this.reportName = cooperationReport.getReport_name();
        this.workerId = cooperationReport.getUser_id();
    }
}
