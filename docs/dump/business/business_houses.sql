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
-- Table structure for table `houses`
--

DROP TABLE IF EXISTS `houses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `houses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `edit_date` datetime DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `approval_building_date` varchar(255) DEFAULT NULL,
  `bathroom` int(11) NOT NULL,
  `building_use` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `contract_code` int(11) NOT NULL,
  `court_code` varchar(255) DEFAULT NULL,
  `floor` int(11) NOT NULL,
  `house_code` int(11) NOT NULL,
  `maintenance_list` varchar(255) DEFAULT NULL,
  `realtor_id` varchar(255) DEFAULT NULL,
  `room` int(11) NOT NULL,
  `square_meter` double NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  `supply_area_meter` double NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_floor` int(11) NOT NULL,
  `weekly_view_count` int(11) NOT NULL,
  `house_option_id` bigint(20) DEFAULT NULL,
  `monthly_info_id` bigint(20) DEFAULT NULL,
  `sale_info_id` bigint(20) DEFAULT NULL,
  `yearly_info_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ag212meg4haaubuy10thgadf` (`house_option_id`),
  KEY `FKd1rcmbdjavtnpv2jc9ccn2qtn` (`monthly_info_id`),
  KEY `FKl63eb8dm8b6qjyvuhx3rinash` (`sale_info_id`),
  KEY `FKhv5cgftpg74cnu9aecu2ov48n` (`yearly_info_id`),
  CONSTRAINT `FK2ag212meg4haaubuy10thgadf` FOREIGN KEY (`house_option_id`) REFERENCES `house_options` (`id`),
  CONSTRAINT `FKd1rcmbdjavtnpv2jc9ccn2qtn` FOREIGN KEY (`monthly_info_id`) REFERENCES `monthly_infos` (`id`),
  CONSTRAINT `FKhv5cgftpg74cnu9aecu2ov48n` FOREIGN KEY (`yearly_info_id`) REFERENCES `yearly_infos` (`id`),
  CONSTRAINT `FKl63eb8dm8b6qjyvuhx3rinash` FOREIGN KEY (`sale_info_id`) REFERENCES `sale_infos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `houses`
--

LOCK TABLES `houses` WRITE;
/*!40000 ALTER TABLE `houses` DISABLE KEYS */;
INSERT INTO `houses` VALUES (23,'2023-08-13 17:48:53','2023-08-13 16:24:28','서울시 역삼','2022-01-15',2,'주거용','아마',1,'1168010100',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'최고의 매물!!!!!!!',4,0,1,51,NULL,NULL),(24,'2023-08-13 17:15:25','2023-08-13 17:15:25','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010600',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,2,43,NULL,NULL),(25,'2023-08-13 17:15:47','2023-08-13 17:15:47','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168011800',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,3,44,NULL,NULL),(26,'2023-08-13 17:24:42','2023-08-13 17:24:42','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010400',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,4,45,NULL,NULL),(27,'2023-08-13 17:30:45','2023-08-13 17:30:45','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010500',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,5,46,NULL,NULL),(28,'2023-08-13 17:36:55','2023-08-13 17:36:55','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010700',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,6,47,NULL,NULL),(29,'2023-08-13 17:45:14','2023-08-13 17:45:14','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010100',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,7,48,NULL,NULL),(30,'2023-08-13 17:50:05','2023-08-13 17:49:39','서울시 역삼','2022-01-15',2,'주거용','아마',1,'1168010100',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'최고의 매물!!!!!!!',4,0,8,53,NULL,NULL),(31,'2023-08-13 18:41:27','2023-08-13 17:53:07','서울시 역삼','2022-01-15',2,'주거용','아마',1,'1168011800',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'최고의 매물!!!!!!!',4,0,9,58,NULL,NULL),(32,'2023-08-13 17:57:40','2023-08-13 17:57:40','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168011800',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,10,57,NULL,NULL),(33,'2023-08-13 18:41:31','2023-08-13 18:41:31','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010400',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,11,59,NULL,NULL),(34,'2023-08-14 14:07:54','2023-08-14 14:07:54','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010400',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,12,60,NULL,NULL),(35,'2023-08-14 14:09:38','2023-08-14 14:09:19','서울시 역삼','2022-01-15',2,'주거용','아마',1,'1168010700',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'최고의 매물!!!!!!!',4,0,13,62,NULL,NULL),(36,'2023-08-16 10:01:23','2023-08-16 10:01:23','서울시 역삼','2022-01-15',2,'주거용','월세 300000',1,'1168010700',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'고시원 원룸',4,0,14,63,NULL,NULL),(38,'2023-08-16 05:52:46','2023-08-16 05:48:18','서울시 역삼','2022-01-15',2,'주거용','아마',1,'1168010100',3,2,NULL,'r_000000000000',1,75.2,'TRADING',80,'최고의 매물!!!!!!!',4,0,16,66,NULL,NULL);
/*!40000 ALTER TABLE `houses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:11
