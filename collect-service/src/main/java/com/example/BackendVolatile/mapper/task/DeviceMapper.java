package com.example.BackendVolatile.mapper.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import javax.validation.groups.Default;

@Validated(Default.class)
@Mapper
public interface DeviceMapper {
    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.android = true")
    Integer get_num_of_tasks_can_be_finished_by_android();

    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.ios = true")
    Integer get_num_of_tasks_can_be_finished_by_ios();

    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.linux = true")
    Integer get_num_of_tasks_can_be_finished_by_linux();

    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.android = true AND t.task_state = #{recruiting}")
    Integer get_num_of_recruiting_tasks_can_be_finished_by_android(@Param(value = "recruiting") Integer recruitingValue);

    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.ios = true AND t.task_state = #{recruiting}")
    Integer get_num_of_recruiting_tasks_can_be_finished_by_ios(@Param(value = "recruiting") Integer recruitingValue);

    @Select("SELECT count(t.task_id) FROM VR_task.tasks AS t WHERE t.linux = true AND t.task_state = #{recruiting}")
    Integer get_num_of_recruiting_tasks_can_be_finished_by_linux(@Param(value = "recruiting") Integer recruitingValue);

    @Select("SELECT count(u.user_id) FROM VR_user.users AS u WHERE u.device = #{device}")
    Integer get_user_num_of_device(@Param(value = "device") String deviceName);

}
