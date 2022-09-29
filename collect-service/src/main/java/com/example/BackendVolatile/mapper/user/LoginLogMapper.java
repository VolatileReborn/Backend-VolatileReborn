package com.example.BackendVolatile.mapper.user;


import com.example.BackendVolatile.dao.UserDao.LoginLog;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@Validated(Default.class)
public interface LoginLogMapper {

    @NotNull(message = ParamFormatErrorConstant.LOGIN_LOG_DO_NOT_EXIST)
    @Select("SELECT * FROM login_logs WHERE login_id = #{login_id}")
    LoginLog get_by_login_id(@Param("login_id") Long login_id);


    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("SELECT * FROM login_logs WHERE user_id = #{user_id}")
    List<LoginLog> get_by_user_id(@Param("user_id") Long user_id);

    @Insert("INSERT INTO login_logs (user_id, role, login_time, login_event) VALUES" +
            " (#{login_log.user_id}, #{login_log.role}, #{login_log.login_time}, #{login_log.login_event})")
    void insert(@Param("login_log") LoginLog loginLog);

    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("SELECT login_time FROM login_logs WHERE user_id = #{user_id}")
    List<Long> get_login_time_list_by_user_id(@Param("user_id") Long user_id);

    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("SELECT login_time FROM login_logs WHERE user_id = #{user_id} and login_time > #{login_time}")
    List<Long> get_login_time_list_by_user_id_with_limit(@Param("user_id") Long user_id,
                                                         @Param("login_time") Long login_time);



}
