package com.example.BackendVolatile.vo.stakeholder;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;

import java.util.List;

@Data
public class BrowserEmployersVO {
    private ResultVO response;
    private List<EmployerStateVO> employerList;
    private Integer currSumSize;
}
