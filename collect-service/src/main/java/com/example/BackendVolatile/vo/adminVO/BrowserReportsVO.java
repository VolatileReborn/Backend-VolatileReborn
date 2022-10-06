package com.example.BackendVolatile.vo.adminVO;

import com.example.BackendVolatile.vo.ResultVO;
import com.example.BackendVolatile.vo.employeeVO.ReportBriefInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserReportsVO {
    private ResultVO response;
    /**
     * 一页的报告简略信息列表，按报告id降序排序
     * @see ReportBriefInfo
     */
    private List<ReportBriefInfo> reportList;
    // 当前系统中的报告总数
    private Integer currSumSize;
}
