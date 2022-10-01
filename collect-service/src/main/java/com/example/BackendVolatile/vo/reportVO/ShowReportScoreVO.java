package com.example.BackendVolatile.vo.reportVO;

import com.example.BackendVolatile.dao.reportDAO.ReportScore;
import com.example.BackendVolatile.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowReportScoreVO {

    private String comment;
    private Integer score;
    private ResultVO response;

    public void setPartShowReportScoreVO(ReportScore reportScore){
        this.comment = reportScore.getComment();
        this.score = reportScore.getScore();
    }


}
