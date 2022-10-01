package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.employeeDTO.*;
import com.example.BackendVolatile.vo.employeeVO.*;

import javax.validation.Valid;

public interface EmployeeService {

    BrowserUndertakingTasksVO browserUndertakingTasks();

    BrowserFinishedTasksVO browserFinishedTasks();

    BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO);

    UploadTestReportVO uploadTestReport(UploadTestReportDTO uploadTestReportDTO);

    UploadCooperationReportVO uploadCooperationReport(UploadCooperationReportDTO uploadCooperationReportDTO);

    ReportDetailVO reportDetail(ReportDetailDTO reportDetailDTO);

    CooperationReportDetailVO cooperationReportDetail(CooperationReportDetailDTO cooperationReportDetailDTO);

    CooperateReportVO cooperateReport(CooperateReportDTO cooperateReportDTO);

    CooperatingListVO cooperatingList();

    CooperatedListVO cooperatedList();

    ChangeReportVO changeReport(ChangeReportDTO changeReportDTO);
    GetAllMyReportVO getAllMyReport(GetAllMyReportDTO getAllMyReportDTO);


}
