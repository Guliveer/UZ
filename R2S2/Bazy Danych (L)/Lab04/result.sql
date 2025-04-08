-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: 127.0.0.1    Database: lab
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `miejscowosci`
--

DROP TABLE IF EXISTS `miejscowosci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miejscowosci` (
  `miejscowosci_id` int(11) NOT NULL,
  `nazwa` varchar(30) NOT NULL,
  `liczba_mieszkancow` int(11) DEFAULT NULL,
  PRIMARY KEY (`miejscowosci_id`),
  UNIQUE KEY `miejscowosci_pk` (`nazwa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miejscowosci`
--

LOCK TABLES `miejscowosci` WRITE;
/*!40000 ALTER TABLE `miejscowosci` DISABLE KEYS */;
/*!40000 ALTER TABLE `miejscowosci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenci`
--

DROP TABLE IF EXISTS `studenci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studenci` (
  `stud_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(30) NOT NULL,
  `pesel` decimal(11,0) NOT NULL,
  `data_urodzenia` date NOT NULL,
  `plec` enum('M','K') NOT NULL,
  `stypendium` decimal(8,2) NOT NULL,
  `uwagi` varchar(100) DEFAULT NULL,
  `miejscowosci_id` int(11) DEFAULT NULL,
  `Attribute1` char(20) DEFAULT NULL,
  PRIMARY KEY (`stud_id`),
  UNIQUE KEY `studenci_pk` (`pesel`),
  KEY `studenci_miejscowosci_miejscowosci_id_fk` (`miejscowosci_id`),
  CONSTRAINT `studenci_miejscowosci_miejscowosci_id_fk` FOREIGN KEY (`miejscowosci_id`) REFERENCES `miejscowosci` (`miejscowosci_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenci`
--

LOCK TABLES `studenci` WRITE;
/*!40000 ALTER TABLE `studenci` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenci` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-02 13:24:54
