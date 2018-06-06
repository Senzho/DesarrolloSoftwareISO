-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ared
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `alumno`
--
drop database Ared;
create database Ared;
use Ared;
CREATE USER 'aredespacio'@'localhost' IDENTIFIED BY 'aredespacio';
grant all privileges on Ared.* to 'aredespacio'@'localhost';
FLUSH PRIVILEGES;


DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `idAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `direccion` text,
  `correo` varchar(150) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idAlumno`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (3,'2018-04-18','Ana Elvia López López','Calle Francisco Esteban Mascareñas #15. Col. Rébsamen. Xalapa, Ver.','anelolo@yahoo.com',1,'2281767754'),(4,'2018-04-18','Juan Luis Muñóz Sánchez','Av. Lucio Vega #554. Col. Rodriguez Clara. Xalapa. Ver.','julumusa@outlook.es',1,'2281977734'),(5,'2018-04-18','Maria Violeta Magaña Castelán','Calle 3. Col. 21 de Marzo. Xalapa, Ver.','mavimaca@gmail.com',1,'2281654321'),(6,'2018-04-18','Zianya García Márquez','Av. 20 de Noviembre #35. Col. Centro. Xalapa, Ver.','zigama@outlook.com',1,'2281102534'),(7,'2018-04-18','Elton Roberto Hurtado Herrera','Calle Justo Sierra #1. Col. Progreso. Xalapa, Ver.','elrohuhe@gmail.com',1,'2281765590'),(8,'2018-04-18','Karla Mariana García Pozos','Av. Universidad #11. Col. Jacarandas. Xalapa, Ver.','kamagapo@outlook.es',1,'2281769922'),(9,'2018-04-18','Carolina Herrera Morales','Calle Juarez # 45. Col. Centro. Xalapa, Ver.','cahemo@yahoo.com',1,'2281654472'),(10,'2018-04-18','Rosa Grecia Mora López','Av. Américas #37. Col. Benito Juárez.','rogrmolo@outlook.es',1,'2281769009'),(11,'2018-04-18','Javier Antonio Mendoza García','Calle Santos Degollado # 5. Col. Centro. Xalapa, Ver.','janmega@gmail.com',1,'2281764930'),(12,'2018-04-19','Adrian Pérez','Calle Justo sierra #4','adpe@gmail.com',1,'2281765432');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistencia` (
  `idAsistencia` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `idGrupo` int(11) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAsistencia`),
  KEY `idGrupo` (`idGrupo`),
  KEY `idAlumno` (`idAlumno`),
  CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`idGrupo`) REFERENCES `grupo` (`idGrupo`),
  CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES (1,'2018-05-23',24,9),(2,'2018-05-23',24,10),(3,'2018-05-24',24,9),(4,'2018-05-24',24,10),(5,'2018-05-26',25,11),(6,'2018-05-26',25,12),(7,'2018-05-26',22,8),(8,'2018-05-26',22,4),(9,'2018-05-26',22,3),(10,'2018-05-26',21,5),(11,'2018-05-26',23,8),(12,'2018-05-26',23,7),(13,'2018-05-27',23,7),(14,'2018-05-27',23,8),(15,'2018-05-28',22,3),(16,'2018-05-28',22,4),(17,'2018-06-01',22,9),(18,'2018-06-01',22,8),(19,'2018-06-01',22,3),(20,'2018-06-01',22,4),(21,'2018-06-01',23,7),(22,'2018-06-01',23,8);
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `correo` varchar(150) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `direccion` text,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'2018-03-29','edmasazu@gmail.com','2281917765','Edna María Sanchez Zurita','Av. Murillo Vidál #22. Col. Progreso. Xalapa, Ver.'),(2,'2018-05-26','samalolo@gmail.com','2281654330','Sandra Mariana López López','Calle #3 Col. Emiliano Zapata, Xalapa, Veracruz.');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia`
--

DROP TABLE IF EXISTS `dia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dia` (
  `idDia` int(11) NOT NULL AUTO_INCREMENT,
  `salon` varchar(50) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `idTipo` int(11) DEFAULT NULL,
  `dia` varchar(10) DEFAULT NULL,
  `horaInicio` varchar(5) DEFAULT NULL,
  `horaFin` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`idDia`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia`
--

LOCK TABLES `dia` WRITE;
/*!40000 ALTER TABLE `dia` DISABLE KEYS */;
INSERT INTO `dia` VALUES (40,'X',1,21,'Lunes','09:00','11:00'),(41,'X',1,21,'Miercoles','11:30','14:30'),(42,'X',1,21,'Sabado','18:30','19:00'),(43,'X',1,21,'Domingo','09:00','09:30'),(44,'X',1,22,'Martes','20:00','21:30'),(45,'X',1,22,'Jueves','16:00','16:30'),(46,'X',1,22,'Sabado','09:00','12:00'),(47,'X',1,23,'Lunes','15:00','17:00'),(48,'X',1,23,'Martes','10:00','11:00'),(49,'X',1,23,'Miercoles','09:00','10:00'),(50,'X',1,23,'Jueves','12:30','15:00'),(51,'X',1,23,'Viernes','20:00','20:30'),(52,'X',1,23,'Sabado','12:30','13:30'),(53,'X',1,23,'Domingo','10:00','10:30'),(54,'X',1,24,'Miercoles','20:00','20:30'),(55,'X',1,24,'Jueves','09:00','10:30'),(56,'X',1,24,'Viernes','09:00','11:00'),(57,'X',1,25,'Lunes','17:30','19:30'),(58,'X',1,25,'Martes','11:30','12:30'),(59,'X',0,1,'Domingo','17:30','19:00'),(65,'X',0,2,'Sabado','19:00','21:30'),(66,'X',0,3,'Martes','09:30','10:00'),(67,'X',0,4,'Viernes','12:00','12:30');
/*!40000 ALTER TABLE `dia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egreso`
--

DROP TABLE IF EXISTS `egreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egreso` (
  `idEgreso` int(11) NOT NULL AUTO_INCREMENT,
  `monto` varchar(15) DEFAULT NULL,
  `descripcion` text,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idEgreso`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egreso`
--

LOCK TABLES `egreso` WRITE;
/*!40000 ALTER TABLE `egreso` DISABLE KEYS */;
INSERT INTO `egreso` VALUES (3,'50.0','Escoba.','2018-04-11'),(4,'140','Paquete de 100 hojas para impresora.','2018-04-19'),(5,'100','Hojas de papel para impresión','2018-04-19');
/*!40000 ALTER TABLE `egreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastopromocional`
--

DROP TABLE IF EXISTS `gastopromocional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gastopromocional` (
  `idGasto` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date DEFAULT NULL,
  `fechaFin` date DEFAULT NULL,
  `monto` varchar(15) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `descripcion` text,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGasto`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `gastopromocional_ibfk_1` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastopromocional`
--

LOCK TABLES `gastopromocional` WRITE;
/*!40000 ALTER TABLE `gastopromocional` DISABLE KEYS */;
INSERT INTO `gastopromocional` VALUES (4,'2018-04-15','2018-04-30','280','www.google.com','Inscribete del 15 al 30 de abril y paga solo la mitad.',10),(5,'2018-04-19','2018-05-05','1000','www.facebook.com','Paga la mitad de la inscripción',11);
/*!40000 ALTER TABLE `gastopromocional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `idGrupo` int(11) NOT NULL AUTO_INCREMENT,
  `danza` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGrupo`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `grupo_ibfk_1` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (21,'Hip Hop','HIPHOP_LDMJ',11,1),(22,'Techno','TECHNO_LDMJ',11,1),(23,'Ballet','BALLET_DIREC',10,1),(24,'Folklorica','FOLKLORICA_DIREC',10,1),(25,'Clasica','CLASICA_DIREC',10,1);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscripcion` (
  `idInscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `idGrupo` int(11) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInscripcion`),
  KEY `idGrupo` (`idGrupo`),
  KEY `idAlumno` (`idAlumno`),
  CONSTRAINT `inscripcion_ibfk_1` FOREIGN KEY (`idGrupo`) REFERENCES `grupo` (`idGrupo`),
  CONSTRAINT `inscripcion_ibfk_2` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (16,22,4,1),(17,21,5,1),(18,21,6,1),(19,23,7,1),(20,23,8,1),(21,24,9,1),(22,24,10,1),(23,25,11,1),(24,25,12,1),(25,22,8,1),(27,22,3,1),(29,22,9,1),(30,25,8,1);
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoalumno`
--

DROP TABLE IF EXISTS `pagoalumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoalumno` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `tipoPago` int(11) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `idPromocion` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `monto` varchar(15) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  `idGrupo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idAlumno` (`idAlumno`),
  KEY `idPromocion` (`idPromocion`),
  KEY `idProfesor` (`idProfesor`),
  KEY `idGrupo` (`idGrupo`),
  CONSTRAINT `pagoalumno_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`),
  CONSTRAINT `pagoalumno_ibfk_2` FOREIGN KEY (`idPromocion`) REFERENCES `promocion` (`idPromocion`),
  CONSTRAINT `pagoalumno_ibfk_3` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`),
  CONSTRAINT `pagoalumno_ibfk_4` FOREIGN KEY (`idGrupo`) REFERENCES `grupo` (`idGrupo`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoalumno`
--

LOCK TABLES `pagoalumno` WRITE;
/*!40000 ALTER TABLE `pagoalumno` DISABLE KEYS */;
INSERT INTO `pagoalumno` VALUES (58,0,7,1,'2018-05-05','1200',10,23),(59,1,7,NULL,'2018-05-24','600',10,23),(60,0,8,NULL,'2018-05-24','1200',10,23),(61,1,8,NULL,'2018-05-24','600',10,23),(62,0,8,NULL,'2018-05-24','1200',10,25),(63,1,8,NULL,'2018-05-24','600',10,25),(64,0,9,NULL,'2018-05-24','1200',10,24),(65,1,9,5,'2018-05-24','500',10,24),(66,0,10,NULL,'2018-05-24','3000',10,24),(67,1,10,NULL,'2018-05-24','1200',10,24),(68,0,11,NULL,'2018-05-24','1200',10,25),(69,1,11,NULL,'2018-05-24','400',10,25),(70,0,12,NULL,'2018-05-24','4500',10,25),(71,1,12,NULL,'2018-05-24','500',10,25);
/*!40000 ALTER TABLE `pagoalumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoprofesor`
--

DROP TABLE IF EXISTS `pagoprofesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoprofesor` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `tipoPago` int(11) DEFAULT NULL,
  `monto` varchar(15) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `pagoprofesor_ibfk_1` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoprofesor`
--

LOCK TABLES `pagoprofesor` WRITE;
/*!40000 ALTER TABLE `pagoprofesor` DISABLE KEYS */;
INSERT INTO `pagoprofesor` VALUES (17,1,'1850','2018-04-18',11),(18,0,'2600','2018-04-18',10);
/*!40000 ALTER TABLE `pagoprofesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagotemporal`
--

DROP TABLE IF EXISTS `pagotemporal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagotemporal` (
  `idPago` int(11) NOT NULL AUTO_INCREMENT,
  `tipoPago` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `monto` varchar(15) DEFAULT NULL,
  `idAlumno` int(11) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPago`),
  KEY `idAlumno` (`idAlumno`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `pagotemporal_ibfk_1` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`idAlumno`),
  CONSTRAINT `pagotemporal_ibfk_2` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagotemporal`
--

LOCK TABLES `pagotemporal` WRITE;
/*!40000 ALTER TABLE `pagotemporal` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagotemporal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profesor` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `estado` int(11) DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `tipoPago` int(11) DEFAULT NULL,
  `correo` varchar(150) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `direccion` text,
  `monto` varchar(15) DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  PRIMARY KEY (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (10,1,'Gabriela Sosa Martínez','2281916676',0,'gasoma@gmail.com','2018-04-18','Av. Américas #45. Col. Centro. Xalapa, Ver.','2600','2018-04-23'),(11,1,'Luis Daniel Montoya Jiménez','2281765542',1,'ludamoji@outlook.es','2018-04-18','Calle Justino Sarmiento #03. Col. Revolución. Xalapa, Ver.','1850','2018-02-01');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `idPromocion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` text,
  `porcentaje` int(11) DEFAULT NULL,
  `idProfesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPromocion`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `promocion_ibfk_1` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
INSERT INTO `promocion` VALUES (1,'Inscripción / 2','Se paga solamente el 50% del costo de la inscripción.',50,10),(2,'Solo 20% al mes','Se paga únicamente el 20% en la mensualidad.',20,10),(3,'Inscripción 1/4','Solo se paga 1/4 de la inscripción.',75,11),(4,'Medio mes','Solo se paga la mitad de la mensualidad.',50,11),(5,'Promoción X','25% menos',25,10);
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renta`
--

DROP TABLE IF EXISTS `renta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `renta` (
  `idRenta` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `monto` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idRenta`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `renta_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renta`
--

LOCK TABLES `renta` WRITE;
/*!40000 ALTER TABLE `renta` DISABLE KEYS */;
INSERT INTO `renta` VALUES (1,'2018-05-19',1,'2500'),(2,'2018-05-26',1,'650'),(3,'2018-06-05',2,'300'),(4,'2018-06-01',1,'300');
/*!40000 ALTER TABLE `renta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT NULL,
  `idTipoUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (23,'direc_gaby','5b7b165ce98eceda3f4ca2740d061dca',0,10),(24,'profe_luis','502ff82f7f1f8218dd41201fe4353687',1,11),(25,'abcde@abcd.com','66a4cc20eb6fe3251a256f02b5623630',1,12);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-02 17:54:02
