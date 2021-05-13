/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

/*
-- create schema
CREATE SCHEMA [schema_name];

-- use schema
USE [schema_name];

-- Create user 
create user 'USERNAME'@'IPADDRESS' identified by 'PASSWORD';

-- Grant privileges to user
grant all privileges on *.* to 'USERNAME'@'IPADDRESS' with grant option;
 */

--
-- Table structure for table `sbat_log`
--

 CREATE TABLE `sbat_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `details` varchar(255) NOT NULL COMMENT 'Application Event Details.',
  `event` varchar(255) NOT NULL COMMENT 'Application Event.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Application Log.';

--
-- Table structure for table `built_with`
--

 CREATE TABLE `sbat_built_with` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='List of tools used to build the app.';

--
-- Table structure for table `sbat_settings`
--

 CREATE TABLE `sbat_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `app_key` varchar(255) NOT NULL,
  `app_value` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Application Settings.';