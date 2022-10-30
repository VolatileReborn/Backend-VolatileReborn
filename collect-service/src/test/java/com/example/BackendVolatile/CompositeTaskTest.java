package com.example.BackendVolatile;

import com.example.BackendVolatile.dto.employeeDTO.TestReport;
import com.example.BackendVolatile.dto.employeeDTO.UploadTestReportDTO;
import com.example.BackendVolatile.dto.taskDTO.*;
import com.example.BackendVolatile.serviceImpl.*;
import com.example.BackendVolatile.util.ThreadLocalUtils;
import com.example.BackendVolatile.vo.squareVO.BrowserTasksVO;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskServiceImpl.class)
@MapperScan("com.example.*.mapper")
@ContextConfiguration(classes = Config.class)
public class CompositeTaskTest {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    EmployerServiceImpl employerService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    SquareServiceImpl squareService;
}
