package com.example.BackendVolatile.vo.adminVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAlgorithmVO {

    private ResultVO response;

    private String rule;
}
