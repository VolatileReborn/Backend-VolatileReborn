package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEvaluationVO {
    private ResultVO response;
    private Double evaluationValue;
    private Boolean evaluated;
}
