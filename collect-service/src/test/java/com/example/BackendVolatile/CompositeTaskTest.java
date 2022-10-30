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


    //Bug！！
//    @org.junit.Test
//    @Transactional
//    public void publishTest(){
//        ThreadLocalUtils.set("valid",1L);
//        ThreadLocalUtils.set("userId", 11L);
//        SubTaskDTO subTask1=new SubTaskDTO(
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                "intro",1,1,"sub1",4,4,true,false,false
//        );
//        SubTaskDTO subTask2=new SubTaskDTO(
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                "intro",1,1,"sub2",4,4,true,false,false
//        );
//        SubTaskDTO subTask3=new SubTaskDTO(
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                "intro",1,1,"sub3",4,3,true,false,false
//        );
//        SubTaskDTO subTask4=new SubTaskDTO(
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),
//                "intro",1,1,"sub4",4,3,true,false,false
//        );
//
//        //TODO
////        System.out.println(taskService.publishCompositeTask(new CompositeTaskPublishDTO(
////                "name","intro",new ArrayList<>(Arrays.asList(subTask1,subTask2,subTask3,subTask4)),
////                new ArrayList<>(Arrays.asList(
////                        new TaskOrderPairDTO(0, 1),
////                        new TaskOrderPairDTO(0,2),
////                        new TaskOrderPairDTO(1,3),
////                        new TaskOrderPairDTO(2,3)
////                ))
////        )));
//
//        System.out.println(employerService.browserCompositeTasks(1,5));
//
//        ThreadLocalUtils.set("userId", 21L);
//        BrowserTasksVO browserTasksVO=squareService.browserTasks();
//        Long subId=browserTasksVO.getTaskList().get(browserTasksVO.getTaskList().size()-1).getTaskId();
//        taskService.acceptTask(new AcceptTaskDTO(subId));
//
//        employeeService.uploadTestReport(new UploadTestReportDTO(subId,new TestReport(
//                "A","A","A",new ArrayList<>(Collections.singletonList(new File("file 1", "https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe"))),"A"
//        )));
//
//        ThreadLocalUtils.set("userId", 11L);
//        System.out.println(employerService.browserCompositeTasks(1,5));
//
//    }
}
