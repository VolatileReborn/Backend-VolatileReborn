package com.example.BackendVolatile.vo.reportVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetCooperationTreeVO {
    private Long name;
    private String value;
    private List<TreeNode> children;

    public GetCooperationTreeVO(){
        this.children = new ArrayList<>();
    }

}


