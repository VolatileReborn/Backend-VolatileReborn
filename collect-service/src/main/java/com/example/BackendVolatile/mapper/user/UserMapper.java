package com.example.BackendVolatile.mapper.user;
import com.example.BackendVolatile.dao.stakeholder.EmployeeDAO;
import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.dao.stakeholder.EmployerDAO;
import com.example.BackendVolatile.util.constant.ParamFormatErrorConstant;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

/**
 * Author: 陆昱宽
 *
 */
@Validated(Default.class)
@Mapper
public interface UserMapper {

    @NotNull
    @Select("SELECT count(user_id) FROM users WHERE role = #{code}")
    Integer get_employer_size(@Param("code") Integer employer_code);

    @NotNull
    @Select("SELECT count(user_id) FROM users WHERE role = #{code}")
    Integer get_employee_size(@Param("code") Integer employee_code);


    @NotNull
    @Select("SELECT user_id FROM users WHERE role = #{role}")
    List<Long> get_all_user_id_by_role(@Param("role") Integer role);

    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("SELECT * FROM users WHERE user_id = #{ user_id }")
    User get_by_id(@Param("user_id") Long id );

    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("SELECT role FROM users WHERE user_id = #{ user_id }")
    Integer get_role_by_id(@Param("user_id") Long id );

    @NotNull(message = ParamFormatErrorConstant.PHONE_NUMBER_DO_NO_REGISTER)
    @Select("SELECT * FROM users WHERE phone_number = #{ phone_number }")
    User get_by_phone_number(@Param("phone_number") String phone_number );

    @NotNull(message = ParamFormatErrorConstant.LIST_CANNOT_BE_NULL)
    @Select("SELECT * FROM users WHERE role = #{ role }")
    List<User> get_by_role(@Param("role")Integer role );


    @NotNull(message = ParamFormatErrorConstant.NO_USER_EXIST)
    List<EmployeeDAO> get_all_employee(@Param("role") Integer role,
                                       @Param("limit") Integer maxResults,
                                       @Param("offset") Integer offset );

    @Select("SELECT rs.score FROM volatile_report.reports AS r, volatile_report.report_scores AS rs " +
            "WHERE r.report_id = rs.report_id AND r.user_id = #{userId}")
    List<Integer> get_all_scores_of_reports_of_employee(@Param("userId") Long userId);

    @Select("SELECT t.task_difficulty FROM volatile_task.select_task AS st, volatile_task.tasks AS t " +
            "WHERE st.task_id = t.task_id AND st.user_id = #{userId}")
    List<Integer> get_all_difficulty_of_tasks_of_employee(@Param("userId") Long userId);

    @Select("SELECT t.task_type AS type FROM volatile_task.select_task AS st, volatile_task.tasks AS t " +
            "WHERE st.task_id = t.task_id AND st.user_id = #{userId}")
    List<String> get_all_type_of_tasks_of_employee(@Param("userId") Long userId);


    @NotNull(message = ParamFormatErrorConstant.NO_USER_EXIST)
    List<EmployerDAO> get_all_employer(@Param("role") Integer role,
                                       @Param("limit") Integer maxResults,
                                       @Param("offset") Integer offset );

    @Select("SELECT task_difficulty FROM volatile_task.tasks AS t WHERE t.user_id = #{userId}")
    List<Integer> get_all_difficulty_of_tasks_of_employer(@Param("userId") Long userId);

    @Select("SELECT task_type FROM volatile_task.tasks AS t WHERE t.user_id = #{userId}")
    List<String> get_all_type_of_tasks_of_employer(@Param("userId") Long userId);


    @NotNull
    @Min(value = 1, message = ParamFormatErrorConstant.WRONG_PASSWORD)
    @Select("SELECT count(1) FROM users WHERE phone_number = #{phone_number} AND password = #{password}")
    Integer right_password(@Param("phone_number") String phone_number, @Param("password") String password );

    @NotNull(message = ParamFormatErrorConstant.NO_USER_EXIST)
    @Select("SELECT * FROM users LIMIT #{limit} OFFSET #{offset}" )
    List<User> get_all(@Param("limit") Integer maxResults, @Param("offset") Integer offset );

    @NotNull(message = ParamFormatErrorConstant.NO_USER_EXIST)
    @Select("SELECT * FROM users" )
    List<User> get_all_without_paging();



    @Max(value = 0,message = ParamFormatErrorConstant.PHONE_NUMBER_ALREADY_REGISTERED)
    @NotNull
    @Select("SELECT COUNT(1) FROM users WHERE phone_number = #{phone_number}")
    Integer phone_number_dont_exist(@Param("phone_number") String phone_number);

    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    @Insert("INSERT INTO users ( phone_number, password, role, nick_name ) VALUES (#{user.phone_number}, #{user.password}, #{user.role}, #{user.nick_name}) ")
    void insert( @Param("user") User user );

    @Update( "UPDATE users SET nick_name = #{ nick_name } WHERE user_id = #{user_id}; " )
    void update_nick_name(@Param("user_id") Long  user_id, @Param("nick_name") String nick_name );

    @Update( "UPDATE users SET device = #{ device } WHERE user_id = #{user_id}; " )
    void update_device(@Param("user_id") Long  user_id, @Param("device") String device );

    @Update( "UPDATE users SET professional_skill = #{ professional_skill } WHERE user_id = #{user_id}; " )
    void update_professional_skill(@Param("user_id") Long  user_id, @Param("professional_skill") String professional_skill );

    @Update( "UPDATE users SET password = #{ password } WHERE user_id = #{user_id}; " )
    void update_password( @Param("user_id") Long user_id  ,@Param("password") String password );

    @Update("UPDATE users SET phone_number = #{ phone_number } WHERE user_id = #{user_id}; ")
    void update_phone_number(@Param("user_id") Long user_id  ,@Param("phone_number") String phone_number  );

    /**
     * 会连带删除与该用户的report和task
     * @Author: lyk
     * @param id
     */
    @Delete("DELETE FROM users where user_id = #{id}")
    void deleteById( @Param("id") Long id );


    /**
     * 得到输入的用户的总报告数
     * @param user_id: 众包工人的id
     * @return
     */
    @NotNull(message = ParamFormatErrorConstant.USER_DO_NOT_EXIST)
    @Select("select count(*) from reports WHERE user_id = #{user_id};")
    Integer count_reports_of_user( @Param("user_id") Long user_id);

}
