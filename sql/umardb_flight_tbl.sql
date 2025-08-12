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
-- Table structure for table `flight_tbl`
--

DROP TABLE IF EXISTS `flight_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flight_tbl` (
  `flight_id` varchar(45) NOT NULL,
  `flight_name` varchar(45) NOT NULL,
  `flight_type` varchar(45) NOT NULL,
  `flight_place_of_dept` varchar(45) NOT NULL,
  `flight_destination` varchar(45) NOT NULL,
  `flight_arr_time` varchar(45) NOT NULL,
  `flight_dept_time` varchar(45) NOT NULL,
  `flight_status` varchar(45) NOT NULL,
  PRIMARY KEY (`flight_id`),
  UNIQUE KEY `flight_id_UNIQUE` (`flight_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight_tbl`
--

LOCK TABLES `flight_tbl` WRITE;
/*!40000 ALTER TABLE `flight_tbl` DISABLE KEYS */;
INSERT INTO `flight_tbl` VALUES ('FL-001','Air Asia','International','India','Singapore','19:05','14:05','On_Time'),('FL-002','Air Asia','International','Singapore','India','02:05','21:05','On_Time'),('FL-003','IndiGo','Domestic','Delhi','Udaipur','08:20','07:00','On_Time'),('FL-004','Air India','Domestic','Udaipur','Delhi','11:05','09:45','Delay'),('FL-005','Spice Jet','International','India','Dubai','16:20','12:40','Delay'),('FL-006','Spice Jet','International','Dubai','India','20:40','17:00','On_Time'),('FL-007','Air Canada','International','India','Canada','22:20','08:00','On_Time'),('FL-008','Canadian Airlines','International','Canada','India','14:30','00:10','On_Time'),('FL-009','Air Asia','Domestic','Imphal','Goa','13:00','12:25','Delay'),('FL-010','Air Asia','Domestic','Goa','Imphal','15:00','14:25','Delay'),('FL-011','Air India','Domestic','Mumbai','Delhi','14:25','13:05','On_Time'),('FL-012','Air India','Domestic','Delhi','Mumbai','17:45','15:00','Delay'),('FL-013','PassengerWays','International','United States of America','California','04:25','23:25','On_Time'),('FL-014','PassengerWays','International','California','United States of America','12:00','07:00','On_Time'),('FL-015','Air Arabia','International','Dubai','Kuwait','15:45','14:00','On_Time');
/*!40000 ALTER TABLE `flight_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-04 21:52:14
