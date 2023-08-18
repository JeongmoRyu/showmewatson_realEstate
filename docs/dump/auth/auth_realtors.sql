-- MariaDB dump 10.19  Distrib 10.4.31-MariaDB, for Win64 (AMD64)
--
-- Host: I9A803.p.ssafy.io    Database: auth
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
-- Table structure for table `realtors`
--

DROP TABLE IF EXISTS `realtors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realtors` (
  `id` varchar(255) NOT NULL,
  `auth_id` varchar(255) NOT NULL,
  `auth_type` varchar(255) NOT NULL,
  `fcm_token` varchar(255) NOT NULL,
  `is_banned` bit(1) NOT NULL,
  `is_deleted` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `reg_date` datetime(6) NOT NULL,
  `role` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `agency_img` varchar(255) NOT NULL,
  `agency_name` varchar(255) NOT NULL,
  `license` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `realtor_name` varchar(255) NOT NULL,
  `regist_id` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fix26hvmp0vths90vg7f52mai` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realtors`
--

LOCK TABLES `realtors` WRITE;
/*!40000 ALTER TABLE `realtors` DISABLE KEYS */;
INSERT INTO `realtors` VALUES ('r_000000000000','kakao_2967018631','kakao','tmpFcmToken','\0','\0','password','2023-08-16 00:00:00.000000','realtor','서울시 강남구 역삼동','img','사피공인중개사무소','12345','010-1234-1234','박지영','12345','02-1234-1234');
/*!40000 ALTER TABLE `realtors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:12
