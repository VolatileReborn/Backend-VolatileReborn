package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.reportDTO.*;
import com.example.BackendVolatile.service.ReportService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.PassToken;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.reportVO.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/report")
public class ReportController {

    @Resource
    ReportService reportService;

    //根据任务id获得测试报告列表
    @GetMapping(value = "/checkReports")
    @PassToken
    public CheckReportsVO checkReports(@Valid CheckReportsDTO checkReportsDTO){
        return reportService.checkReports(checkReportsDTO);
    }

    //对测试报告进行评分，三种身份都可以
    @PostMapping(value = "/scoreReport")
    @UserLoginToken
    public ScoreReportVO scoreReport(@Valid @RequestBody ScoreReportDTO scoreReportDTO){
        return reportService.scoreReport(scoreReportDTO);
    }

    //employer/admin根据reportId获得它的协作报告的信息列表
    @GetMapping(value = "/cooperationReport")
    @UserLoginToken
    public CooperationReportListVO cooperationReportList(@Valid CooperationReportListDTO cooperationReportListDTO){
        return reportService.cooperationReportList(cooperationReportListDTO);
    }

    //根据userId和reportId获取一个用户的评论和评分
    @GetMapping(value = "/showReportScore")
    @UserLoginToken
    public ShowReportScoreVO showReportScore(@Valid ShowReportScoreDTO showReportScoreDTO){
        return reportService.showReportScore(showReportScoreDTO);
    }

    @GetMapping(value = "/getSimilarityGraph")
    @PassToken
    public GetSimilarityGraphVO getSimilarityGraph(@Valid GetSimilarityGraphDTO getSimilarityGraphDTO){
        return reportService.getSimilarityGraph(getSimilarityGraphDTO);
    }

    @GetMapping(value = "/getCooperationTree")
    @PassToken
    public GetCooperationTreeVO getCooperationTree(@Valid  GetCooperationTreeDTO getCooperationTreeDTO){
        return reportService.getCooperationTree(getCooperationTreeDTO);
    }


    @GetMapping(value = "/getClusterScatter")
    @PassToken
    public GetClusterScatterVO getClusterScatterVO(GetClusterScatterDTO getClusterScatterDTO){
        return reportService.getClusterScatter(getClusterScatterDTO);
    }
}