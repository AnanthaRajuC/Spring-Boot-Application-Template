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
-- Table structure for table `application_log`
--

 CREATE TABLE `application_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `details` varchar(255) NOT NULL,
  `event` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `built_with`
--

 CREATE TABLE `built_with` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `application_settings`
--

 CREATE TABLE `application_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `app_key` varchar(255) NOT NULL,
  `app_value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;