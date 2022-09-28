package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.employerDTO.*;
import com.example.BackendVolatile.vo.employerVO.*;

public interface EmployerService {
    BrowserUndertakingTasksVO browserUndertakingTasks();

    BrowserFinishedTasksVO browserFinishedTasks();

    BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO);

    BrowserCheckedVO browserChecked(BrowserCheckedDTO browserCheckedDTO);

    ReportDetailVO reportDetail(ReportDetailDTO reportDetailDTO);

    CooperationReportDetailVO cooperationReportDetail(CooperationReportDetailDTO cooperationReportDetailDTO);


}
