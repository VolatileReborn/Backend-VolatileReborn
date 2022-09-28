package com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData;

import lombok.Data;

import java.util.List;

@Data
public class PrepareReportTrainingDataDTO {
    String algorithm;
    Long task_id;
    List<ReportInfo> report_list;

}

@Data
class ReportInfo{
    Long report_id;
    String report_name;
    Long task_id;
    String defect_explanation;
    String defect_reproduction_step;
    String test_equipment_information;
    List<PictureInfo> defect_picture_list;

}

@Data
class PictureInfo{
    String img_name;
    String img_url;
    Long img_id;
}