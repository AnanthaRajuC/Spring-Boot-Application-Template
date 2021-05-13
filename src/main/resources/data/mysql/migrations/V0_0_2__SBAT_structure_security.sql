--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `isAccountNonExpired` bit(1) DEFAULT NULL,
  `isAccountNonLocked` bit(1) DEFAULT NULL,
  `isCredentialsNonExpired` bit(1) DEFAULT NULL,
  `isEnabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Application Users.';

--
-- Table structure for table `security_permission`
--

CREATE TABLE `sbat_auth_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='List of Permissions.';

--
-- Table structure for table `security_role`
--

CREATE TABLE `sbat_auth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='List of Roles.';

--
-- Table structure for table `security_permission_role`
--

CREATE TABLE `sbat_auth_permission_role` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK3tuvkbyi6wcytyg21hvpd6txw` (`permission_id`),
  KEY `FK50sfdcvbvdaclpn7wp4uop4ml` (`role_id`),
  CONSTRAINT `FK3tuvkbyi6wcytyg21hvpd6txw` FOREIGN KEY (`permission_id`) REFERENCES `sbat_auth_permission` (`id`),
  CONSTRAINT `FK50sfdcvbvdaclpn7wp4uop4ml` FOREIGN KEY (`role_id`) REFERENCES `sbat_auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Role and Permission mapping.'; 

--
-- Table structure for table `security_role_user`
--

CREATE TABLE `sbat_auth_role_user` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKiqpmjd2qb4rdkej916ymonic6` (`role_id`),
  KEY `FK4320p8bgvumlxjkohtbj214qi` (`user_id`),
  CONSTRAINT `FK4320p8bgvumlxjkohtbj214qi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKiqpmjd2qb4rdkej916ymonic6` FOREIGN KEY (`role_id`) REFERENCES `sbat_auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User and Role Mapping';

--
-- Table structure for table `security_verification_token`
--

CREATE TABLE `sbat_auth_verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdn0mss276m9jdobfhhn2qogw` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Application Log';

--
-- Table structure for table `security_refresh_token`
--

CREATE TABLE `sbat_auth_refresh_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID for each entry.',
  `created_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that created the entity containing the field.',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was created.',
  `last_modified_by` varchar(255) NOT NULL COMMENT 'Field representing the principal that recently modified the entity containing the field.',
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Field representing the date the entity containing the field was recently modified.',
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User and Account Verification Token mapping.';