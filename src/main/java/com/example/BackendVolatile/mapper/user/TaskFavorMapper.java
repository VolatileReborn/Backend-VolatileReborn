package com.example.BackendVolatile.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskFavorMapper {


    @Select("SELECT task_favor FROM task_favors WHERE user_id = #{ user_id }")
    List<String> get_task_favor_by_user_id(@Param("user_id") Long user_id);

    @Select("SELECT count(1) FROM task_favors WHERE user_id = #{ user_id }")
    Integer task_favor_in_it_by_user_id(@Param("user_id") Long user_id);

    @Select("DELETE FROM task_favors WHERE user_id = #{ user_id }")
    void delete_task_favor_by_user_id(@Param("user_id") Long user_id);

    @Insert("INSERT INTO task_favors (task_favor, user_id) VALUES (#{task_favor}, #{user_id})")
    void insert(@Param("user_id") Long user_id, @Param("task_favor") String task_favor);

}
