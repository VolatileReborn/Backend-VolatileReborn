-- MariaDB dump 10.19  Distrib 10.6.5-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: volatile_report
-- ------------------------------------------------------
-- Server version	10.6.5-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `report_scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_scores` (
    `score_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `report_id` bigint(20) NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `score` tinyint(5) NOT NULL,
    `comment` VARCHAR(1000) NOT NULL COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (`score_id`),
    FOREIGN KEY (`user_id`) REFERENCES VR_user.users(`user_id`) ON DELETE CASCADE,
    FOREIGN KEY (`report_id`) REFERENCES reports(`report_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `defect_pictures`
--

DROP TABLE IF EXISTS `defect_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `defect_pictures` (
  `picture_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `picture_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `picture_url` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `report_id` bigint(20) NOT NULL,
  PRIMARY KEY (`picture_id`),
  FOREIGN KEY (`report_id`) REFERENCES reports(`report_id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

# INSERT INTO defect_pictures VALUES (1, 'file 1', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 1);
# INSERT INTO defect_pictures VALUES (2, 'file 2', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 1);
# INSERT INTO defect_pictures VALUES (3, 'file 3', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 2);
# INSERT INTO defect_pictures VALUES (4, 'file 4', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 2);
# INSERT INTO defect_pictures VALUES (5, 'file 5', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 2);
# INSERT INTO defect_pictures VALUES (6, 'file 6', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 3);
# INSERT INTO defect_pictures VALUES (7, 'file 7', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 4);
# INSERT INTO defect_pictures VALUES (8, 'file 8', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 5);
# INSERT INTO defect_pictures VALUES (9, 'file 9', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 5);
# INSERT INTO defect_pictures VALUES (10, 'file 10', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 6);
# INSERT INTO defect_pictures VALUES (11, 'file 11', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 7);
# INSERT INTO defect_pictures VALUES (12, 'file 12', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 8);
# INSERT INTO defect_pictures VALUES (13, 'file 13', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 9);
# INSERT INTO defect_pictures VALUES (14, 'file 14', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 10);
# INSERT INTO defect_pictures VALUES (15, 'file 15', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 10);
# INSERT INTO defect_pictures VALUES (16, 'file 16', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 12);
# INSERT INTO defect_pictures VALUES (17, 'file 17', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 13);
# INSERT INTO defect_pictures VALUES (18, 'file 18', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 12);
# INSERT INTO defect_pictures VALUES (19, 'file 19', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 14);
# INSERT INTO defect_pictures VALUES (20, 'file 20', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 15);
# INSERT INTO defect_pictures VALUES (21, 'file 21', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 15);
# INSERT INTO defect_pictures VALUES (22, 'file 22', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 16);
# INSERT INTO defect_pictures VALUES (23, 'file 23', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 16);
# INSERT INTO defect_pictures VALUES (24, 'file 24', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 16);
# INSERT INTO defect_pictures VALUES (25, 'file 25', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 17);
# INSERT INTO defect_pictures VALUES (26, 'file 26', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 17);
# INSERT INTO defect_pictures VALUES (27, 'file 27', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 18);
# INSERT INTO defect_pictures VALUES (28, 'file 28', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 19);
# INSERT INTO defect_pictures VALUES (29, 'file 29', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 19);
# INSERT INTO defect_pictures VALUES (30, 'file 30', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 20);
# INSERT INTO defect_pictures VALUES (31, 'file 31', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 20);
# INSERT INTO defect_pictures VALUES (32, 'file 32', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316164457_8wa4.jpg', 11);

--
-- Dumping data for table `defect_pictures`
--

LOCK TABLES `defect_pictures` WRITE;
/*!40000 ALTER TABLE `defect_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `defect_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `report_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20),
  `report_state` tinyint(4) NOT NULL,
  `defect_explain` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `defect_reproduction_step` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `test_equipment_information` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `similarity` tinyint(8),
  `similar_report_id` bigint(20),
  PRIMARY KEY (`report_id`),
  FOREIGN KEY (`user_id`) REFERENCES VR_user.users(`user_id`) on DELETE CASCADE,
  FOREIGN KEY (`task_id`) REFERENCES VR_task.tasks(`task_id`) on DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


/*!40101 SET character_set_client = @saved_cs_client */;

# INSERT INTO reports VALUES (1, 'report 1',21,0,'explain', 'step', 'equipment_info', 2, 0, 2);
# INSERT INTO reports VALUES (2, 'report 2',21,0,'explain', 'step', 'equipment_info', 3, 35, 2);
# INSERT INTO reports VALUES (3, 'report 3',21,1,'explain', 'step', 'equipment_info', 4, 20, 2);
# INSERT INTO reports VALUES (4, 'report 4',21,1,'explain', 'step', 'equipment_info', 5, 7, 2);
# INSERT INTO reports VALUES (5, 'report 5',21,1,'explain', 'step', 'equipment_info', 9, 66, 2);
# INSERT INTO reports VALUES (6, 'report 6',21,0,'explain', 'step', 'equipment_info', 10, 4, 2);
# INSERT INTO reports VALUES (7, 'report 7',21,1,'explain', 'step', 'equipment_info', 13, 15, 2);
# INSERT INTO reports VALUES (8, 'report 8',21,0,'explain', 'step', 'equipment_info', 20, 8, 2);
#
# #taskid = 8 userid = 22
# INSERT INTO reports VALUES (9, 'report 9',22,1,'explain', 'step', 'equipment_info', 3, 0, 2);
# INSERT INTO reports VALUES (10, 'report 10',22,0,'explain', 'step', 'equipment_info', 4, 0, 2);
# INSERT INTO reports VALUES (11, 'report 11',22,1,'explain', 'step', 'equipment_info', 10, 0, 2);
# INSERT INTO reports VALUES (12, 'report 12',22,0,'explain', 'step', 'equipment_info', 12, 30, 24);
# INSERT INTO reports VALUES (13, 'report 13',22,1,'explain', 'step', 'equipment_info', 13, 0, 2);
# INSERT INTO reports VALUES (14, 'report 14',22,0,'explain', 'step', 'equipment_info', 17, 0, 2);
# INSERT INTO reports VALUES (15, 'report 15',22,0,'explain', 'step', 'equipment_info', 19, 0, 2);
#
# INSERT INTO reports VALUES (16, 'report 16',23,0,'explain', 'step', 'equipment_info', 2, 0, 1);
# INSERT INTO reports VALUES (17, 'report 17',23,1,'explain', 'step', 'equipment_info', 3, 0, 2);
# INSERT INTO reports VALUES (18, 'report 18',23,1,'explain', 'step', 'equipment_info', 8, 0, 2);
# INSERT INTO reports VALUES (19, 'report 19',23,0,'explain', 'step', 'equipment_info', 12, 18, 12);
# INSERT INTO reports VALUES (20, 'report 20',23,0,'explain', 'step', 'equipment_info', 20, 0, 2);
#
# INSERT INTO reports VALUES (21, 'report 21',24,1,'explain', 'step', 'equipment_info', 3, 0, 2);
# INSERT INTO reports VALUES (22, 'report 22',24,0,'explain', 'step', 'equipment_info', 8, 0, 2);
# INSERT INTO reports VALUES (23, 'report 23',24,1,'explain', 'step', 'equipment_info', 10, 0, 2);
# INSERT INTO reports VALUES (24, 'report 24',24,1,'explain', 'step', 'equipment_info', 12, 44, 12);
# INSERT INTO reports VALUES (25, 'report 25',24,0,'explain', 'step', 'equipment_info', 13, 0, 2);
# INSERT INTO reports VALUES (26, 'report 26',24,1,'explain', 'step', 'equipment_info', 19, 0, 2);
# INSERT INTO reports VALUES (27, 'report 27',24,0,'explain', 'step', 'equipment_info', 20, 0, 2);
#
# INSERT INTO reports VALUES (28, 'report 28',25,0,'explain', 'step', 'equipment_info', 3, 0, 2);
# INSERT INTO reports VALUES (29, 'report 29',25,1,'explain', 'step', 'equipment_info', 4, 0, 2);
# INSERT INTO reports VALUES (30, 'report 30',25,1,'explain', 'step', 'equipment_info', 8, 0, 2);
# INSERT INTO reports VALUES (31, 'report 31',25,1,'explain', 'step', 'equipment_info', 9, 0, 2);
# INSERT INTO reports VALUES (32, 'report 32',25,0,'explain', 'step', 'equipment_info', 11, 0, 2);
# INSERT INTO reports VALUES (33, 'report 33',25,1,'explain', 'step', 'equipment_info', 12, 13, 19);

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `cooperation_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cooperation_reports` (
    `report_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `report_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
    `user_id` bigint(20) NOT NULL,
    `report_state` tinyint(4) NOT NULL,
    `defect_explain` text COLLATE utf8mb4_unicode_ci NOT NULL,
    `defect_reproduction_step` text COLLATE utf8mb4_unicode_ci NOT NULL,
    `test_equipment_information` text COLLATE utf8mb4_unicode_ci NOT NULL,
    `task_id` bigint(20) NOT NULL,
    `parent_report_id` bigint(20) not null ,
    PRIMARY KEY (`report_id`),
    FOREIGN KEY (`user_id`) REFERENCES VR_user.users(`user_id`) on DELETE CASCADE,
    FOREIGN KEY (`task_id`) REFERENCES VR_task.tasks(`task_id`) on DELETE CASCADE,
    FOREIGN KEY (`parent_report_id`) REFERENCES reports(`report_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `cooperations`;
CREATE TABLE `cooperations` (
    `cooperation_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL ,
    `report_id` bigint(20) NOT NULL ,
    `cooperation_state` tinyint(5) NOT NULL ,
    PRIMARY KEY (`cooperation_id`),
    FOREIGN KEY (`user_id`) REFERENCES VR_user.users(`user_id`) on delete cascade ,
    FOREIGN KEY (`report_id`) REFERENCES reports(`report_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



DROP TABLE IF EXISTS `cooperation_defect_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cooperation_defect_pictures` (
   `picture_id` bigint(20) NOT NULL AUTO_INCREMENT,
   `picture_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
   `picture_url` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
   `report_id` bigint(20) NOT NULL,
    PRIMARY KEY (`picture_id`),
    FOREIGN KEY (`report_id`) REFERENCES cooperation_reports(`report_id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `algorithms`;
CREATE TABLE `algorithms` (
    admin_id bigint(20) not null,
    `algorithm` varchar(256) COLLATE utf8mb4_unicode_ci  not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `algorithms` (`admin_id`, `algorithm`) VALUES (1, 'CosineSimilarity');

DROP TABLE IF EXISTS `recommendation_rules`;
CREATE TABLE `recommendation_rules` (
    `feature` varchar(256) COLLATE utf8mb4_unicode_ci  not null,
    `classification` varchar(256) COLLATE utf8mb4_unicode_ci  not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `period`;
CREATE TABLE `period` (
    day bigint(20) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `period` VALUES (1);

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-04 16:58:25
