package com.example.BackendVolatile;

import com.example.BackendVolatile.dao.UserDao.User;
import com.example.BackendVolatile.mapper.report.ReportMapper;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import com.example.BackendVolatile.mapper.user.UserMapper;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


//@MapperScan("com.example.BackendVolatile.mapper")

@SpringBootTest
public class UserDAOTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private TaskMapper taskMapper;

    @BeforeAll
    //为测试提供数据
    public static void init() {
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("end test userDAO");
    }



    @Test
    @Rollback
    @Transactional
    public void insert(){
        User user = new User();
        user.setNick_name("test_name");
        user.setPassword("12345kkkkkqdss");
        user.setPhone_number("123412432");
        user.setRole(1);
        userMapper.insert(user);
        System.out.println("Inserted user id is:" + user.getUser_id());
    }


    /**
     *  测试删除用户是否正确。 正确删除用户还需要删除该用户对应的report和tasks
     */
    @Test
    @Rollback
    @Transactional
    @Disabled
    public void delete(){
        long user_id = 2;
        userMapper.deleteById(user_id);

        User null_user = userMapper.get_by_id(user_id);
        long task_num_of_deleted_user = taskMapper.get_all_by_user_id_without_paging(user_id).size();
        long report_num_of_deleted_user = reportMapper.get_all_by_user_id(user_id).size();
        Assertions.assertEquals(null, null_user);
        Assertions.assertEquals(0, task_num_of_deleted_user);
        Assertions.assertEquals(0, report_num_of_deleted_user);




    }


    /**
     *  更新用户密码
     *  其他的update方法，如更新phone_number，与本方法是同构的，我就不写了
     */
    @Test
    @Rollback
    @Transactional
    @Disabled
    public void updatePassword(){
        String password = "new_passwd";
        long user_id_to_be_updated = 8;
        userMapper.update_password(user_id_to_be_updated,password);
        Assertions.assertEquals(userMapper.get_by_id(user_id_to_be_updated).getPassword(), password);

    }

    @Test
    @Rollback
    @Disabled
    public void get_by_id()
    {
        long user_id = 3;
        User user = userMapper.get_by_id(user_id);
        Assertions.assertEquals("2345", user.getPassword() );
    }
}
