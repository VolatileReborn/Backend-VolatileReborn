package com.example.BackendVolatile.util.pythonUtil.GetSimilarReportUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetSimilarReportsVO {
    public Long report_id;
    public Double similarity;
}