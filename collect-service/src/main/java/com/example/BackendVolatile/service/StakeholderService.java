package com.example.BackendVolatile.service;

import com.example.BackendVolatile.vo.stakeholder.BrowserDevicesVO;
import com.example.BackendVolatile.vo.stakeholder.BrowserEmployeesVO;
import com.example.BackendVolatile.vo.stakeholder.BrowserEmployersVO;

public interface StakeholderService {
    BrowserEmployersVO getEmployerStates(Integer pageNum, Integer pageSize);

    BrowserEmployeesVO getEmployeeStates(Integer pageNum, Integer pageSize);

    BrowserDevicesVO getDeviceStates(Integer pageNum, Integer pageSize);
}
