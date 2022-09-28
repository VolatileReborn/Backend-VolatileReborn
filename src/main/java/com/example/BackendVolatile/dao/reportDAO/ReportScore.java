package com.example.BackendVolatile.dao.reportDAO;

import com.example.BackendVolatile.dto.reportDTO.ScoreReportDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportScore {

    @Setter(AccessLevel.NONE)
    private Long score_id;

    private Long report_id;

    private Long user_id;

    private Integer score;

    private String comment;


    public ReportScore(ScoreReportDTO scoreReportDTO){
        this.comment = scoreReportDTO.getComment();
        this.report_id = scoreReportDTO.getReportId();
        this.score = scoreReportDTO.getScore();
    }

}
