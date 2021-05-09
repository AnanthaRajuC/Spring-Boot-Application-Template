--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_account_non_expired` bit(1) DEFAULT NULL,
  `is_account_non_locked` bit(1) DEFAULT NULL,
  `is_credentials_non_expired` bit(1) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL UNIQUE,
  `email` varchar(255) DEFAULT NULL
);

--
-- Table structure for table `permission`
--

CREATE TABLE `sbat_auth_permission` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL
);

--
-- Table structure for table `role`
--

CREATE TABLE `sbat_auth_role` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) DEFAULT NULL
);

--
-- Table structure for table `permission_role`
--

create table sbat_auth_permission_role (
role_id bigint not null,
permission_id bigint not null
);

ALTER TABLE sbat_auth_permission_role ADD FOREIGN KEY (permission_id) REFERENCES sbat_auth_permission(id);
ALTER TABLE sbat_auth_permission_role ADD FOREIGN KEY (role_id) REFERENCES sbat_auth_role(id);

--
-- Table structure for table `role_user`
--

create table sbat_auth_role_user (
user_id bigint not null,
role_id bigint not null
);

ALTER TABLE sbat_auth_role_user ADD FOREIGN KEY (user_id) REFERENCES sbat_auth_permission(id);
ALTER TABLE sbat_auth_role_user ADD FOREIGN KEY (role_id) REFERENCES sbat_auth_role(id);

--
-- Table structure for table `verification_token`
--

CREATE TABLE `sbat_auth_verification_token` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
);

ALTER TABLE sbat_auth_verification_token ADD FOREIGN KEY (user_id) REFERENCES user(id);

--
-- Table structure for table `refresh_token`
--

CREATE TABLE `sbat_auth_refresh_token` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(255) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
);