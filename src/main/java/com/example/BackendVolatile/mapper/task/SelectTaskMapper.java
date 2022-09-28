package com.example.BackendVolatile.mapper.task;

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

/**
 *
 */
@Validated(Default.class)
public interface SelectTaskMapper {

    @Insert("INSERT INTO select_task ( task_id ,user_id) " +
            "VALUES (#{taskId}, #{userId}) ")
    void insert( @Param("taskId") Long taskId,@Param("userId") Long userId );

    @NotNull
    @Select("SELECT count(1) FROM select_task where task_id=#{taskId} AND user_id=#{userId} ")
    Integer is_selected(@Param("taskId") Long taskId, @Param("userId") Long userId );

    @NotNull
    @Min(value = 1,message = ParamFormatErrorConstant.DO_NOT_SELECT_TASK)
    @Select("SELECT count(1) FROM select_task where task_id=#{taskId} AND user_id=#{userId} ")
    Integer assert_is_selected(@Param("taskId") Long taskId, @Param("userId") Long userId );

    @NotNull
    @Max(value = 0,message = ParamFormatErrorConstant.YOU_HAVE_SELECTED_THE_TASK)
    @Select("SELECT count(1) FROM select_task where task_id=#{taskId} AND user_id=#{userId} ")
    Integer assert_is_not_selected(@Param("taskId") Long taskId, @Param("userId") Long userId );

    @NotNull(message = ParamFormatErrorConstant.REPORT_DO_NOT_EXIST)
    @Select("SELECT task_id FROM select_task WHERE user_id = #{userId}")
    List<Long> select_task_id_by_userid(@Param("userId") Long userId);


    @Select("SELECT user_id FROM select_task WHERE task_id = #{task_id}")
    List<Long> get_user_id_by_task_id(@Param("task_id") Long taskId);


}
