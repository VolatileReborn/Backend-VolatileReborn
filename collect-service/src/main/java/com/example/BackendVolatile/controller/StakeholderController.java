package com.example.BackendVolatile.controller;

import com.example.BackendVolatile.dto.PageMsgDTO;
import com.example.BackendVolatile.service.StakeholderService;
import com.example.BackendVolatile.util.jwtUtil.CustomAnnotation.UserLoginToken;
import com.example.BackendVolatile.vo.stakeholder.BrowserDevicesVO;
import com.example.BackendVolatile.vo.stakeholder.BrowserEmployeesVO;
import com.example.BackendVolatile.vo.stakeholder.BrowserEmployersVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/stakeholder")
public class StakeholderController {

    @Resource
    private StakeholderService stakeholderService;

    /**
     * 系统管理员浏览当前发包方用户的状态
     * @param pageMsgDTO 分页查询信息，包含页号及页大小
     * @return 发包方用户状态列表，按照用户id升序排序
     * @see com.example.BackendVolatile.vo.stakeholder.EmployerStateVO
     */
    @GetMapping("/stateList/employer")
    @UserLoginToken
    public BrowserEmployersVO getEmployerStates(@Valid PageMsgDTO pageMsgDTO) {
        return stakeholderService.getEmployerStates(pageMsgDTO.getPageNum(), pageMsgDTO.getPageSize());
    }

    /**
     * 系统管理员浏览当前众包工人用户的状态
     * @param pageMsgDTO 分页查询信息，包含页号及页大小
     * @return 发包方用户状态列表，按照用户id升序排序
     * @see com.example.BackendVolatile.vo.stakeholder.EmployeeStateVO
     */
    @GetMapping("/stateList/employee")
    @UserLoginToken
    public BrowserEmployeesVO getEmployeeStates(@Valid PageMsgDTO pageMsgDTO) {
        return stakeholderService.getEmployeeStates(pageMsgDTO.getPageNum(), pageMsgDTO.getPageSize());
    }

    /**
     * 系统管理员浏览当前测试设备的状态
     * 目前只是最简单实现了一下，之后需要重构
     * @param pageMsgDTO 分页查询信息，包含页号及页大小
     * @return 设备状态列表
     * @see com.example.BackendVolatile.vo.stakeholder.DeviceStateVO
     */
    @GetMapping("/stateList/device")
    @UserLoginToken
    public BrowserDevicesVO getDeviceStates(@Valid PageMsgDTO pageMsgDTO) {
        return stakeholderService.getDeviceStates(pageMsgDTO.getPageNum(), pageMsgDTO.getPageSize());
    }
}
