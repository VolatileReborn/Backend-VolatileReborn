package com.example.BackendVolatile.mapper.report;

import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface CooperationMapper {


    @Insert("INSERT INTO cooperations ( report_id ,user_id, cooperation_state) " +
            "VALUES (#{report_id}, #{user_id}, #{cooperation_state}) ")
    void insert(@Param("report_id") Long reportId, @Param("user_id") Long user_id,
                @Param("cooperation_state") Integer cooperation_report_state);

    @NotNull
    @Select("SELECT count(1) FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer is_cooperated(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull
    @Select("SELECT cooperation_state FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer get_cooperation_report_state(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull
    @Min(value = 1, message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT cooperation_state FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer assert_cooperation_report_submitted(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull
    @Max(value = 0, message = ParamFormatErrorConstant.YOU_HAVE_SUBMITTED_THE_REPORT)
    @Select("SELECT cooperation_state FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer assert_cooperation_report_not_submitted(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull
    @Min(value = 1, message = ParamFormatErrorConstant.DO_NOT_COOPERATED_THE_REPORT)
    @Select("SELECT count(1) FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer assert_is_cooperated(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull
    @Max(value = 0, message = ParamFormatErrorConstant.HAVE_COOPERATED_THE_REPORT)
    @Select("SELECT count(1) FROM cooperations where report_id=#{report_id} AND user_id=#{user_id} ")
    Integer assert_is_not_cooperated(@Param("report_id") Long report_id, @Param("user_id") Long user_id );

    @NotNull(message = ParamFormatErrorConstant.COOPERATION_REPORT_DO_NOT_EXIST)
    @Select("SELECT report_id FROM cooperations WHERE user_id = #{user_id}")
    List<Long> select_report_id_by_user_id(@Param("user_id") Long user_id);


    @NotNull(message = ParamFormatErrorConstant.COOPERATION_REPORT_DO_NOT_EXIST)
    @Select("SELECT report_id FROM cooperations WHERE user_id = #{user_id} AND cooperation_state = #{state}")
    List<Long> select_report_id_by_user_id_and_report_state(@Param("user_id") Long user_id, @Param("state") Integer state);

//    @Select("SELECT report_id FROM cooperations WHERE user_id = #{user_id}")
//    List<Long> select_task_by_task_id(@Param("user_id") Long user_id);

    @Update("UPDATE cooperations SET cooperation_state = #{state} " +
            "WHERE user_id = #{user_id} AND report_id = #{report_id}")
    void update_report_state_by_user_id_and_report_id(@Param("state") Integer state, @Param("report_id") Long report_id,
                                               @Param("user_id") Long user_id);


}
