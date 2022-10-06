package com.example.BackendVolatile.vo.employerVO;

import com.example.BackendVolatile.vo.ResultVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BrowserCompositeTasksVO {
    private ResultVO response;
    /**
     * 一页的复合任务状态信息列表，按照复合任务发布时间降序排序
     * @see CompositeTaskStateVO
     */
    private List<CompositeTaskStateVO> compositeTaskStateList;

    // 当前（此发包方发布过的）复合任务总数
    private Integer currSumSize;
}
