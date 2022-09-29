package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;

import java.util.List;


@Data
public class GetClusterScatterVO {
    private ResultVO response;

    private Integer clusterCount;

    List<Info> data;

}

