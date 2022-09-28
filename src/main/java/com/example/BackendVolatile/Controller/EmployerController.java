package com.example.BackendVolatile.Controller;

import com.example.BackendVolatile.dto.employerDTO.*;
import com.example.BackendVolatile.service.EmployerService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.employeeVO.CooperatingListVO;
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
