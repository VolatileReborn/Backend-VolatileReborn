//package com.example.BackendVolatile;
//
//
//import com.example.BackendVolatile.dao.taskDAO.Task;
//import com.example.BackendVolatile.mapper.report.ReportMapper;
//import com.example.BackendVolatile.mapper.task.TaskMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import javax.annotation.Resource;
//
//@SpringBootTest
//public class TaskDAOTest {
//
//    @Resource
//    TaskMapper taskMapper;
//
//    @Resource
//    ReportMapper reportMapper;
//
//
//    /**
//     * 测试插入是否成功， 方法是计算插入后的record数量是否比插入前的大1
//     *
//     */
//    @Test
//    @Rollback(true)
//    @Transactional
//    @Disabled
//    public void insert(){
//
//        int original_task_num = taskMapper.count_tasks();
//        Task task = new Task();
//        task.setTask_name("lykkk");
//        task.setTask_type(0);
//        task.setBegin_time(new Long(1000));
//        task.setEnd_time(new Long(2000));
//        task.setUser_id(2L);
//        task.setIntroduction("test_introductionadasda13werwlykkkk");
//        taskMapper.insert(task);
//
//        int added_task_num = taskMapper.count_tasks();
//        Assertions.assertEquals(original_task_num+1, added_task_num);
//    }
//
//
//    /**
//     *   测试删除是否成功，删除任务会删除该任务对应的报告
//     */
//    @Test
//    @Rollback
//    @Transactional
////    @Disabled
//    public void delete(){
//        long task_id_to_be_deleted = 1;
//
//        taskMapper.delete_by_task_id( task_id_to_be_deleted );
//
//        Task null_task = taskMapper.get_by_task_id(task_id_to_be_deleted);
//        int related_reports_num = reportMapper.count_reports_of_task(task_id_to_be_deleted);
//        Assertions.assertEquals(null, null_task);
//        Assertions.assertEquals( 0, related_reports_num ); //测试报告也应该删除
//    }
//
//    @Test
//    @Rollback
//    @Disabled
//    public void update_begin_time(){
//
//        long task_id_to_be_updated = 5;
////        Timestamp new_begin_time = new Timestamp(System.currentTimeMillis());
////        taskMapper.update_task_begin_time(task_id_to_be_updated, new_begin_time );
//        long new_begin_time = 1000;
//        Task task = taskMapper.get_by_task_id(task_id_to_be_updated);
////        System.out.println(task.getBegin_time().getTime());
//
//        Assertions.assertEquals( new_begin_time, taskMapper.get_by_task_id(task_id_to_be_updated).getBegin_time());
//
//    }
//
//
//
//}
