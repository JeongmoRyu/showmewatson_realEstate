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
-- Table structure for table `house_options`
--

DROP TABLE IF EXISTS `house_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `air_conditioner` bit(1) NOT NULL,
  `closet` bit(1) NOT NULL,
  `cold_heating` bit(1) NOT NULL,
  `desk` bit(1) NOT NULL,
  `elevator` bit(1) NOT NULL,
  `induction` bit(1) NOT NULL,
  `parking` bit(1) NOT NULL,
  `refrigerator` bit(1) NOT NULL,
  `shoe_closet` bit(1) NOT NULL,
  `sink` bit(1) NOT NULL,
  `washing_machine` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_options`
--

LOCK TABLES `house_options` WRITE;
/*!40000 ALTER TABLE `house_options` DISABLE KEYS */;
INSERT INTO `house_options` VALUES (1,'\0','','\0','','','\0','','\0','','',''),(2,'\0','','\0','','','\0','','\0','','',''),(3,'\0','','','','','','','\0','','',''),(4,'\0','','','','','','','\0','','',''),(5,'\0','','','','','','','\0','','',''),(6,'\0','','','','','','','\0','','',''),(7,'\0','','','','','','','\0','','',''),(8,'\0','','','','','','','\0','','',''),(9,'\0','','','','','','','\0','','',''),(10,'\0','','','','','','','\0','','',''),(11,'\0','','','','','','','\0','','',''),(12,'\0','','\0','','','\0','','\0','','',''),(13,'\0','','\0','','','\0','','\0','','',''),(14,'\0','','\0','','','\0','','\0','','',''),(15,'\0','','\0','','','\0','','\0','','',''),(16,'\0','','\0','','','\0','','\0','','','');
/*!40000 ALTER TABLE `house_options` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:07
