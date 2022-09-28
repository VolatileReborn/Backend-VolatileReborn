package com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil;

import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
import com.example.BackendVolatile.dao.reportDAO.Report;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetSimilarReportsDTO{
    public ReportDTO report;
    public List<ReportDTO> related_reports;
    public Integer similar_report_num;
    public String algorithm;

    public GetSimilarReportsDTO(){
        this.related_reports = new ArrayList<>();
        this.report = new ReportDTO();
    }

}

@Data
@NoArgsConstructor
class FileItem{

    private String file_name;
    private String file_url;
    private Long file_id;

    public void set(DefectPicture defectPicture){
        this.file_id = defectPicture.getPicture_id();
        this.file_name = defectPicture.getPicture_name();
        this.file_url = defectPicture.getPicture_url();
    }
}

@Data
class ReportDTO{
    private String report_name;
    private String defect_reproduction_step;
    private String test_equipment_information;
    private Long task_id;
    private Long user_id;
    private String defect_explain;
    private List<FileItem> defect_picture_list;
    private Long report_id;

    public ReportDTO(){
        this.defect_picture_list = new ArrayList<>();
    }

    public void set(List<DefectPicture> defectPictures, Report report){
        setPart(report);
        setDefect_picture_list(defectPictures);
    }

    public void setDefect_picture_list(List<DefectPicture> defectPictures){
        this.defect_picture_list = new ArrayList<>();
        for(int i=0;i<defectPictures.size();i++){
            FileItem temp = new FileItem();
            temp.set(defectPictures.get(i));
            this.defect_picture_list.add(temp);
        }
    }

    public void setPart(Report report){
        this.report_id = report.getReport_id();
        this.report_name = report.getReport_name();
        this.defect_reproduction_step = report.getDefect_reproduction_step();
        this.test_equipment_information = report.getTest_equipment_information();
        this.task_id = report.getTask_id();
        this.user_id = report.getUser_id();
        this.defect_explain = report.getDefect_explain();
    }

}