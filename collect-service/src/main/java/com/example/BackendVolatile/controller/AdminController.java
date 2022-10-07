package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.PageMsgDTO;
import com.example.BackendVolatile.dto.adminDTO.BrowserTaskDetailDTO;
import com.example.BackendVolatile.dto.adminDTO.SetAlgorithmDTO;
import com.example.BackendVolatile.dto.adminDTO.SetChangeablePeriodDTO;
import com.example.BackendVolatile.dto.adminDTO.SetRecommendRuleDTO;
import com.example.BackendVolatile.dto.reportDTO.ReportIdDTO;
import com.example.BackendVolatile.dto.taskDTO.TaskIdDTO;
import com.example.BackendVolatile.service.AdminService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.adminVO.*;
import com.example.BackendVolatile.vo.employerVO.BrowserCompositeTasksVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    /**
     * Iter2新增
     * 系统管理员分页查看系统中的所有复合（可拆分的）任务的状态信息
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

    /**
     * Iter2新增
     * 系统管理员删除指定复合任务
     * @param taskIdDTO 包含指定复合任务id
     * @return 删除结果
     * @see TaskIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/compositeTask/remove")
    @UserLoginToken
    public RemoveVO removeCompositeTask(@Valid @RequestBody TaskIdDTO taskIdDTO){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员批量删除指定复合任务
     * @param taskIdList 指定复合任务id的列表
     * @return 删除结果
     * @see TaskIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/compositeTask/batchRemove")
    @UserLoginToken
    public RemoveVO removeBatchOfCompositeTasks(@NotNull @Valid @RequestBody List<TaskIdDTO> taskIdList){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员删除指定普通任务（或复合任务的子任务）
     * @param taskIdDTO 包含指定任务id
     * @return 删除结果
     * @see TaskIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/task/remove")
    @UserLoginToken
    public RemoveVO removeTask(@Valid @RequestBody TaskIdDTO taskIdDTO){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员批量删除指定普通任务（或复合任务的子任务）
     * @param taskIdList 指定任务id的列表
     * @return 删除结果
     * @see TaskIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/task/batchRemove")
    @UserLoginToken
    public RemoveVO removeBatchOfTasks(@NotNull @Valid @RequestBody List<TaskIdDTO> taskIdList){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员分页查看系统中的所有报告
     * @param pageMsgDTO 分页查询信息，包含页号及页大小
     * @return 一页的报告简略信息列表，按照报告id顺序降序排序
     * @see PageMsgDTO
     * @see BrowserReportsVO
     */
    @GetMapping(value = "/browserReports")
    @UserLoginToken
    public BrowserReportsVO browserReports(@Valid @RequestParam("taskIdList")PageMsgDTO pageMsgDTO){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员删除指定报告
     * @param reportIdDTO 包含指定报告id
     * @return 删除结果
     * @see ReportIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/report/remove")
    @UserLoginToken
    public RemoveVO removeReport(@Valid @RequestBody ReportIdDTO reportIdDTO){
        return null;
    }

    /**
     * Iter2新增
     * 系统管理员批量删除指定报告
     * @param reportIdList 指定报告id的列表
     * @return 删除结果
     * @see ReportIdDTO
     * @see RemoveVO
     */
    @PostMapping(value = "/report/batchRemove")
    @UserLoginToken
    public RemoveVO removeBatchOfReports(@NotNull @Valid @RequestBody List<ReportIdDTO> reportIdList){
        return null;
    }



    //管理员查看所有普通任务列表
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
