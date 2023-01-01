package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.serviceImpl.ReportServiceImpl;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AugmentedReportsVO {
    private ResultVO response;

    private List<ReportServiceImpl.AugmentedReport> augmentedReports;
}
