--
-- Table structure for table `geo`
--

CREATE TABLE `example_geo` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL
);

--
-- Table structure for table `address`
--

 CREATE TABLE `example_address` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `suite` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `geo_id` bigint(20) DEFAULT NULL
);

ALTER TABLE example_address ADD FOREIGN KEY (geo_id) REFERENCES example_geo(id);

--
-- Table structure for table `person`
--

CREATE TABLE `example_person` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `age` int(11) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email_primary` varchar(255) NOT NULL UNIQUE,
  `email_secondary` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `is_adult` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` bigint(20) NOT NULL UNIQUE,
  `username` varchar(255) DEFAULT NULL UNIQUE,
  `address_id` bigint(20) DEFAULT NULL
);

ALTER TABLE example_person ADD FOREIGN KEY (address_id) REFERENCES example_address(id);