-- MariaDB dump 10.19  Distrib 10.4.31-MariaDB, for Win64 (AMD64)
--
-- Host: I9A803.p.ssafy.io    Database: business
-- ------------------------------------------------------
-- Server version	10.3.38-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `live_schedules`
--

DROP TABLE IF EXISTS `live_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `live_schedules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `house_id` bigint(20) DEFAULT NULL,
  `live_date` datetime DEFAULT NULL,
  `realtor_id` varchar(255) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_schedules`
--

LOCK TABLES `live_schedules` WRITE;
/*!40000 ALTER TABLE `live_schedules` DISABLE KEYS */;
INSERT INTO `live_schedules` VALUES (1,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 14:30:00','realtor','2023-08-13 15:30:17'),(2,'매물 라이브 진행해요~~~~~~',24,'2023-08-13 14:30:00','realtor','2023-08-13 15:00:45'),(3,'매물 라이브 진행해요~~~~~~',25,'2023-08-13 14:30:00','realtor','2023-08-13 15:00:00'),(4,'매물 라이브 진행해요~~~~~~',26,'2023-08-13 14:30:00','realtor','2023-08-13 15:30:00'),(5,'매물 라이브 진행해요~~~~~~',26,'2023-08-13 14:30:00','realtor','2023-08-13 15:30:03'),(6,'매물 라이브 진행해요~~~~~~',28,'2023-08-13 16:30:00','realtor','2023-08-13 12:00:24'),(7,'매물 라이브 진행해요~~~~~~',29,'2023-08-13 14:30:00','realtor','2023-08-13 18:00:46'),(14,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 14:30:00','realtor','2023-08-15 07:12:17'),(15,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 14:30:00','realtor','2023-08-15 13:53:28'),(16,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 14:30:00','realtor','2023-08-15 13:53:58'),(17,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 14:30:00','realtor','2023-08-15 23:08:54'),(18,'ㅠㅠㅠㅠㅠㅠ',35,'2023-07-09 10:30:00','realtor','2023-08-16 04:55:53'),(19,'매물 라이브 진행해요~~~~~~',38,'2023-08-13 14:30:00','r_000000000000','2023-08-16 12:51:53'),(20,'매물 라이브 진행해요~~~~~~',23,'2023-08-13 06:01:53','r_000000000000','2023-08-16 13:33:31');
/*!40000 ALTER TABLE `live_schedules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:06
