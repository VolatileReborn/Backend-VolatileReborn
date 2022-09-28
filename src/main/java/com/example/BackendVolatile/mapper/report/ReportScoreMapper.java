package com.example.BackendVolatile.mapper.report;

import com.example.BackendVolatile.dao.reportDAO.ReportScore;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface ReportScoreMapper {

    @NotNull(message = ParamFormatErrorConstant.REPORT_SCORE_DO_NOT_EXIST)
    @Select("SELECT * FROM report_scores WHERE score_id = #{score_id}")
    ReportScore get_by_score_id(@Param("score_id") Long score_id);

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT * FROM report_scores WHERE report_id = #{report_id}")
    List<ReportScore> get_by_report_id(@Param("report_id") Long report_id);

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT * FROM report_scores WHERE user_id = #{user_id}")
    List<ReportScore> get_by_user_id(@Param("user_id") Long user_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_SCORE_EXIST)
    @Select("SELECT * FROM report_scores WHERE user_id = #{user_id} AND report_id = #{report_id}")
    ReportScore get_by_user_id_and_report_id(@Param("user_id") Long user_id, @Param("report_id") Long report_id);

    @Select("SELECT count(1) FROM report_scores WHERE user_id = #{user_id} AND report_id = #{report_id}")
    Integer score_in_it_by_user_id_and_report_id(@Param("user_id") Long user_id, @Param("report_id") Long report_id);

    @NotNull(message = ParamFormatErrorConstant.NO_REPORT_SCORE_EXIST)
    @Select("SELECT count(1) FROM report_scores WHERE user_id = #{user_id} AND report_id = #{report_id}")
    @Max(value = 0, message = ParamFormatErrorConstant.YOU_HAVE_SCORED_THE_REPORT)
    Integer assert_have_not_submitted_report_score_by_user_id_and_report_id(@Param("user_id") Long user_id, @Param("report_id") Long report_id);

    @Insert("INSERT INTO report_scores (report_id, user_id, score, comment) VALUES " +
            "( #{reportScore.report_id}, #{reportScore.user_id}, #{reportScore.score}, #{reportScore.comment})")
    void insert(@Param("reportScore") ReportScore reportScore);

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT score from report_scores WHERE report_id = #{report_id}")
    List<Integer> get_score_by_report_id(@Param("report_id") Long report_id);

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT comment from report_scores WHERE report_id = #{report_id}")
    List<String> get_comment_by_report_id(@Param("report_id") Long report_id);
}

