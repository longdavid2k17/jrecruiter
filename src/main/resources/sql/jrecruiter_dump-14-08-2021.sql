-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: jrecruiter
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `localization` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (1,'Comarch','Usługi, systemy informatyczne i obsługa firm, opieka i rozwiązania IT. COMARCH jest globalnym dostawcą biznesowych rozwiązań IT obsługujących relacje z zagranicznymi firmami','Polska'),(2,'Asseco Poland','Asseco Poland jest wszędzie tam, gdzie technologia i biznes łączą się z codziennym życiem. Suma doświadczeń, które płyną ze wszystkich sektorów rynku, pozwala nam kreować niezawodne, zaawansowane produkty.','Polska'),(3,'Integrated Solutions','Od 2011 doradzamy naszym klientom, jak jeszcze lepiej rozwijać swoje biznesy dzięki cyfryzacji i nowoczesnym technologiom. Dostarczamy gotowe rozwiązania oraz zapewniamy najwyższy poziom bezpieczeństwa wdrożeń.','Polska'),(4,'CD Projekt RED','Zatrudniamy obecnie ponad pół tysiąca ludzi z całego świata. Choć różni nas wiele, od wyznawanych wartości, po język, którym się posługujemy na co dzień, łączy jedno – pasja do gier. To ona definiuje kim jesteśmy, w jaki sposób działamy i dokąd zmierzamy. Mamy przed sobą jeden cel – chcemy dostarczać graczom na całym świecie rozrywkę na najwyższym poziomie i tworzyć gry, które na stałe wejdą do kanonu światowej popkultury.','Polska'),(5,'Dell ','Jesteśmy głównym dostawcą rozwiązań IT dla firm z tego segmentu na rynku amerykańskim. Duże przedsiębiorstwa darzą zaufaniem rozwiązania firmy Dell. Jesteśmy największym na świecie dostawcą systemów pamięci masowej Internet SCSI (iSCSI) i największym w USA producentem serwerów x86.','Polska'),(6,'HP Inc','Naszą wizją jest tworzenie technologii ułatwiających życie wszystkim — każdej osobie, organizacji i społeczności na całym świecie. To nas inspiruje i motywuje do dalszej pracy. Do tworzenia. Do odkrywania i rozwijania. Do poszukiwania niesamowitych rozwiązań. Nigdy nie spoczniemy na laurach, bo nasi klienci też stale poszukują. Poszukują nowych sposobów na pracę. Na zabawę. Na życie. Za sprawą naszych technologii zmieniają świat.','cały świat'),(7,'Microsoft','Jako lider w obszarze cloud computingu, firma nieustannie tworzy nowe usługi i rozwiązania chmurowe oraz mechanizmy AI, które pomagają w transformacji instytucji, przedsiębiorstw oraz całych gałęzi gospodarki. Microsoft Corporation powstał w 1975 roku w USA, a polski oddział firmy istnieje od 1992 r.','cały świat'),(8,'IBM','Pracownicy IBM wierzą w postęp. W to, że wykorzystanie informacji, rozumu i nauki może prowadzić do rozwoju biznesowego i społecznego oraz poprawy jakości życia.','Europa'),(9,'Sii Polska','Zatrudniając 5 000 specjalistów, Sii jest największym dostawcą usług doradztwa technologicznego, transformacji cyfrowej, BPO i inżynieryjnych w Polsce','Polska');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_offers`
--

DROP TABLE IF EXISTS `job_offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_offers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contract_type` varchar(50) DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `high_end_payment_range` double NOT NULL,
  `low_end_payment_range` double NOT NULL,
  `position_description` varchar(1000) DEFAULT NULL,
  `position_title` varchar(30) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `leading_technology` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ta7i9ubxobstdem21hgs2byb` (`company_id`),
  CONSTRAINT `FK8ta7i9ubxobstdem21hgs2byb` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_offers`
--

LOCK TABLES `job_offers` WRITE;
/*!40000 ALTER TABLE `job_offers` DISABLE KEYS */;
INSERT INTO `job_offers` VALUES (1,'Umowa o pracę','2021-07-04 16:39:48',7140,4376,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C Developer',2,'C'),(2,'Umowa o pracę','2021-07-04 16:44:55',7968,3167,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack Python Developer',6,'Python'),(3,'Umowa o dzieło','2021-07-04 17:24:47',8104,3786,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',6,'Node JS'),(4,'Umowa o dzieło','2021-07-04 17:45:21',7136,4097,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',6,'Node JS'),(5,'Umowa o dzieło','2021-07-04 17:49:27',8135,3694,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack JAVA Developer',5,'JAVA'),(6,'Umowa zlecenie','2021-07-04 17:54:14',8639,2876,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',7,'Node JS'),(7,'Umowa zlecenie','2021-07-04 18:08:40',8420,2892,'This is default offer description, generated by service. Nothing here is with sense till edition.','Senior Database engineer',4,'Database'),(8,'Umowa o pracę','2021-07-04 18:29:45',4646,3149,'This is default offer description, generated by service. Nothing here is with sense till edition.','Junior Database engineer',2,'Database'),(9,'Umowa o dzieło','2021-07-04 18:33:48',4847,3565,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista C',8,'C'),(10,'Staż','2021-07-05 11:40:03',6367,3327,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C Developer',8,'C'),(11,'Staż','2021-07-05 11:50:19',7850,4209,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - React',6,'React'),(12,'Umowa zlecenie','2021-07-05 11:53:05',5622,3789,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista .NET',7,'.Net'),(13,'Umowa o pracę','2021-07-05 12:31:10',8981,4302,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Angular',5,'Angular'),(14,'Staż','2021-07-05 12:32:23',5408,2855,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista JAVA',4,'JAVA'),(15,'Staż','2021-07-05 12:38:59',8634,2863,'This is default offer description, generated by service. Nothing here is with sense till edition.','Junior Database engineer',5,'Database'),(16,'Umowa zlecenie','2021-07-05 12:59:34',8499,3141,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',1,'Node JS'),(17,'Umowa o pracę','2021-07-05 13:00:22',7829,3349,'This is default offer description, generated by service. Nothing here is with sense till edition.','Junior Database engineer',3,'Database'),(18,'Staż','2021-07-05 13:03:49',5514,3998,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista C++',7,'C++'),(19,'Umowa zlecenie','2021-07-05 13:07:22',6718,3084,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista JAVA',2,'JAVA'),(20,'Umowa o dzieło','2021-07-05 13:59:06',8774,2638,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack .NET Developer',5,'.Net'),(21,'Umowa zlecenie','2021-07-05 14:44:45',7648,2970,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack .NET Developer',1,'.Net'),(22,'Umowa o pracę','2021-07-05 15:04:42',8720,4149,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - React',3,'React'),(23,'Staż','2021-07-05 15:08:43',4591,4426,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack JAVA Developer',9,'JAVA'),(24,'Umowa o pracę','2021-07-05 15:29:04',6759,3441,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack JAVA Developer',7,'JAVA'),(25,'Umowa o dzieło','2021-07-05 17:57:39',7433,3802,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista .NET',4,'.Net'),(26,'Staż','2021-07-05 18:03:45',7804,2765,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack .NET Developer',3,'.Net'),(27,'Umowa zlecenie','2021-07-05 18:19:01',7797,2782,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack JAVA Developer',3,'JAVA'),(28,'Umowa zlecenie','2021-07-05 18:46:18',6854,3986,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista Python',5,'Python'),(29,'Umowa zlecenie','2021-07-05 18:46:20',7799,4458,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',8,'Node JS'),(30,'Umowa o pracę','2021-07-05 18:47:28',6658,3583,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista Python',3,'Python'),(31,'Umowa o pracę','2021-07-05 18:48:50',8024,4149,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',9,'Node JS'),(32,'Umowa o pracę','2021-07-05 18:56:02',8926,3650,'This is default offer description, generated by service. Nothing here is with sense till edition.','Project manager',6,'Managment'),(33,'Umowa zlecenie','2021-07-05 19:01:43',4602,4235,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Angular',2,'Angular'),(34,'Umowa o dzieło','2021-07-05 21:10:07',6635,3473,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C++ Developer',3,'C++'),(35,'Umowa zlecenie','2021-07-05 21:12:44',5694,2642,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Node JS',3,'Node JS'),(36,'Umowa o pracę','2021-07-05 21:29:06',6326,3960,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C++ Developer',5,'C++'),(37,'Umowa o pracę','2021-07-05 22:12:29',6627,2978,'This is default offer description, generated by service. Nothing here is with sense till edition.','Senior Database engineer',7,'Database'),(38,'Staż','2021-07-05 22:18:34',8977,2506,'This is default offer description, generated by service. Nothing here is with sense till edition.','Junior Database engineer',6,'Database'),(39,'Umowa o pracę','2021-07-05 22:48:25',4549,3143,'This is default offer description, generated by service. Nothing here is with sense till edition.','Regular Database engineer',9,'Database'),(40,'Umowa o dzieło','2021-07-05 22:51:55',5900,3011,'This is default offer description, generated by service. Nothing here is with sense till edition.','Project manager',7,'Managment'),(41,'Umowa o pracę','2021-07-05 23:07:31',6058,2995,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - React',8,'React'),(42,'Staż','2021-07-06 11:30:14',4757,2613,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Angular',8,'Angular'),(43,'Umowa o dzieło','2021-07-06 11:50:47',6967,4105,'This is default offer description, generated by service. Nothing here is with sense till edition.','Project manager',8,'Managment'),(44,'Umowa o dzieło','2021-07-06 12:09:08',8937,2704,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista Python',8,'Python'),(45,'Umowa o pracę','2021-07-06 12:11:06',8148,3971,'This is default offer description, generated by service. Nothing here is with sense till edition.','Frontend developer - Angular',7,'Angular'),(46,'Umowa zlecenie','2021-07-06 12:11:56',8461,2966,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C Developer',8,'C'),(47,'Umowa zlecenie','2021-07-06 13:05:08',5425,2668,'This is default offer description, generated by service. Nothing here is with sense till edition.','Senior Database engineer',6,'Database'),(48,'Umowa zlecenie','2021-07-06 17:09:01',6284,4418,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista Python',5,'Python'),(49,'Umowa o dzieło','2021-07-06 17:32:17',5586,3216,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista JAVA',4,'JAVA'),(50,'Staż','2021-07-06 17:58:37',6603,4180,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack C++ Developer',4,'C++'),(51,'Umowa o pracę','2021-07-06 18:00:43',7072,3878,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista C',5,'C'),(52,'Umowa o dzieło','2021-07-06 18:02:22',6713,2573,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack JAVA Developer',7,'JAVA'),(53,'Staż','2021-07-06 18:11:00',8321,2908,'This is default offer description, generated by service. Nothing here is with sense till edition.','Senior Database engineer',3,'Database'),(54,'Staż','2021-07-08 11:23:34',5108,3222,'This is default offer description, generated by service. Nothing here is with sense till edition.','Fullstack .NET Developer',3,'.Net'),(55,'Umowa o dzieło','2021-07-08 11:26:45',6788,3233,'This is default offer description, generated by service. Nothing here is with sense till edition.','Programista Python',6,'Python');
/*!40000 ALTER TABLE `job_offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment_processes`
--

DROP TABLE IF EXISTS `recruitment_processes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment_processes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `feedback` varchar(500) DEFAULT NULL,
  `process_start_date` datetime NOT NULL,
  `process_status` varchar(255) DEFAULT NULL,
  `job_offer_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrd0lueov1kg740y4syal0dpyb` (`job_offer_id`),
  KEY `FKmc6ni9lhjjmjk8yj9nblia8nd` (`user_id`),
  CONSTRAINT `FKmc6ni9lhjjmjk8yj9nblia8nd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrd0lueov1kg740y4syal0dpyb` FOREIGN KEY (`job_offer_id`) REFERENCES `job_offers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment_processes`
--

LOCK TABLES `recruitment_processes` WRITE;
/*!40000 ALTER TABLE `recruitment_processes` DISABLE KEYS */;
INSERT INTO `recruitment_processes` VALUES (1,NULL,'2021-07-06 10:39:48','Wysłano aplikacje',5,1);
/*!40000 ALTER TABLE `recruitment_processes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone_number` varchar(12) DEFAULT NULL,
  `profile_img_url` varchar(150) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `cv_path` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'dawid@gmail.com','Dawid','$2a$10$Bv/fgTx4egZNtbzrz7c.oeZgn.YPhW5vmQ3tgRKgMvRc/drBYnv56','+48517195222',NULL,'Kańtoch','dawid','Dawid Kantoch CV.pdf'),(2,'turbo@email.com','Jan','$2a$10$pnmOh6h27kk5DBg402eSMOK4zOeHI5HyWzHOTJND6tvD7KPRmuDCK','666777888',NULL,'Nowak','testowy',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-14 14:25:16
