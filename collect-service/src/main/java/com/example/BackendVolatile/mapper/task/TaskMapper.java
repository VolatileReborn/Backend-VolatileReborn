package com.example.BackendVolatile.mapper.task;

import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

/**
 * Author: lyk
 * 对应数据库中的task s和taskInfo表
 */
@Validated(Default.class)
public interface TaskMapper {

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT task_id FROM tasks")
    List<Long> get_all_task_id();

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT * FROM tasks WHERE task_id = #{ task_id }")
    Task get_by_task_id(@Param("task_id") Long task_id );

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT task_name FROM tasks WHERE task_id = #{ task_id }")
    String get_name_by_task_id(@Param("task_id") Long task_id );

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT count(1) FROM tasks WHERE task_id = #{ task_id }")
    Integer task_id_exist(@Param("task_id") Long task_id );

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT task_state FROM tasks WHERE task_id = #{ task_id }")
    Integer get_state_by_task_id(@Param("task_id") Long task_id );

    @NotNull(message = ParamFormatErrorConstant.NOT_YOUR_TASK)
    @Select("SELECT * FROM tasks WHERE task_id = #{ task_id } AND user_id = #{user_id}")
    Task get_by_task_id_and_user_id(@Param("task_id") Long task_id, @Param("user_id") Long user_id );


    /**
     * 得到所有的任务，用于任务广场
     * 分页显示
     * @param offset
     * @param maxResults
     * @return
     */
    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks LIMIT #{offset}, #{maxResults}" )
    List<Task> get_all(@Param("offset") Integer offset, @Param("maxResults") Integer maxResults );

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks")
    List<Task> get_all_without_paging();

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks WHERE task_state = #{state}")
    List<Task> get_one_state_all_tasks_without_paging(@Param("state") Integer state);

    /**
     * 得到该用户的所有任务
     * 分页
     * @param user_id
     * @return
     */
    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks WHERE user_id=#{user_id} LIMIT #{maxResults} OFFSET #{offset}" )
    List<Task> get_all_by_user_id(
            @Param("user_id")Long user_id, @Param("offset") Integer offset, @Param("maxResults") Integer maxResults );

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks WHERE user_id=#{user_id}" )
    List<Task> get_all_by_user_id_without_paging( @Param("user_id")Long user_id );

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("SELECT * FROM tasks WHERE user_id=#{user_id} and task_state = #{state}" )
    List<Task> get_one_state_tasks_by_user_id_without_paging(@Param("user_id")Long user_id,@Param("state")Integer state);

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT COUNT(1) FROM tasks WHERE task_id = #{task_id}")
    Integer task_id_in_it(@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Min(value = 0, message = ParamFormatErrorConstant.UNCLEAR_WORKER_NUMBER_LEFT)
    @Select("SELECT worker_num_left FROM tasks WHERE task_id = #{task_id}")
    Integer get_worker_num_left_by_task_id(@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT user_id FROM tasks WHERE task_id = #{task_id}")
    Long get_user_id_by_task_id(@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.TASK_DONT_EXIST_OR_NOT_YOUR_TASK)
    @Min(value = 1, message = ParamFormatErrorConstant.TASK_DONT_EXIST_OR_NOT_YOUR_TASK)
    @Select("SELECT COUNT(1) FROM tasks WHERE task_id = #{task_id} AND user_id = #{user_id}")
    Integer get_task_existence_by_user_id_and_task_id(@Param("task_id")Long task_id, @Param("user_id")Long user_id);

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Select("SELECT task_state FROM tasks WHERE task_id = #{task_id}")
    Integer get_task_state_by_task_id(@Param("task_id")Long task_id);

    @NotNull(message = ParamFormatErrorConstant.TASK_DO_NOT_EXIST)
    @Min(value = 1, message = ParamFormatErrorConstant.UNCLEAR_TASK_STATE)
    @Select("SELECT count(1) FROM tasks WHERE task_id = #{task_id} AND task_state = #{task_state}")
    Integer task_is_undertaking(@Param("task_id")Long task_id, @Param("task_state")Integer task_state);//task_state只能为进行中

    @Options(useGeneratedKeys = true, keyProperty = "task_id", keyColumn = "task_id")
    @Insert("INSERT INTO tasks " +
            "( task_type,begin_time,end_time, introduction, worker_num_total, worker_num_left," +
            " user_id, task_state,task_name, task_difficulty,task_urgency, android, ios, linux) " +
            "VALUES (#{task.task_type}, #{task.begin_time},#{task.end_time}, #{ task.introduction }," +
            " #{ task.worker_num_total }, #{ task.worker_num_left }, #{task.user_id}, #{task.task_state}, #{task.task_name}, " +
            "#{task.task_difficulty}, #{task.task_urgency},#{task.android}, #{task.ios}, #{task.linux}); " )
    void insert( @Param("task") Task task );



    @Delete("DELETE FROM tasks where task_id = #{task_id}")
    void delete_by_task_id(@Param("task_id") Long task_id );

    @Delete(" DELETE FROM tasks where  user_id = #{user_id} ")
    void delete_all_by_user_id( @Param("user_id") Long user_id);

    /**
     *   更改任务类型
     */
    @Update( "UPDATE tasks SET task_type = #{ task.task_type } WHERE task_id = #{task.id} " )
    void  update_task_type( @Param("task") Task task );

    @Update( "UPDATE tasks SET begin_time = #{ begin_time } WHERE task_id = #{task_id} " )
    void  update_task_begin_time( @Param("task_id") Long task_id, @Param("begin_time") Long begin_time);

    @Update( "UPDATE tasks SET end_time = #{ end_time } WHERE task_id = #{task_id} " )
    void  update_task_end_time( @Param("task_id") Long task_id, @Param("begin_time") Long end_time);

    @Update( "UPDATE tasks SET task_state = #{ task_state } WHERE task_id = #{task_id} " )
    void  update_task_state( @Param("task_id") Long task_id, @Param("task_state") Integer task_state);


    @Update( "UPDATE tasks" +
            " SET executableFile = #{ executableFile} WHERE task_id = #{task_id} " )
    void  update_task_executableFile( @Param("task_id") Long task_id, @Param("executableFile") String executableFile);

    @Update( "UPDATE task SET executableFile = #{ requirementDiscriptionFile} WHERE task_id = #{task_id} " )
    void  update_task_requirementDiscriptionFile(
            @Param("requirementDiscriptionFile") Long task_id,
            @Param("requirementDiscriptionFile") String requirementDiscriptionFile);

    @Update( "UPDATE tasks" +
            " SET introduction = #{ introduction} WHERE task_id = #{task_id} " )
    void  update_task_introduction( @Param("task_id") Long task_id, @Param("introduction") String introduction);

    @Update( "UPDATE tasks SET worker_num_left = worker_num_left - 1 WHERE task_id = #{task_id}" )
    void  update_task_worker_num_left ( @Param("task_id") Long task_id);


    /**
     * 得到总任务数
     */
    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("select count(*) from tasks")
    Integer count_tasks();

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("select MAX(task_id) from tasks")
    Long max_id();

    /**
     * 得到输入的用户的总任务数
     * @param user_id
     * @return
     */
    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("select count(*) from tasks WHERE user_id = #{user_id};")
    Integer count_tasks_of_user( @Param("user_id") Long user_id);

    @NotNull(message = ParamFormatErrorConstant.NO_TASK_EXIST)
    @Select("select min(id) from tasks")
    Integer get_min_task_id();


}
