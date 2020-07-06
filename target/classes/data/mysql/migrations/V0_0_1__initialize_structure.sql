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

CREATE TABLE `application_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `appKey` varchar(255) NOT NULL,
  `appValue` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

 /*
 * Structure
 */
create table `person` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `age` integer DEFAULT NULL,
   `gender` varchar(255) DEFAULT NULL,
   `name` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;