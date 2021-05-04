--
-- Table structure for table `example_geo`
--

CREATE TABLE `example_geo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `example_address`
--

 CREATE TABLE `example_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `suite` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `geo_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKco48e3q5mlq831ijyssn2t25q` (`geo_id`),
  CONSTRAINT `FKco48e3q5mlq831ijyssn2t25q` FOREIGN KEY (`geo_id`) REFERENCES `example_geo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
 
--
-- Table structure for table `example_person`
--

CREATE TABLE `example_person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `age` int(11) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email_primary` varchar(255) NOT NULL,
  `email_secondary` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `is_adult` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cb2kxuy8vrw12d8wn5tc3x7uq` (`email_primary`),
  UNIQUE KEY `UK_24t7n7hhuvwaqw92b2t514m33` (`phone`),
  UNIQUE KEY `UK_n0i6d7rc1hqkjivk494g8j2qd` (`username`),
  KEY `FKk7rgn6djxsv2j2bv1mvuxd4m9` (`address_id`),
  CONSTRAINT `FKk7rgn6djxsv2j2bv1mvuxd4m9` FOREIGN KEY (`address_id`) REFERENCES `example_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;