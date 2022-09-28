package com.example.BackendVolatile.Controller;


import com.example.BackendVolatile.dto.employeeDTO.*;
import com.example.BackendVolatile.service.EmployeeService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.PassToken;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.employeeVO.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
//@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Resource
    EmployeeService employeeService;

    //众包工人浏览自己参与的进行中的任务列表
    @GetMapping(value = "/browserUndertakingTasks")
    @UserLoginToken
    public BrowserUndertakingTasksVO browserUndertakingTasks(){
        return employeeService.browserUndertakingTasks();
    }

    //众包工人查看自己参与的已结束的任务的列表
    @GetMapping(value = "/browserFinishedTasks")
    @UserLoginToken
    public BrowserFinishedTasksVO browserFinishedTasks(){
        return employeeService.browserFinishedTasks();
    }

    //众包工人查看任务细节
    @GetMapping(value = "/browserTaskDetail")
    @UserLoginToken
    public BrowserTaskDetailVO browserTaskDetail(@Valid BrowserTaskDetailDTO browserTaskDetailDTO){
        return employeeService.browserTaskDetail(browserTaskDetailDTO);
    }

    //众包工人上传测试报告
    @PostMapping(value = "/uploadTestReport")
    @UserLoginToken
    public UploadTestReportVO uploadTestReport(@Valid @RequestBody UploadTestReportDTO uploadTestReportDTO){
        return employeeService.uploadTestReport(uploadTestReportDTO);
    }

    //众包工人上传协作测试报告
    @PostMapping(value = "/uploadCooperationReport")
    @UserLoginToken
    public UploadCooperationReportVO uploadCooperationReport(@Valid @RequestBody UploadCooperationReportDTO uploadCooperationReportDTO){
        System.out.println("controller---------------");
        return employeeService.uploadCooperationReport(uploadCooperationReportDTO);
    }

    //众包工人在个人中心查看自己参与的任务的测试报告细节
    @GetMapping(value = "/reportDetail")
    @UserLoginToken
    public ReportDetailVO reportDetail(@Valid ReportDetailDTO reportDetailDTO){
        return employeeService.reportDetail(reportDetailDTO);
    }

    //众包工人在个人中心查看自己的协作报告细节
    @GetMapping(value = "/cooperationReportDetail")
    @UserLoginToken
    public CooperationReportDetailVO cooperationReportDetail(@Valid CooperationReportDetailDTO cooperationReportDetailDTO){
        return employeeService.cooperationReportDetail(cooperationReportDetailDTO);
    }


    //众包工人主动协作报告
    @PutMapping(value = "/cooperateReport")
    @UserLoginToken
    public CooperateReportVO cooperateReport(@Valid CooperateReportDTO cooperateReportDTO){
        return employeeService.cooperateReport(cooperateReportDTO);
    }

    //众包工人在个人中心页面获取协作中（可协作报告列表）（一级报告信息列表）
    @GetMapping(value = "/cooperatingList")
    @UserLoginToken
    public CooperatingListVO cooperatingList(){
        return employeeService.cooperatingList();
    }

    //众包工人个人中心页面获取已协作（我的协作）列表（一级报告信息列表）
    @GetMapping(value = "/browserFinishedCooperation")
    @UserLoginToken
    public CooperatedListVO cooperatedList(){
        return employeeService.cooperatedList();
    }


    @PostMapping(value = "/changeReport")
    @UserLoginToken
    public ChangeReportVO changeReport(@RequestBody ChangeReportDTO changeReportDTO){
        System.out.println("controller:" + changeReportDTO.toString());
        return employeeService.changeReport(changeReportDTO);
    }


    @GetMapping(value = "/getAllMyReport")
    @UserLoginToken
    public GetAllMyReportVO getAllMyReport(GetAllMyReportDTO getAllMyReportDTO){
        return employeeService.getAllMyReport(getAllMyReportDTO);
    }


}
