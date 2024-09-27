-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: intern
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'công nghệ thông tin'),(2,'kinh tế'),(3,'tiếng anh'),(4,'marketing');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_following`
--

DROP TABLE IF EXISTS `category_following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_following` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `students_fk_user` (`user_id`),
  KEY `category_fk_student` (`category_id`),
  CONSTRAINT `category_fk_student` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `students_fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_following`
--

LOCK TABLES `category_following` WRITE;
/*!40000 ALTER TABLE `category_following` DISABLE KEYS */;
INSERT INTO `category_following` VALUES (4,1,6),(5,2,6),(6,3,6);
/*!40000 ALTER TABLE `category_following` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `number_of_student` int(11) DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_fk_category` (`category_id`),
  CONSTRAINT `course_fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,1,'Java Programming Course PHAN 1','Learn Java from beginner to advanced',NULL,0,NULL,200000,NULL,'2024-09-26 00:00:00'),(2,1,'Java Programming Course PHAN 1','Learn Java from beginner to advanced',NULL,0,NULL,200000,NULL,'2024-09-26 00:00:00'),(3,1,'React Programming Course Part 1','Learn ReactJS from beginner to advanced',NULL,0,NULL,200000,NULL,'2024-09-27 00:00:00'),(4,1,'CSS Programming Course','Learn CSS from beginner to advanced',NULL,0,NULL,150000,NULL,'2024-09-27 00:00:00'),(5,1,'Python Programming Course','Learn Python from beginner to advanced',NULL,0,NULL,180000,NULL,'2024-09-27 00:00:00');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text DEFAULT NULL,
  `duration` int(11) NOT NULL,
  `video_url` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lesson_fk_section` (`section_id`),
  CONSTRAINT `lesson_fk_section` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,1,'Lesson 1: Introduction to Java phan 1',NULL,0,'https://example.com/java-intro'),(2,1,'Lesson 2: OOP in Java phan 1',NULL,0,'https://example.com/java-oop'),(3,2,'Lesson 1: Introduction to HTML, CSS phan 1',NULL,0,'https://example.com/html-css-intro'),(4,3,'Lesson 1: Introduction to Java phan 2',NULL,0,'https://example.com/java-intro'),(5,3,'Lesson 2: OOP in Java phan 2',NULL,0,'https://example.com/java-oop'),(6,4,'Lesson 1: Introduction to HTML, CSS phan 2',NULL,0,'https://example.com/html-css-intro'),(7,5,'Lesson 1: What is React?',NULL,0,'https://example.com/what-is-react'),(8,5,'Lesson 2: Setting Up React Environment',NULL,0,'https://example.com/react-setup'),(9,5,'Lesson 3: JSX and Rendering Elements',NULL,0,'https://example.com/react-jsx'),(10,6,'Lesson 1: Components in React',NULL,0,'https://example.com/react-components'),(11,6,'Lesson 2: State and Props in React',NULL,0,'https://example.com/react-state-props'),(12,6,'Lesson 3: Handling Events in React',NULL,0,'https://example.com/react-events'),(13,7,'Lesson 1: Introduction to React Router',NULL,0,'https://example.com/react-router'),(14,7,'Lesson 2: Using useState and useEffect Hooks',NULL,0,'https://example.com/react-hooks'),(15,7,'Lesson 3: Custom Hooks in React',NULL,0,'https://example.com/react-custom-hooks'),(16,8,'Lesson 1: What is CSS?',NULL,0,'https://example.com/what-is-css'),(17,8,'Lesson 2: How to Include CSS in HTML',NULL,0,'https://example.com/include-css-html'),(18,8,'Lesson 3: CSS Selectors',NULL,0,'https://example.com/css-selectors'),(19,9,'Lesson 1: CSS Box Model',NULL,0,'https://example.com/css-box-model'),(20,9,'Lesson 2: Flexbox in CSS',NULL,0,'https://example.com/css-flexbox'),(21,9,'Lesson 3: CSS Grid Layout',NULL,0,'https://example.com/css-grid'),(22,10,'Lesson 1: Text Styling in CSS',NULL,0,'https://example.com/css-text-styling'),(23,10,'Lesson 2: Backgrounds and Borders',NULL,0,'https://example.com/css-backgrounds-borders'),(24,10,'Lesson 3: Responsive Design with Media Queries',NULL,0,'https://example.com/css-responsive-design'),(25,11,'Lesson 1: What is Python?',NULL,0,'https://example.com/what-is-python'),(26,11,'Lesson 2: Installing Python and Setting Up',NULL,0,'https://example.com/install-python'),(27,11,'Lesson 3: Your First Python Program',NULL,0,'https://example.com/python-first-program'),(28,12,'Lesson 1: Variables and Data Types',NULL,0,'https://example.com/python-variables'),(29,12,'Lesson 2: Control Flow (if-else, loops)',NULL,0,'https://example.com/python-control-flow'),(30,12,'Lesson 3: Functions in Python',NULL,0,'https://example.com/python-functions'),(31,13,'Lesson 1: Object-Oriented Programming in Python',NULL,0,'https://example.com/python-oop'),(32,13,'Lesson 2: File Handling in Python',NULL,0,'https://example.com/python-file-handling'),(33,13,'Lesson 3: Working with Libraries and Modules',NULL,0,'https://example.com/python-modules');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_authentication`
--

DROP TABLE IF EXISTS `mail_authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mail_authentication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token_sent_at` datetime NOT NULL,
  `token` varchar(100) NOT NULL,
  `is_confirmed_mail` tinyint(1) DEFAULT 0,
  `is_approved` tinyint(1) DEFAULT 0,
  `confirm_completed_at` datetime DEFAULT NULL,
  `approve_completed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mail_authentication_fk_user` (`user_id`),
  CONSTRAINT `mail_authentication_fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_authentication`
--

LOCK TABLES `mail_authentication` WRITE;
/*!40000 ALTER TABLE `mail_authentication` DISABLE KEYS */;
INSERT INTO `mail_authentication` VALUES (1,2,'2024-09-26 23:30:49','86f42b80-15e7-47f5-87e8-44c40d8b58d4',1,0,'2024-09-26 23:31:05',NULL),(2,3,'2024-09-26 23:33:21','67b44813-9780-42f1-9f3b-d1306945a612',1,0,'2024-09-26 23:33:27',NULL),(3,4,'2024-09-26 23:34:04','d7289532-cd62-42ea-823c-b4e5824c648e',1,0,'2024-09-26 23:34:10',NULL),(4,6,'2024-09-26 23:55:52','9a86b7cd-923c-470c-89bd-14a40e768a19',1,0,'2024-09-26 23:56:06',NULL);
/*!40000 ALTER TABLE `mail_authentication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number_of_lesson` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `section_fk_course` (`course_id`),
  CONSTRAINT `section_fk_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,1,'Introduction to Java Phan 1',2),(2,1,'Introduction to HTML, CSS phan 1',1),(3,2,'Introduction to Java Phan 2',2),(4,2,'Introduction to HTML, CSS phan 2',1),(5,3,'Getting Started with React',3),(6,3,'React Components and State',3),(7,3,'React Router and Hooks',3),(8,4,'Introduction to CSS',3),(9,4,'CSS Layouts and Positioning',3),(10,4,'CSS Styling Techniques',3),(11,5,'Introduction to Python',3),(12,5,'Python Basics',3),(13,5,'Advanced Python Concepts',3);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_following`
--

DROP TABLE IF EXISTS `teacher_following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_following` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_fk_user` (`teacher_id`),
  CONSTRAINT `student_fk_user` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`),
  CONSTRAINT `teacher_fk_user` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_following`
--

LOCK TABLES `teacher_following` WRITE;
/*!40000 ALTER TABLE `teacher_following` DISABLE KEYS */;
INSERT INTO `teacher_following` VALUES (2,3,6),(3,2,6);
/*!40000 ALTER TABLE `teacher_following` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  `profile_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `position` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'linh','linhkhanhurlphoto','khanhlinh@gmail.com','$2a$10$dfRpesisQYiHVn3lfL95m.t7W90UD5tDQlIvUq4WfFCJKJxuqJj72','635442322','describe',1,1,'IT Dev'),(3,'loc','lockhanhurlphoto','khanhloc@gmail.com','$2a$10$ZgD2yzyMERtzaOKKhsv4iuqgqD9OSsR.o.YP.44a4R7qUCw/RjL0O','635442322','describe',1,1,'IT Dev'),(4,'phuc','phuckhanhurlphoto','khanhphuc@gmail.com','$2a$10$0z4wc6VpRY5Vn7V3.ACg5enlLrvIBx8SFoSoY/x4s9tjvj2rQNgsm','635442322','describe',1,1,'IT Dev'),(6,'chi','chikieuurlphoto','chhikieu@gmail.com','$2a$10$O97CUf3IoZV3P7moTQ4naO0bK3/x6k6oX3Hes.9SmD9kYSc86xFcy','635442322','describe',0,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_course`
--

DROP TABLE IF EXISTS `user_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_course_fk_User` (`user_id`),
  KEY `user_course_fk_course` (`course_id`),
  CONSTRAINT `user_course_fk_User` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_course_fk_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_course`
--

LOCK TABLES `user_course` WRITE;
/*!40000 ALTER TABLE `user_course` DISABLE KEYS */;
INSERT INTO `user_course` VALUES (1,1,2),(2,2,2),(3,3,3),(4,4,3),(5,5,4);
/*!40000 ALTER TABLE `user_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-27  0:47:06
