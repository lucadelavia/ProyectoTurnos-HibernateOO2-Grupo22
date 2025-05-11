-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: turnos
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` int NOT NULL,
  `nroCliente` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diasdeatencion`
--

DROP TABLE IF EXISTS `diasdeatencion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diasdeatencion` (
  `idDiasDeAtencion` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idDiasDeAtencion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `diasdeatencion_sucursal`
--

DROP TABLE IF EXISTS `diasdeatencion_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diasdeatencion_sucursal` (
  `sucursal_id` int NOT NULL,
  `diasDeAtencion_id` int NOT NULL,
  PRIMARY KEY (`sucursal_id`,`diasDeAtencion_id`),
  KEY `diasDeAtencion_id` (`diasDeAtencion_id`),
  CONSTRAINT `diasdeatencion_sucursal_ibfk_1` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`idsucursal`),
  CONSTRAINT `diasdeatencion_sucursal_ibfk_2` FOREIGN KEY (`diasDeAtencion_id`) REFERENCES `diasdeatencion` (`idDiasDeAtencion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empleado_especialidad`
--

DROP TABLE IF EXISTS `empleado_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado_especialidad` (
  `empleado_id` int NOT NULL,
  `especialidad_id` int NOT NULL,
  PRIMARY KEY (`empleado_id`,`especialidad_id`),
  KEY `especialidad_id` (`especialidad_id`),
  CONSTRAINT `empleado_especialidad_ibfk_1` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`idEmpleado`),
  CONSTRAINT `empleado_especialidad_ibfk_2` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidades` (`idEspecialidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `idEmpleado` int NOT NULL,
  `cuil` int DEFAULT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`),
  CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `especialidad_sucursal`
--

DROP TABLE IF EXISTS `especialidad_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidad_sucursal` (
  `sucursal_id` int NOT NULL,
  `especialidad_id` int NOT NULL,
  PRIMARY KEY (`sucursal_id`,`especialidad_id`),
  KEY `especialidad_id` (`especialidad_id`),
  CONSTRAINT `especialidad_sucursal_ibfk_1` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`idsucursal`),
  CONSTRAINT `especialidad_sucursal_ibfk_2` FOREIGN KEY (`especialidad_id`) REFERENCES `especialidades` (`idEspecialidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `especialidades`
--

DROP TABLE IF EXISTS `especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidades` (
  `idEspecialidad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEspecialidad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `establecimientos`
--

DROP TABLE IF EXISTS `establecimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `establecimientos` (
  `idestablecimiento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idestablecimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `idServicio` int NOT NULL AUTO_INCREMENT,
  `nombreServicio` varchar(255) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sucursales`
--

DROP TABLE IF EXISTS `sucursales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursales` (
  `idsucursal` int NOT NULL AUTO_INCREMENT,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `horarioApertura` time DEFAULT NULL,
  `horarioCierre` time DEFAULT NULL,
  `idestablecimiento` int DEFAULT NULL,
  `espacio` int DEFAULT NULL,
  PRIMARY KEY (`idsucursal`),
  KEY `idestablecimiento` (`idestablecimiento`),
  CONSTRAINT `sucursales_ibfk_1` FOREIGN KEY (`idestablecimiento`) REFERENCES `establecimientos` (`idestablecimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turnos` (
  `idTurno` int NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime DEFAULT NULL,
  `estadoActivo` tinyint(1) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `cliente_id` int NOT NULL,
  `sucursal_id` int NOT NULL,
  `servicio_id` int DEFAULT NULL,
  `empleado_id` int DEFAULT NULL,
  PRIMARY KEY (`idTurno`),
  KEY `cliente_id` (`cliente_id`),
  KEY `sucursal_id` (`sucursal_id`),
  KEY `fk_turno_empleado` (`empleado_id`),
  CONSTRAINT `fk_turno_empleado` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`idEmpleado`),
  CONSTRAINT `turnos_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`idCliente`),
  CONSTRAINT `turnos_ibfk_2` FOREIGN KEY (`sucursal_id`) REFERENCES `sucursales` (`idsucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` int NOT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11 16:36:46
