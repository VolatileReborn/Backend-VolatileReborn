package com.example.BackendVolatile;


import com.example.BackendVolatile.dao.taskDAO.Task;
import com.example.BackendVolatile.mapper.task.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest

public class TaskMapperTest {

    @Resource
    TaskMapper taskMapper;

//    @Test
//    public void getById(){
//        Task task = taskMapper.get_by_task_id(1L);
////        Assertions.assertEquals(task.getTask_name(),"task1");
//    }
    @Test
    @Rollback(true)
    @Transactional
    @Disabled
    public void testget_all_without_paging(){
        List<Task> taskList = taskMapper.get_all_without_paging();
//        Assertions.assertEquals(0, taskList.size());
//        INSERT INTO `tasks` VALUES (2,1,1,0,'task2',123423,456789,'intro2',20,5);
//          `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
//  `user_id` bigint(20) NOT NULL,
//  `task_type` tinyint(4) NOT NULL,
//  `task_state` tinyint(4) NOT NULL, #add
//  `task_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL, #add
//  `begin_time` bigint(20) NOT NULL,
//  `end_time` bigint(20) NOT NULL,
//  `introduction` text COLLATE utf8mb4_unicode_ci NOT NULL,
//  `worker_num_total` int(11) NOT NULL,
//  `worker_num_left` int(11) NOT NULL,
//        Assertions.assertEquals(taskList.get(0).getEnd_time(),456789);
//        Assertions.assertEquals(taskList.get(2).getTask_name(), "task2");
    }


}
