-- MariaDB dump 10.19  Distrib 10.4.31-MariaDB, for Win64 (AMD64)
--
-- Host: I9A803.p.ssafy.io    Database: log
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
-- Table structure for table `view_log`
--

DROP TABLE IF EXISTS `view_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  `log_date` varchar(45) DEFAULT NULL,
  `donglee_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view_log`
--

LOCK TABLES `view_log` WRITE;
/*!40000 ALTER TABLE `view_log` DISABLE KEYS */;
INSERT INTO `view_log` VALUES (1,'test',1,'2023-08-07 04:06:24','37'),(2,'test',1,'2023-08-09','창천동'),(3,'test',2,'2023-08-09','창천동'),(4,'test',2,'2023-08-10','창천동'),(5,'test',1,'2023-08-13','창천동'),(6,'test',14,'2023-08-13','창천동'),(7,'test',2,'2023-08-13','창천동'),(8,'test',22,'2023-08-13','창천동'),(9,'test',23,'2023-08-13','창천동'),(10,'test',18,'2023-08-13','창천동'),(11,'test',15,'2023-08-13','창천동'),(12,'test',24,'2023-08-13','창천동'),(13,'test',25,'2023-08-13','창천동'),(14,'test',26,'2023-08-13','창천동'),(15,'test',28,'2023-08-13','창천동'),(16,'test',29,'2023-08-13','창천동'),(17,'test',30,'2023-08-13','창천동'),(18,'test',31,'2023-08-13','창천동'),(19,'test',32,'2023-08-13','창천동'),(20,'test',32,'2023-08-14','창천동'),(21,'test',30,'2023-08-14','창천동'),(22,'test',23,'2023-08-14','창천동'),(23,'test',33,'2023-08-14','창천동'),(24,'test',31,'2023-08-14','창천동'),(25,'test',34,'2023-08-14','창천동'),(26,'test',35,'2023-08-14','창천동'),(27,'test',25,'2023-08-14','창천동'),(28,'test',27,'2023-08-14','창천동'),(29,'test',24,'2023-08-14','창천동'),(30,'test',26,'2023-08-14','창천동'),(31,'test',28,'2023-08-14','창천동'),(32,'test',29,'2023-08-14','창천동'),(33,'test',23,'2023-08-15','창천동'),(34,'test',28,'2023-08-15','창천동'),(35,'test',24,'2023-08-15','창천동'),(36,'test',25,'2023-08-15','창천동'),(37,'test',26,'2023-08-15','창천동'),(38,'test',29,'2023-08-15','창천동'),(39,'test',32,'2023-08-15','창천동'),(40,'test',27,'2023-08-15','창천동'),(41,'test',31,'2023-08-15','창천동'),(42,'test',34,'2023-08-15','창천동'),(43,'test',30,'2023-08-15','창천동'),(44,'test',33,'2023-08-15','창천동'),(45,'test',35,'2023-08-15','창천동'),(46,'test',26,'2023-08-16','창천동'),(47,'test',28,'2023-08-16','창천동'),(48,'test',25,'2023-08-16','창천동'),(49,'test',24,'2023-08-16','창천동'),(50,'test',23,'2023-08-16','창천동'),(51,'test',23,'2023-08-16','창천동'),(52,'test',23,'2023-08-16','창천동'),(53,'test',24,'2023-08-16','창천동'),(54,'test',29,'2023-08-16','창천동'),(55,'test',30,'2023-08-16','창천동'),(56,'test',33,'2023-08-16','창천동'),(57,'test',31,'2023-08-16','창천동'),(58,'test',35,'2023-08-16','창천동'),(59,'test',27,'2023-08-16','창천동'),(60,'test',32,'2023-08-16','창천동'),(61,'test',34,'2023-08-16','창천동'),(62,'test',31,'2023-08-16','창천동'),(63,'test',36,'2023-08-16','창천동'),(64,'test',37,'2023-08-16','창천동'),(65,'kiheon',39,'2023-08-16','봉천동'),(66,'kiheon1',39,'2023-08-16','봉천동'),(67,'kiheon12',39,'2023-08-16','봉천동'),(68,'kiheon13',39,'2023-08-16','봉천동'),(69,'kiheon14',39,'2023-08-16','봉천동'),(70,'kiheon15',39,'2023-08-16','봉천동'),(71,'kiheon15',40,'2023-08-16','봉천동'),(72,'kiheon13',40,'2023-08-16','봉천동'),(73,'u_000000000000',38,'2023-08-16','역삼동'),(74,'u_000000000000',32,'2023-08-17','도곡동'),(75,'u_000000000000',29,'2023-08-17','역삼동'),(76,'u_000000000000',30,'2023-08-17','역삼동'),(77,'u_000000000000',23,'2023-08-17','역삼동'),(78,'u_000000000000',38,'2023-08-17','역삼동');
/*!40000 ALTER TABLE `view_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-18 11:48:13
