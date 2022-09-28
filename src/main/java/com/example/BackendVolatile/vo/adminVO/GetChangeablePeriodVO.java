package com.example.BackendVolatile.vo.adminVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetChangeablePeriodVO {
    private ResultVO response;

    private Integer changeablePeriod;
}
