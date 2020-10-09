CREATE TABLE `application_log` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `details` varchar(255) NOT NULL,
  `event` varchar(255) NOT NULL
);

CREATE TABLE `built_with` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `version` varchar(255) DEFAULT NULL
);

CREATE TABLE `application_settings` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `appKey` varchar(255) NOT NULL,
  `appValue` varchar(255) NOT NULL
);