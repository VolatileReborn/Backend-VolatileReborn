package com.example.BackendVolatile.vo.stakeholder;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserEmployeesVO {
    private ResultVO response;
    private List<EmployeeStateVO> employeeList;
    private Integer currSumSize;
}
