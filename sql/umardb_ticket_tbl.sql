-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: umardb
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ticket_tbl`
--

DROP TABLE IF EXISTS `ticket_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_tbl` (
  `ticket_id` varchar(45) NOT NULL,
  `p_full_name` varchar(45) NOT NULL,
  `p_age` varchar(45) NOT NULL,
  `p_email` varchar(45) NOT NULL,
  `p_phone` varchar(45) NOT NULL,
  `depart_date` varchar(45) NOT NULL,
  `depart_time` varchar(45) NOT NULL,
  `depart_from` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `flight_name` varchar(45) NOT NULL,
  `flight_class` varchar(45) NOT NULL,
  `flight_price` varchar(45) NOT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_id_UNIQUE` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_tbl`
--

LOCK TABLES `ticket_tbl` WRITE;
/*!40000 ALTER TABLE `ticket_tbl` DISABLE KEYS */;
INSERT INTO `ticket_tbl` VALUES ('TP-001','Mohammad Umar Abdullah','21','md.umarabdullah@gmail.com','7004195726','30/05/2022','14:05','India','Singapore','Air Asia','Economy','18000.00'),('TP-002','Gulshan Das','21','gulshanraj123@gmail.com','9565912340','31/05/2022','09:45','Udaipur','Delhi','Air India','First','60000.00'),('TP-003','Ritesh Saw','21','ritesh12@gmail.com','7786592106','05/06/2022','07:00','Delhi','Udaipur','IndiGo','Business','35000.00'),('TP-004','Manish Rawani','21','rawanimanish23@gmail.com','9876520015','17/08/2022','12:40','India','Dubai','Spice Jet','First','60000.00'),('TP-005','Vijay Krishna Paswan','21','vijayrajni333@gmail.com','7896592015','25/06/2022','13:05','Mumbai','Delhi','Air India','Economy','18000.00'),('TP-006','Md. Zaid Ibrahim','25','zaidy34@gmail.com','8752269808','17/06/2022','14:00','Dubai','Kuwait','Air Arabia','Business','35000.00');
/*!40000 ALTER TABLE `ticket_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-04 21:52:13
