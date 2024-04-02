-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: resumes_console_proyect
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tittle` varchar(80) NOT NULL,
  `fullName` varchar(80) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `description` text,
  `certifications` text,
  `socialNetworks` text,
  `skills` text,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `resume_user_fk` (`id_user`),
  CONSTRAINT `resume_user_fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (4,'test2023-10-04T04:43:32.411Z','name updated','address updated','description updated','certifications updated','socials updated','skills updated',2),(5,'test2023-10-04T04:43:55.132Z',NULL,NULL,NULL,'[]','{}','[]',2),(6,'test2023-10-04T04:45:30.958Z',NULL,NULL,NULL,'[]','{}','[]',2),(7,'Resume para el tio julio',NULL,NULL,NULL,'[]','{}','[]',2),(8,'test2023-10-05T23:40:52.675Z',NULL,NULL,NULL,'[]','{}','[]',2),(9,'test2023-10-05T23:41:34.236Z',NULL,NULL,NULL,'[]','{}','[]',2),(10,'test2023-10-05T23:43:24.417Z',NULL,NULL,NULL,'[]','{}','[]',2),(11,'Curriculum Scantech',NULL,NULL,NULL,'[]','{}','[]',18),(12,'Curriculum Diego Calderon',NULL,NULL,NULL,'[]','{}','[]',18),(13,'Curriculum tio Carlos',NULL,NULL,NULL,'[]','{}','[]',18),(14,'curriculum mamá','Haydee Gregoria Puente Contreras',NULL,NULL,'[]','{}','[]',18),(15,'Mi curriculum',NULL,NULL,NULL,'[]','{}','[]',22),(17,'test2023-11-09T15:50:47.090Z',NULL,NULL,NULL,'[]','{}','[]',2),(18,'test2023-11-10T18:19:59.223Z',NULL,NULL,NULL,'[]','{}','[]',2),(19,'Mi primer resume','Brando Calderon Puente','Las vegas','una vaga descripcion','[, jws, awt, oracle, ]','{github=github.com/brando333, whatsApp=wa.me/912880415, facebook=facebook.com/brando.jeanpier}','[]',3),(20,'My first resume','Juan Gabriel Hinostroza','Psje. Los olivos 143',NULL,'[]','{}','[]',3),(21,'Mi nuevo resume','Diego Calderon Puente',NULL,NULL,'[]','{}','[]',3),(22,'Mi resume','Brando Calderon PUente',NULL,'una vaga descripcion','[]','{}','[]',3),(23,'new resume','Brando Calderon Puente',NULL,'another vague description','[]','{}','[]',3),(24,'another new resume',NULL,NULL,NULL,'[]','{}','[]',3),(25,'mi resumE',NULL,NULL,NULL,'[]','{}','[]',3),(26,'resume nuevo',NULL,'brando jeanpier',NULL,'[]','{}','[]',3),(27,'nueva prueba',NULL,'brando jeanpier',NULL,'[]','{wsp=wp.com, facebook=fb.com}','[]',3),(28,'prueba2',NULL,'brando jeanpier',NULL,'[, java, oop]','{}','[]',3),(29,'prueba 3','brando jeanpier','brando jeanpier',NULL,'[]','{}','[]',3),(30,'no se','brando jeanpier','brando jeanpier',NULL,'[, java]','{}','[]',3),(31,'otra prueba','brando jeanpier','brando jeanpier',NULL,'[, java]','{}','[]',3),(32,'mi resume','brando jeanpier','brando jeanpier',NULL,'[]','{}','[]',3);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullName` varchar(80) NOT NULL,
  `emailAddress` varchar(100) NOT NULL,
  `password` varchar(60) NOT NULL,
  `keyHash` varchar(29) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emailAddress` (`emailAddress`),
  UNIQUE KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'This is just a test','this is a test.com','$2a$10$daysCuvNwf5yrrs.Q2n4bO9JA7lL9brB69q7shcRptbS9/U6Db4kS','$2a$10$daysCuvNwf5yrrs.Q2n4bO'),(3,'brando jeanpier','brando@hotmail.com','$2a$10$HdmHTzB6TmFr4pLJJa/kgOIKpG7ysiAN7a.y.cD0SivEEO2aZVsay','$2a$10$HdmHTzB6TmFr4pLJJa/kgO'),(4,'diego hugo','diego@hotmail.com','$2a$10$By5qdc5Q.SG4lg2n015egeZTFZRdmQCWkDH.4pXkxeMJvf24VnphG','$2a$10$By5qdc5Q.SG4lg2n015ege'),(5,'diego hugo','diegocalderon@hotmail.com','$2a$10$irVUU0gBj3qKmcDjNhEU1ej1uz.yWtgAJUveuGR5JR37q/zYfs8ye','$2a$10$irVUU0gBj3qKmcDjNhEU1e'),(18,'Brando Jeanpier Calderon Puente','brandobrandocalderon@gmail.com','$2a$10$Mn9sa0Oe2C3XiAn0a85eReQzR4fXgD.do/.WDI8evIRh.Jephwfve','$2a$10$Mn9sa0Oe2C3XiAn0a85eRe'),(19,'','','$2a$10$dZE/NyTqdAidaA0yfnN5U.reYQM7b4kKJV6q4kugMACcRRYRd5Pqe','$2a$10$dZE/NyTqdAidaA0yfnN5U.'),(21,'aea','aea.com','$2a$10$63gxIdOdWp6U2xqknsqvU.l/CeSoquF/b8F4y/ITVyH5tgk.zN/FC','$2a$10$63gxIdOdWp6U2xqknsqvU.'),(22,'Hugo Cabrera','hugo@hotmail.com','$2a$10$6GL9jhPk.MzlTHo5ryzhiur1AS8oop6vq02k0mAAuMVy0cVVtY39S','$2a$10$6GL9jhPk.MzlTHo5ryzhiu'),(23,'test','2023-11-09T15:50:47.103Z@example.com','$2a$10$jnFBRecwxMBEfOUSH5llqOxAU4nZ6ifVZzmh78fXcsYsFGLmjkG1W','$2a$10$jnFBRecwxMBEfOUSH5llqO'),(24,'test','2023-11-10T18:19:59.247Z@example.com','$2a$10$hvLSeiD0Ns/ay8YZFddUcO2LzKpNwpoG8y.gTiO4TuX06ZnJGAv1q','$2a$10$hvLSeiD0Ns/ay8YZFddUcO');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-02 17:49:55
