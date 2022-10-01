package com.example.BackendVolatile.vo.userVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private ResultVO response;
    private String token;
    private String nickname;
    private Integer role;
}
