package com.example.BackendVolatile.service;

import com.example.BackendVolatile.dto.adminDTO.BrowserTaskDetailDTO;
import com.example.BackendVolatile.dto.adminDTO.SetAlgorithmDTO;
import com.example.BackendVolatile.dto.adminDTO.SetChangeablePeriodDTO;
import com.example.BackendVolatile.dto.adminDTO.SetRecommendRuleDTO;
import com.example.BackendVolatile.vo.adminVO.*;

public interface AdminService {

    BrowserAllTasksVO browserAllTasks();

    BrowserTaskDetailVO browserTaskDetail(BrowserTaskDetailDTO browserTaskDetailDTO);

    SetRecommendRuleVO setRecommendRule(SetRecommendRuleDTO setRecommendRuleDTO);

    GetRecommendRuleVO getRecommendRule();

    SetAlgorithmVO setAlgorithm(SetAlgorithmDTO setAlgorithmDTO);

    GetAlgorithmVO getAlgorithm();

    GetChangeablePeriodVO getChangeablePeriod();

    SetChangeablePeriodVO setChangeablePeriod(SetChangeablePeriodDTO setChangeablePeriodDTO);
}
