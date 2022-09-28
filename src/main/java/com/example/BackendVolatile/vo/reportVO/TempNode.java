package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.dao.reportDAO.CooperationReport;
import com.example.BackendVolatile.dao.reportDAO.Report;
import lombok.Data;

@Data
public class TempNode {
    private Long id;
    private String value;
    private Integer symbolSize;
    private Long category;
    private Long target;
    private String formatter;
    private Boolean show;
    private String parentName;
    private Long reportId;
    private Long similarId;

    public TempNode(Report report, Long id){
        this.id = id;
        this.value = report.getReport_name();
        this.symbolSize = 10;
        this.category = id;
//        this.target = ?;
        this.formatter = report.getSimilarity().toString();
        this.show = true;
        this.parentName = report.getReport_name();
        this.reportId = report.getReport_id();
        this.similarId = report.getSimilar_report_id();

    }

    public TempNode(CooperationReport cooperationReport, Long parentId, String parentName, Long id){
        this.id = id;
        this.value = cooperationReport.getReport_name();
        this.symbolSize = 5;
        this.category = parentId;
        this.target = parentId;
        this.formatter = "";
        this.show = false;
        this.parentName = parentName;
        this.reportId = cooperationReport.getReport_id();
    }


}
