package com.example.BackendVolatile.userTest;


import com.example.BackendVolatile.mapper.user.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@SpringBootTest
@Transactional
public class UserDaoTest {
    @Resource
    UserMapper userMapper;

    @BeforeAll
    public static void init(){
        System.out.println("开始对UserDao进行测试");
    }

    @AfterAll
    public static void finished(){
        System.out.println("UserDao测试完成");
    }
//
//    @Test
//    public void
}
