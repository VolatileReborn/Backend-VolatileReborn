package com.example.BackendVolatile.util.pythonUtil.prepareReportTrainingData;

import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.mapper.report.DefectPictureMapper;
import com.example.BackendVolatile.mapper.report.ReportMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PrepareReportTrainingDataUtil {
    @Resource
    ReportMapper reportMapper;

    @Resource
    DefectPictureMapper defectPictureMapper;

    public PrepareReportTrainingDataDTO getPrepareReportTrainingDataDTO(Long taskId, String algorithm){
        PrepareReportTrainingDataDTO pDTO = new PrepareReportTrainingDataDTO();
        pDTO.setTask_id(taskId);
        pDTO.setAlgorithm(algorithm);
        //报告列表
        List<ReportInfo> reportInfos = new ArrayList<>();
        List<Report> reportList = reportMapper.get_all_by_task_id(taskId);
        for(int i=0;i<reportList.size();i++){
            Report report = reportList.get(i);
            Long reportId = report.getReport_id();

            ReportInfo reportInfo = new ReportInfo();
            reportInfo.setTask_id(taskId);
            reportInfo.setReport_id(reportId);
            reportInfo.setReport_name(report.getReport_name());
            reportInfo.setDefect_explanation(report.getDefect_explain());
            reportInfo.setTest_equipment_information(report.getTest_equipment_information());
            reportInfo.setDefect_reproduction_step(report.getDefect_reproduction_step());

            //处理缺陷图片
            List<PictureInfo> pictureInfoList = new ArrayList<>();
            List<DefectPicture> defectPictureList = defectPictureMapper.get_all_by_report_id(reportId);
            for(int j = 0; j < defectPictureList.size();j++){
                DefectPicture defectPicture = defectPictureList.get(j);
                PictureInfo pictureInfo = new PictureInfo();
                pictureInfo.setImg_id(defectPicture.getPicture_id());
                pictureInfo.setImg_name(defectPicture.getPicture_name());
                pictureInfo.setImg_url(defectPicture.getPicture_url());
                pictureInfoList.add(pictureInfo);
            }
            reportInfo.setDefect_picture_list(pictureInfoList);
            reportInfos.add(reportInfo);
        }
        System.out.println("reportInfos:" + reportInfos.toString());
        pDTO.setReport_list(reportInfos);

        return pDTO;
    }


}
