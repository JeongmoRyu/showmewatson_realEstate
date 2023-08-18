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
-- Table structure for table `monthly_infos`
--

DROP TABLE IF EXISTS `monthly_infos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_infos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deposit` bigint(20) DEFAULT NULL,
  `maintenance` int(11) NOT NULL,
  `maintenance_list` varchar(255) DEFAULT NULL,
  `monthly_rent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_infos`
--

LOCK TABLES `monthly_infos` WRITE;
/*!40000 ALTER TABLE `monthly_infos` DISABLE KEYS */;
INSERT INTO `monthly_infos` VALUES (3,1000,9,'에어컨 다있음',30),(4,1000,9,'에어컨 다있음',30),(5,1000,9,'에어컨 다있음',30),(6,1000,10,'에어컨 다있음',30),(7,1000,10,'에어컨 다있음',30),(8,1000,10,'에어컨 다있음',30),(9,1000,3,'에어컨 다있음',30),(15,1000,3,'에어컨 다있음',30),(16,1000,3,'복도 관리, 분리수거장 관리',20),(17,1000,3,'복도 관리, 분리수거장 관리',20),(18,1000,3,'복도 관리, 분리수거장 관리',20),(19,3000,3,'복도 관리, 분리수거장 관리',20),(20,3000,3,'복도 관리, 분리수거장 관리',20),(21,3000,3,'복도 관리, 분리수거장 관리',20),(22,3000,3,'복도 관리, 분리수거장 관리',20),(32,3000,10,'에어컨 다있음',30),(33,3000,10,'에어컨 다있음',30),(34,2000,9,'에어컨 다있음',30),(35,2000,9,'에어컨 다있음',30),(37,2000,9,'에어컨 다있음',30),(41,2000,9,'에어컨 다있음',20),(42,2000,9,'에어컨 다있음',20),(43,2000,10,'에어컨 다있음',20),(44,2000,7,'에어컨 다있음',80),(45,2000,7,'에어컨 다있음',80),(46,2000,7,'에어컨 다있음',80),(47,3000,9,'에어컨 다있음',80),(48,3000,10,'에어컨 다있음',80),(49,2500,7,'복도 관리, 분리수거장 관리',50),(50,2500,7,'복도 관리, 분리수거장 관리',50),(51,2500,7,'복도 관리, 분리수거장 관리',50),(52,3000,10,'에어컨 다있음',60),(53,5000,0,'관리비 없음',50),(54,3000,10,'에어컨 다있음',60),(55,5000,0,'관리비 없음',50),(56,5000,0,'관리비 없음',50),(57,3000,10,'에어컨 다있음',60),(58,5000,0,'관리비 없음',50),(59,3000,9,'에어컨 다있음',60),(60,1000,9,'에어컨 다있음',30),(61,1000,9,'에어컨 다있음',30),(62,2000,0,'관리비 없음',50),(63,3000,9,'에어컨 다있음',30),(64,30000,7,'에어컨 다있음',30),(65,300,5,'에어컨 다있음',60),(66,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `monthly_infos` ENABLE KEYS */;
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
