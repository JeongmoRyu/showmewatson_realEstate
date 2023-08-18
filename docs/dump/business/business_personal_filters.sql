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
-- Table structure for table `personal_filters`
--

DROP TABLE IF EXISTS `personal_filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_filters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `house_code` int(11) DEFAULT NULL,
  `contract_code` int(11) DEFAULT NULL,
  `court_code` varchar(255) DEFAULT NULL,
  `min_square_meter` double DEFAULT NULL,
  `max_square_meter` double DEFAULT NULL,
  `min_deposit` bigint(20) DEFAULT NULL,
  `max_deposit` bigint(20) DEFAULT NULL,
  `min_maintenance` int(11) DEFAULT NULL,
  `max_maintenance` int(11) DEFAULT NULL,
  `min_monthly_rent` bigint(20) DEFAULT NULL,
  `max_monthly_rent` bigint(20) DEFAULT NULL,
  `min_sale_price` bigint(20) DEFAULT NULL,
  `max_sale_price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_filters`
--

LOCK TABLES `personal_filters` WRITE;
/*!40000 ALTER TABLE `personal_filters` DISABLE KEYS */;
INSERT INTO `personal_filters` VALUES (3,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(4,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(6,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(7,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(8,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(9,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(10,'admin',0,0,NULL,0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(11,'admin',1,1,'1110000000',0,0,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(12,'admin',1,1,'1110000000',50,100,500,1000,0,0,30,50,NULL,NULL),(13,'admin',1,1,'1110000000',50,100,500,1000,0,0,30,50,NULL,NULL),(14,'admin',1,1,'1110000000',50,100,500,1000,0,0,30,50,NULL,NULL),(15,'admin',1,1,'1110000000',50,100,500,1000,0,0,30,50,NULL,NULL),(16,'u_000000000000',1,1,'1110000000',50,100,500,1000,0,0,30,50,NULL,NULL);
/*!40000 ALTER TABLE `personal_filters` ENABLE KEYS */;
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
