package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.reportDTO.*;
import com.example.BackendVolatile.vo.reportVO.*;

public interface ReportService {

    CheckReportsVO checkReports(CheckReportsDTO checkReportsDTO);

    ScoreReportVO scoreReport(ScoreReportDTO scoreReportDTO);

    CooperationReportListVO cooperationReportList(CooperationReportListDTO cooperationReportListDTO);

    ShowReportScoreVO showReportScore(ShowReportScoreDTO showReportScoreDTO);

    GetSimilarityGraphVO getSimilarityGraph(GetSimilarityGraphDTO getSimilarityGraphDTO);

    GetCooperationTreeVO getCooperationTree(GetCooperationTreeDTO getCooperationTreeDTO);

    GetClusterScatterVO getClusterScatter(GetClusterScatterDTO getClusterScatterDTO);
}
