package com.example.BackendVolatile;

import com.example.BackendVolatile.dao.reportDAO.Report;
import com.example.BackendVolatile.mapper.report.ReportMapper;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ReportDAOTest {

    @Resource
    ReportMapper reportMapper;


    @BeforeAll
    //定义了整个测试类在开始前以及结束时的操作，只能修饰静态方法，主要用于在测试过程中所需要的全局数据和外部资源的初始化和清理
    public static void init() {
        System.out.println("Begin testing ReportDAO");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("End testing ReportDAO");
    }


    @Test
    @Rollback
    @Transactional
    @Disabled
    public void insert(){

        long original_report_num = reportMapper.count_all();
        Report report = new Report();
        report.setUser_id(1L);
        report.setTask_id(2L);
        report.setDefect_explain("test_explain");
        report.setDefect_reproduction_step("test_reproduction_step");
        report.setTest_equipment_information("test_equip_inf");
        report.setReport_name("name");

        reportMapper.insert(report);
        long inserted_report_num = reportMapper.count_all();
        Assertions.assertEquals( original_report_num + 1, inserted_report_num );
    }

    @Test
    @Rollback
    @Transactional
    @Disabled
    public void delete(){
        long report_id_to_be_deleted = 5;
        //待被删除的报告的user_id
        long belonged_user_id = reportMapper.get_by_report_id(report_id_to_be_deleted).getUser_id();
//        System.out.println(belonged_user_id);
        reportMapper.delete_by_report_id( report_id_to_be_deleted );
        List<Report> reports_of_the_user = reportMapper.get_all_by_user_id(belonged_user_id);
        for( Report report: reports_of_the_user )
        {
            Assertions.assertNotEquals( report.getReport_id(), report_id_to_be_deleted );
        }

    }

    @Test
    @Rollback
    @Disabled
    public void delete_all_by_user_id()
    {
//        long user_id = 2;
//        reportMapper.delete_all_by_user_id( user_id );
//        List<Report> reports_of_the_user = reportMapper.get_all_by_user_id(user_id, 0, 10);
//        Assertions.assertEquals(0,reports_of_the_user.size());
        long report_id = 5;
        long get_userId = reportMapper.get_user_id_by_report_id(5L);
        System.out.println(get_userId);
        Assertions.assertEquals(get_userId,15);
//

    }
}
