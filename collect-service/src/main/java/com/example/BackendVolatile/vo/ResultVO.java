package com.example.BackendVolatile.vo;

import com.example.BackendVolatile.util.constant.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {
    private Integer code;
    private String message;


    public ResultVO(ResponseConstant responseConstant){
        this.code = responseConstant.getCode();
        this.message = responseConstant.getMessage();
    }

    public ResultVO(ResponseConstant responseConstant, String message){
        this.code = responseConstant.getCode();
        this.message = message;
    }

}


