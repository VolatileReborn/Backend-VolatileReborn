package com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil;

import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.mapper.report.AlgorithmMapper;
import com.example.BackendVolatile.mapper.report.DefectPictureMapper;
import com.example.BackendVolatile.mapper.report.ReportMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetSimilarReportsUtil {

    @Resource
    ReportMapper reportMapper;

    @Resource
    DefectPictureMapper defectPictureMapper;

    @Resource
    AlgorithmMapper algorithmMapper;

    public GetSimilarReportsDTO getSimilarReportsDTO(Long reportId){
        System.out.println(reportId);
        GetSimilarReportsDTO getSimilarReportsDTO = new GetSimilarReportsDTO();
        Report report = reportMapper.get_by_report_id(reportId);
        List<DefectPicture> defectPictures = defectPictureMapper.get_all_by_report_id(reportId);
        getSimilarReportsDTO.report.set(defectPictures,report);
        Long taskId = report.getTask_id();
        List<Report> reportList= reportMapper.get_all_by_task_id(taskId);
        System.out.println(reportList.size());
        getSimilarReportsDTO.related_reports = new ArrayList<>();
        for(int i = 0; i<reportList.size();i++){
            Long tempReportId = reportList.get(i).getReport_id();
            List<DefectPicture> defectPictureList = defectPictureMapper.get_all_by_report_id(tempReportId);
            ReportDTO tempReportDTO = new ReportDTO();
            tempReportDTO.set(defectPictureList,reportList.get(i));
            getSimilarReportsDTO.related_reports.add(tempReportDTO);
        }
        getSimilarReportsDTO.similar_report_num = 5;
        getSimilarReportsDTO.algorithm = algorithmMapper.get_algorithm();
        return getSimilarReportsDTO;

    }

    public GetSimilarReportsVO parseResult(String s){
        Long reportId  = -1L;
        Double similarity = 0.0;
        if(s.startsWith("[{\"report_id\":")){
            String need = s.split("}")[0];
            String[] stringList = need.split(", ");
            if(stringList[0].contains("report_id")){
                reportId = Long.parseLong(stringList[0].split(": ")[1]);
                similarity = Double.parseDouble(stringList[1].split(": ")[1]);
            }
            else{
                reportId = Long.parseLong(stringList[1].split(": ")[1]);
                similarity = Double.parseDouble(stringList[0].split(": ")[1]);
            }
        }
        System.out.println(reportId + "reportId");
        System.out.println(similarity);
        GetSimilarReportsVO getSimilarReportsVO = new GetSimilarReportsVO();
        getSimilarReportsVO.setSimilarity(similarity);
        getSimilarReportsVO.setReport_id(reportId);
        return getSimilarReportsVO;
    }
}

