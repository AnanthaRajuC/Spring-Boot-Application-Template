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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `application_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `appKey` varchar(255) NOT NULL,
  `appValue` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*
 * Structure
 */
CREATE TABLE `geo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*
 * Structure
 */
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `suite` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `geo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKco48e3q5mlq831ijyssn2t25q` (`geo_id`),
  CONSTRAINT `FKco48e3q5mlq831ijyssn2t25q` FOREIGN KEY (`geo_id`) REFERENCES `geo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*
 * Structure
 */
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email_primary` varchar(255) NOT NULL,
  `email_secondary` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `is_adult` bit(1) NOT NULL,
  `name` varchar(15) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `username` varchar(15) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cb2kxuy8vrw12d8wn5tc3x7uq` (`email_primary`),
  UNIQUE KEY `UK_24t7n7hhuvwaqw92b2t514m33` (`phone`),
  UNIQUE KEY `UK_n0i6d7rc1hqkjivk494g8j2qd` (`username`),
  KEY `FKk7rgn6djxsv2j2bv1mvuxd4m9` (`address_id`),
  CONSTRAINT `FKk7rgn6djxsv2j2bv1mvuxd4m9` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;