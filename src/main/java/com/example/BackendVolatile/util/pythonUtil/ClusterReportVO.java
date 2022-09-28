package com.example.BackendVolatile.util.pythonUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClusterReportVO {
    private Long report_id;
    private Long cluster_id;
    private List<Double> coordinate;

}
