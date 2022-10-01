package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.adminDTO.BrowserTaskDetailDTO;
import com.example.BackendVolatile.dto.adminDTO.SetAlgorithmDTO;
import com.example.BackendVolatile.dto.adminDTO.SetChangeablePeriodDTO;
import com.example.BackendVolatile.dto.adminDTO.SetRecommendRuleDTO;
import com.example.BackendVolatile.service.AdminService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.adminVO.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    //管理员查看所有任务列表
    @GetMapping(value = "/browserAllTasks")
    @UserLoginToken
    public BrowserAllTasksVO browserAllTasks(){
        return adminService.browserAllTasks();
    }

    //管理员浏览任务细节
    @GetMapping(value = "/browserTaskDetail")
    @UserLoginToken
    public BrowserTaskDetailVO browserTaskDetail(@Valid BrowserTaskDetailDTO browserTaskDetailDTO){
        return adminService.browserTaskDetail(browserTaskDetailDTO);
    }


    //管理员设置任务推荐规则
    @PostMapping(value = "/setRecommendRule")
    @UserLoginToken
    public SetRecommendRuleVO setRecommendRule(@Valid @RequestBody SetRecommendRuleDTO setRecommendRuleDTO){
        return adminService.setRecommendRule(setRecommendRuleDTO);
    }

    //管理员查看任务推荐规则
    @GetMapping(value = "/getRecommendRule")
    @UserLoginToken
    public GetRecommendRuleVO getRecommendRule(){
        return adminService.getRecommendRule();
    }

    //管理员设置相似度计算算法
    @PostMapping(value = "/setAlgorithm")
    @UserLoginToken
    public SetAlgorithmVO setAlgorithm(@Valid @RequestBody SetAlgorithmDTO setAlgorithmDTO){
        return adminService.setAlgorithm(setAlgorithmDTO);
    }

    //管理员查看任务推荐规则
    @GetMapping(value = "/getAlgorithm")
    @UserLoginToken
    public GetAlgorithmVO getAlgorithm(){
        return adminService.getAlgorithm();
    }


    //管理员查看报告可修改时间
    @GetMapping(value = "/getChangeablePeriod")
    @UserLoginToken
    public GetChangeablePeriodVO getChangeablePeriod(){
        return adminService.getChangeablePeriod();
    }

    //管理员修改报告可修改时间
    @PostMapping(value = "/setChangeablePeriod")
    @UserLoginToken
    public SetChangeablePeriodVO setChangeablePeriod(@RequestBody SetChangeablePeriodDTO setChangeablePeriodDTO){
        return adminService.setChangeablePeriod(setChangeablePeriodDTO);
    }

}
