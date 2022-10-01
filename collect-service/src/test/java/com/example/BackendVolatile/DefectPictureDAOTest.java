//package com.example.BackendVolatile;
//
//import com.example.BackendVolatile.dao.reportDAO.DefectPicture;
//import com.example.BackendVolatile.mapper.report.DefectPictureMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
//@SpringBootTest
//public class DefectPictureDAOTest {
//
//    @Resource
//    DefectPictureMapper defectPictureMapper;
//
//    @Test
//    @Rollback
//    @Transactional
////    @Disabled
//    //需要report_id = 9
//    public void insert(){
//        DefectPicture picture = new DefectPicture();
//        picture.setPicture_name("test_picture");
//        picture.setPicture_url("test_url");
//        picture.setReport_id(255L);
//
//        defectPictureMapper.insert(picture);
//    }
//
//}
