-- MariaDB dump 10.19  Distrib 10.6.5-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: VR_user
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

DROP TABLE IF EXISTS  `login_logs`;
CREATE TABLE `login_logs` (
    `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `role` tinyint(5) NOT NULL ,
    `login_time` bigint(40) NOT NULL,
    `logAin_event` varchar(256) NOT NULL,
    PRIMARY KEY (`login_id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`user_id`) ON DELETE CASCADE
)ENGINE=InnoDB UTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nick_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` tinyint(5) NOT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `professional_skill` varchar(256) COLLATE utf8mb4_unicode_ci,
  `device` varchar(256) COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

#插入管理员
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(1, '00000000000', 'ADMIN1', 2, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
#插入employer
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(11, '00000000001', 'EMPLOYER1', 0, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(12, '00000000002', 'EMPLOYER2', 0, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(13, '00000000003', 'EMPLOYER3', 0, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(14, '00000000004', 'EMPLOYER4', 0, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(15, '00000000005', 'EMPLOYER5', 0, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');

#插入employee
INSERT INTO users (user_id, phone_number, nick_name, role, password, professional_skill, device) VALUES
(21, '10000000001', 'EMPLOYEE1', 1, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a','擅长功能测试, 熟悉众测规则', 'android');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(22, '10000000002', 'EMPLOYEE2', 1, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password, professional_skill, device) VALUES
(23, '10000000003', 'EMPLOYEE3', 1, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a','啥也不会', 'linux');
INSERT INTO users (user_id, phone_number, nick_name, role, password) VALUES
(24, '10000000004', 'EMPLOYEE4', 1, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a');
INSERT INTO users (user_id, phone_number, nick_name, role, password, professional_skill, device) VALUES
(25, '10000000005', 'EMPLOYEE5', 1, 'ce6634e667a716809fd88421756096e0ae7bedbd793c42d49d6ac56c8d569f1a','对于linux系统下的各种测试较为熟悉', 'linux');



DROP TABLE IF EXISTS `task_favors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_favors`(
    `task_favor` varchar(256) NOT NULL,
    `user_id` bigint(20) NOT NULL ,
    FOREIGN KEY(`user_id`) REFERENCES users(`user_id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO task_favors (task_favor, user_id) VALUES
('功能测试任务', 21),
('功能测试任务', 22),
('功能测试任务', 23),
('功能测试任务', 24),
('功能测试任务', 25),
('性能测试任务', 21),
('性能测试任务', 22),
('性能测试任务', 23),
('性能测试任务', 24),
('性能测试任务', 25);
--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-04 17:12:20
