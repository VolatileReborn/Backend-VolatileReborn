package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.squareDTO.EmployeeTaskDetailDTO;
import com.example.BackendVolatile.dto.squareDTO.TaskDetailDTO;
import com.example.BackendVolatile.vo.squareVO.BrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.VisitorBrowserTasksVO;
import com.example.BackendVolatile.vo.squareVO.EmployeeTaskDetailVO;
import com.example.BackendVolatile.vo.squareVO.TaskDetailVO;

public interface SquareService {

    VisitorBrowserTasksVO visitorBrowserTasks();

    BrowserTasksVO browserTasks();

    EmployeeTaskDetailVO employeeTaskDetail(EmployeeTaskDetailDTO employeeTaskDetailDTO);

    TaskDetailVO taskDetail(TaskDetailDTO employerTaskTetailDTO);


}
