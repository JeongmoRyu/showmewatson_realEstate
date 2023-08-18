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
-- Table structure for table `house_files`
--

DROP TABLE IF EXISTS `house_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `edit_date` datetime DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `house_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe5jbq37dji9q4nbghurrhc5di` (`house_id`),
  CONSTRAINT `FKe5jbq37dji9q4nbghurrhc5di` FOREIGN KEY (`house_id`) REFERENCES `houses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_files`
--

LOCK TABLES `house_files` WRITE;
/*!40000 ALTER TABLE `house_files` DISABLE KEYS */;
INSERT INTO `house_files` VALUES (44,'2023-08-13 17:15:25','2023-08-13 17:15:25','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/9b851c4d-c42c-4248-b395-74f9e47e1bcf.jpg',0,24),(45,'2023-08-13 17:15:47','2023-08-13 17:15:47','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/5ce25efd-e800-405b-8ea0-41a7c3760d3a.jpg',0,25),(46,'2023-08-13 17:24:42','2023-08-13 17:24:42','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/a564a54a-f413-413c-895a-8b2cc034e9f4.jpeg',0,26),(47,'2023-08-13 17:30:45','2023-08-13 17:30:45','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/329d4944-8f41-45ef-b594-4d28a3448cc8.jpeg',0,27),(48,'2023-08-13 17:30:45','2023-08-13 17:30:45','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/3c5ee4bb-de09-486d-a7ed-365acabe0313.jpeg',0,27),(49,'2023-08-13 17:36:55','2023-08-13 17:36:55','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/3245f7a2-e163-49ec-a80b-9dd33c7878e2.jpeg',0,28),(50,'2023-08-13 17:36:55','2023-08-13 17:36:55','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/28a5dab3-581f-433b-8192-b076f51d074c.jpeg',0,28),(51,'2023-08-13 17:45:14','2023-08-13 17:45:13','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/fc1266c3-ec16-435c-97ac-c24eeb4272bb.jpeg',0,29),(52,'2023-08-13 17:45:14','2023-08-13 17:45:13','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/4ae5948a-de5c-420a-9edd-e7af28ea402e.jpeg',0,29),(55,'2023-08-13 17:48:54','2023-08-13 17:48:54','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/72950245-fed4-4460-bc81-f126af2f68b3.jpeg',0,23),(56,'2023-08-13 17:48:54','2023-08-13 17:48:54','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/4819a8d8-a856-45ff-8a8c-50c26a007bcd.jpeg',0,23),(59,'2023-08-13 17:50:05','2023-08-13 17:50:05','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/df4b3b99-382a-4d05-b3a6-a43ed7044402.jpeg',0,30),(65,'2023-08-13 17:57:41','2023-08-13 17:57:40','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/270b1c15-120a-4c17-ad30-f665281a7968.jpg',0,32),(66,'2023-08-13 18:41:28','2023-08-13 18:41:28','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/bff3cb74-600a-446c-93c8-f998645819ae.jpeg',0,31),(67,'2023-08-13 18:41:28','2023-08-13 18:41:28','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/0752ba45-496f-4584-8eec-71b49ab47979.jpeg',0,31),(68,'2023-08-13 18:41:31','2023-08-13 18:41:31','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/9bd85a00-0f47-4e70-b145-a3fc8f408036.jpg',0,33),(69,'2023-08-14 14:07:54','2023-08-14 14:07:53','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/a7e8fcc6-e897-4986-bfa6-6c33f5cfc876.jpg',0,34),(70,'2023-08-14 14:07:54','2023-08-14 14:07:54','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/ec55e158-0c20-4648-9628-a748e64ef15b.png',0,34),(73,'2023-08-14 14:09:38','2023-08-14 14:09:38','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/2654963e-68c6-4a45-bd1d-371ca940abed.jpg',0,35),(74,'2023-08-16 10:01:23','2023-08-16 10:01:23','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/c6f61642-a0f3-4c7a-a476-7d327f59bc65.jpg',0,36),(75,'2023-08-16 10:01:23','2023-08-16 10:01:23','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/cf3a39ea-f01a-4714-adaf-b33f3bde158c.png',0,36),(78,'2023-08-16 05:52:47','2023-08-16 05:52:47','https://watson-house.s3.ap-northeast-2.amazonaws.com/house/7fc1195a-4ac4-452a-96ac-b9ff8a2d4c1d.jpeg',0,38);
/*!40000 ALTER TABLE `house_files` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:10
