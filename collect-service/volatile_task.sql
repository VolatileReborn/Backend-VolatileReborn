-- MariaDB dump 10.19  Distrib 10.6.5-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: VR_task
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



--
-- Table structure for table `requirement_description_files`
--

DROP TABLE IF EXISTS `requirement_description_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirement_description_files` (
    `file_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `file_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
    `file_url` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `task_id` bigint(20) NOT NULL,
    PRIMARY KEY (`file_id`),
    FOREIGN KEY(`task_id`) REFERENCES tasks(`task_id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO requirement_description_files VALUES (1, 'file 1', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 1);
INSERT INTO requirement_description_files VALUES (2, 'file 2', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 1);
INSERT INTO requirement_description_files VALUES (3, 'file 3', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 2);
INSERT INTO requirement_description_files VALUES (4, 'file 4', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 2);
INSERT INTO requirement_description_files VALUES (5, 'file 5', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 2);
INSERT INTO requirement_description_files VALUES (6, 'file 6', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 3);
INSERT INTO requirement_description_files VALUES (7, 'file 7', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 4);
INSERT INTO requirement_description_files VALUES (8, 'file 8', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 5);
INSERT INTO requirement_description_files VALUES (9, 'file 9', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 5);
INSERT INTO requirement_description_files VALUES (10, 'file 10', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 6);
INSERT INTO requirement_description_files VALUES (11, 'file 11', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 7);
INSERT INTO requirement_description_files VALUES (12, 'file 12', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 8);
INSERT INTO requirement_description_files VALUES (13, 'file 13', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 9);
INSERT INTO requirement_description_files VALUES (14, 'file 14', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 10);
INSERT INTO requirement_description_files VALUES (15, 'file 15', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 10);
INSERT INTO requirement_description_files VALUES (16, 'file 16', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 12);
INSERT INTO requirement_description_files VALUES (17, 'file 17', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 13);
INSERT INTO requirement_description_files VALUES (18, 'file 18', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 12);
INSERT INTO requirement_description_files VALUES (19, 'file 19', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 14);
INSERT INTO requirement_description_files VALUES (20, 'file 20', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 15);
INSERT INTO requirement_description_files VALUES (21, 'file 21', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 15);
INSERT INTO requirement_description_files VALUES (22, 'file 22', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 16);
INSERT INTO requirement_description_files VALUES (23, 'file 23', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 16);
INSERT INTO requirement_description_files VALUES (24, 'file 24', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 16);
INSERT INTO requirement_description_files VALUES (25, 'file 25', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 17);
INSERT INTO requirement_description_files VALUES (26, 'file 26', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 17);
INSERT INTO requirement_description_files VALUES (27, 'file 27', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 18);
INSERT INTO requirement_description_files VALUES (28, 'file 28', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 19);
INSERT INTO requirement_description_files VALUES (29, 'file 29', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 19);
INSERT INTO requirement_description_files VALUES (30, 'file 30', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 20);
INSERT INTO requirement_description_files VALUES (31, 'file 31', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161905_ms7n.pdf', 20);




--
-- Dumping data for table `requirement_description_files`
--

LOCK TABLES `requirement_description_files` WRITE;
/*!40000 ALTER TABLE `requirement_description_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement_description_files` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `executable_files`
--

DROP TABLE IF EXISTS `executable_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `executable_files` (
    `executable_file_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `executable_file_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
    `executable_file_url` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `task_id` bigint(20) NOT NULL,
    PRIMARY KEY (`executable_file_id`),
    FOREIGN KEY(`task_id`) REFERENCES tasks(`task_id`) on DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


INSERT INTO executable_files VALUES (1, 'file 1', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 1);
INSERT INTO executable_files VALUES (2, 'file 2', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 1);
INSERT INTO executable_files VALUES (3, 'file 3', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 2);
INSERT INTO executable_files VALUES (4, 'file 4', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 2);
INSERT INTO executable_files VALUES (5, 'file 5', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 2);
INSERT INTO executable_files VALUES (6, 'file 6', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 3);
INSERT INTO executable_files VALUES (7, 'file 7', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 4);
INSERT INTO executable_files VALUES (8, 'file 8', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 5);
INSERT INTO executable_files VALUES (9, 'file 9', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 5);
INSERT INTO executable_files VALUES (10, 'file 10', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 6);
INSERT INTO executable_files VALUES (11, 'file 11', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 7);
INSERT INTO executable_files VALUES (12, 'file 12', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 8);
INSERT INTO executable_files VALUES (13, 'file 13', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 9);
INSERT INTO executable_files VALUES (14, 'file 14', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 10);
INSERT INTO executable_files VALUES (15, 'file 15', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 10);
INSERT INTO executable_files VALUES (16, 'file 16', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 12);
INSERT INTO executable_files VALUES (17, 'file 17', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 13);
INSERT INTO executable_files VALUES (19, 'file 19', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 14);
INSERT INTO executable_files VALUES (20, 'file 20', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 15);
INSERT INTO executable_files VALUES (21, 'file 21', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 15);
INSERT INTO executable_files VALUES (22, 'file 22', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 16);
INSERT INTO executable_files VALUES (23, 'file 23', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 16);
INSERT INTO executable_files VALUES (24, 'file 24', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 16);
INSERT INTO executable_files VALUES (25, 'file 25', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 17);
INSERT INTO executable_files VALUES (26, 'file 26', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 17);
INSERT INTO executable_files VALUES (27, 'file 27', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 18);
INSERT INTO executable_files VALUES (28, 'file 28', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 19);
INSERT INTO executable_files VALUES (29, 'file 29', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 19);
INSERT INTO executable_files VALUES (30, 'file 30', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 20);
INSERT INTO executable_files VALUES (31, 'file 31', 'https://se3-volatile.oss-cn-beijing.aliyuncs.com/files/20220316/20220316161846_6ztn.exe', 20);

--
-- Dumping data for table `executable_files`
--

LOCK TABLES `executable_files` WRITE;
/*!40000 ALTER TABLE `executable_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `executable_files` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `executable_files`
--

DROP TABLE IF EXISTS `select_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `select_task` (
    `user_id` bigint(20) NOT NULL,
    `task_id` bigint(20) NOT NULL,
    FOREIGN KEY(`task_id`) REFERENCES tasks(`task_id`) ON DELETE CASCADE ,
    FOREIGN KEY(`user_id`) REFERENCES `VR_user`.users(`user_id`) on delete cascade

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO select_task VALUES (21, 2);
INSERT INTO select_task VALUES (21, 3);
INSERT INTO select_task VALUES (21, 4);
INSERT INTO select_task VALUES (21, 5);
INSERT INTO select_task VALUES (21, 9);
INSERT INTO select_task VALUES (21, 10);
INSERT INTO select_task VALUES (21, 13);
INSERT INTO select_task VALUES (21, 20);

INSERT INTO select_task VALUES (22, 3);
INSERT INTO select_task VALUES (22, 4);
INSERT INTO select_task VALUES (22, 8);
INSERT INTO select_task VALUES (22, 10);
INSERT INTO select_task VALUES (22, 12);
INSERT INTO select_task VALUES (22, 13);
INSERT INTO select_task VALUES (22, 15);
INSERT INTO select_task VALUES (22, 17);
INSERT INTO select_task VALUES (22, 19);
INSERT INTO select_task VALUES (22, 20);

INSERT INTO select_task VALUES (23, 2);
INSERT INTO select_task VALUES (23, 3);
INSERT INTO select_task VALUES (23, 4);
INSERT INTO select_task VALUES (23, 5);
INSERT INTO select_task VALUES (23, 8);
INSERT INTO select_task VALUES (23, 12);
INSERT INTO select_task VALUES (23, 17);
INSERT INTO select_task VALUES (23, 20);

INSERT INTO select_task VALUES (24, 3);
INSERT INTO select_task VALUES (24, 4);
INSERT INTO select_task VALUES (24, 6);
INSERT INTO select_task VALUES (24, 8);
INSERT INTO select_task VALUES (24, 10);
INSERT INTO select_task VALUES (24, 12);
INSERT INTO select_task VALUES (24, 13);
INSERT INTO select_task VALUES (24, 17);
INSERT INTO select_task VALUES (24, 19);
INSERT INTO select_task VALUES (24, 20);

INSERT INTO select_task VALUES (25, 3);
INSERT INTO select_task VALUES (25, 4);
INSERT INTO select_task VALUES (25, 5);
INSERT INTO select_task VALUES (25, 8);
INSERT INTO select_task VALUES (25, 9);
INSERT INTO select_task VALUES (25, 11);
INSERT INTO select_task VALUES (25, 12);
INSERT INTO select_task VALUES (25, 18);
INSERT INTO select_task VALUES (25, 19);
INSERT INTO select_task VALUES (25, 20);
--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `task_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `task_type` tinyint(5) NOT NULL,
  `task_state` tinyint(5) NOT NULL,
  `task_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `begin_time` bigint(40) NOT NULL,
  `end_time` bigint(40) NOT NULL,
  `introduction` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `worker_num_total` int(10) NOT NULL,
  `worker_num_left` int(10) NOT NULL,
  `task_difficulty` tinyint(5) NOT NULL,
  `task_urgency` tinyint(5) NOT NULL,
  `android` bit NOT NULL,
  `ios` bit NOT NULL,
  `linux` bit NOT NULL,
  PRIMARY KEY (`task_id`),
  FOREIGN KEY(`user_id`) REFERENCES VR_user.users(`user_id`) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

、INSERT INTO tasks VALUES (7, 12, 1, 1, 'Task 7', 1643569600000, 1651161600000, 'task 7 introduction', 15, 15, 1, 3, true, false, true);
INSERT INTO tasks VALUES (1, 11, 0, 0, 'Task 1', 1648569600000, 1651161600000, 'task 1 introduction', 5, 5, 2, 3, true, false, true);

INSERT INTO tasks VALUES (3, 11, 1, 1, 'Task 3', 1648669600000, 1651361600000, 'task 3 introduction', 50, 45, 1, 1, true, true, true);
INSERT INTO tasks VALUES (4, 13, 0, 1, 'Task 4', 1648569600000, 1651161600000, 'task 4 introduction', 23, 18, 5, 5, true, false, false);
INSERT INTO tasks VALUES (5, 13, 1, 0, 'Task 5', 1642569600000, 1651161600000, 'task 5 introduction', 3, 0, 4, 3, true, false, false);
INSERT INTO tasks VALUES (6, 11, 0, 0, 'Task 6', 1648569600000, 1651161600000, 'task 6 introduction', 1, 0, 2, 2, true, true, true);
INSERT INTO tasks VALUES (18, 15, 1, 1, 'Task 18', 1647569600000, 1651163200000, 'task 18 introduction', 6, 5, 2, 4, false, true, true);
INSERT INTO tasks VALUES (8, 14, 1, 0, 'Task 8', 1648569600000, 1651161600000, 'task 8 introduction', 8, 4, 2, 4, false, true, false);
INSERT INTO tasks VALUES (9, 13, 1, 0, 'Task 9', 1638569600000, 1651161600000, 'task 9 introduction', 20, 18, 5, 3, false, true, true);
INSERT INTO tasks VALUES (20, 12, 0, 1, 'Task 20', 1648069600000, 1651168600000, 'task 20 introduction', 25, 20, 5, 2, true, true, false);
INSERT INTO tasks VALUES (10, 11, 0, 1, 'Task 10', 1648529600000, 1651261600000, 'task 10 introduction', 13, 10, 4, 2, true, false, false);
INSERT INTO tasks VALUES (11, 13, 0, 1, 'Task 11', 1648568100000, 1651191600000, 'task 11 introduction', 65, 64, 1, 5, false, true, false);
INSERT INTO tasks VALUES (12, 11, 0, 1, 'Task 12', 1647769600000, 1653361600000, 'task 12 introduction', 52, 48, 3, 3, true, false, true);
INSERT INTO tasks VALUES (13, 11, 1, 0, 'Task 13', 1648429600000, 1651841600000, 'task 13 introduction', 39, 36, 4, 3, true, false, true);
INSERT INTO tasks VALUES (14, 12, 0, 0, 'Task 14', 1645369600000, 1652161600000, 'task 14 introduction', 77, 77, 2, 1, false, true, false);
INSERT INTO tasks VALUES (15, 11, 1, 1, 'Task 15', 1648564600000, 1651771600000, 'task 15 introduction', 16, 15, 2, 5, false, true, true);
INSERT INTO tasks VALUES (16, 14, 0, 0, 'Task 16', 1648169600000, 1656661600000, 'task 16 introduction', 20, 20, 1, 1, false, false, true);
INSERT INTO tasks VALUES (17, 12, 0, 0, 'Task 17', 1648562600000, 1651111600000, 'task 17 introduction', 8, 5, 3, 3, true, true, false);
INSERT INTO tasks VALUES (2, 12, 1, 1, 'Task 2', 1649569600000, 1650161600000, 'task 2 introduction', 15, 13, 4, 1, false, false, true);
INSERT INTO tasks VALUES (19, 11, 1, 0, 'Task 19', 1648521600000, 1655161600000, 'task 19 introduction', 44, 41, 4, 4, true, false, false);


--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-04 17:12:36
