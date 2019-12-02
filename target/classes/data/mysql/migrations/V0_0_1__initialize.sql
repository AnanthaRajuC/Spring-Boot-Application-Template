/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

 /*
 * Structure
 */
CREATE TABLE `application_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `details` varchar(255) NOT NULL,
  `event` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
 
 /*
 * Structure
 */
 CREATE TABLE `built_with` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

/*
 * Data
 */
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (1,'Dependency Management','https://maven.apache.org/','Maven','3.5.2');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (2,'Java™ Platform, Standard Edition Development Kit','http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html','JDK','9.0.1');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (3,'Framework to ease the bootstrapping and development of new Spring Applications','https://spring.io/projects/spring-boot','Spring Boot	','2.0.4');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (4,'Open-Source Relational Database Management System','https://www.mysql.com/','MySQL','5.7.21');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (5,'Free and Open-Source distributed version control system','https://git-scm.com/','git','2.9.0.windows.1');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (6,'API Development Environment (ADE)','https://www.getpostman.com/','Postman','v6.2.3');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (7,'API Design, Development, Documentation, Testing & Governance','https://swagger.io/','Swagger','2.7.0');
INSERT INTO `built_with` (`id`,`description`,`link`,`name`,`version`) VALUES (8,'Monitoring system and time series database','https://prometheus.io/','Prometheus','2.3.2');

