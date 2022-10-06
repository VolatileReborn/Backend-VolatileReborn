package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.PageMsgDTO;
import com.example.BackendVolatile.dto.employerDTO.*;
import com.example.BackendVolatile.service.EmployerService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.employerVO.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(path = "/api/employer")
public class EmployerController {
    @Resource
    EmployerService employerService;

    /**
     * Iter2新增
     * 发包方分页查看其发布的所有复合（可拆分的）任务的状态信息
     * @param pageMsgDTO 分页查询信息，包含页号及页大小
     * @return 一页的复合任务状态信息列表，按照复合任务发布时间降序排序
     * @see PageMsgDTO
     * @see BrowserCompositeTasksVO
     */
    @GetMapping(value = "/browserCompositeTasks")
    @UserLoginToken
    public BrowserCompositeTasksVO browserCompositeTasks(@Valid @RequestParam PageMsgDTO pageMsgDTO){
        return null;
    }

    @GetMapping(value = "/browserUndertakingTasks")
    @UserLoginToken
    public BrowserUndertakingTasksVO browserUndertakingTasks(){
        return employerService.browserUndertakingTasks();
    }

    @GetMapping(value = "/browserFinishedTasks")
    @UserLoginToken
    public BrowserFinishedTasksVO browserFinishedTasks(){
        return employerService.browserFinishedTasks();
    }

    @GetMapping(value = "/browserTaskDetail")
    @UserLoginToken
    public BrowserTaskDetailVO browserTaskDetail(@Valid BrowserTaskDetailDTO browserTaskDetailDTO){
        return employerService.browserTaskDetail(browserTaskDetailDTO);
    }

    @PutMapping(value = "/browserChecked")
    @UserLoginToken
    public BrowserCheckedVO browserChecked(@Valid BrowserCheckedDTO browserCheckedDTO){
        return employerService.browserChecked(browserCheckedDTO);
    }

    @GetMapping(value = "/reportDetail")
    @UserLoginToken
    public ReportDetailVO reportDetail(@Valid ReportDetailDTO reportDetailDTO){
        return employerService.reportDetail(reportDetailDTO);
    }

    @GetMapping(value = "/CooperationReportDetail")
    @UserLoginToken
    public CooperationReportDetailVO cooperationReportDetail(CooperationReportDetailDTO cooperationReportDetailDTO){
        return employerService.cooperationReportDetail(cooperationReportDetailDTO);
    }







}
