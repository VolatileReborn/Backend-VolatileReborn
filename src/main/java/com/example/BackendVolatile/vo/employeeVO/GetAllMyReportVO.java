package com.example.BackendVolatile.vo.employeeVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;

import java.util.List;

@Data
public class GetAllMyReportVO {

    ResultVO response;

    List<ReportBriefInfo> reportList;


}

