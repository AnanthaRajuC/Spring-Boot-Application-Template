--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isAccountNonExpired` bit(1) DEFAULT NULL,
  `isAccountNonLocked` bit(1) DEFAULT NULL,
  `isCredentialsNonExpired` bit(1) DEFAULT NULL,
  `isEnabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL UNIQUE,
  `email` varchar(255) DEFAULT NULL
);

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
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

CREATE TABLE `role` (
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

create table permission_role (
role_id bigint not null,
permission_id bigint not null
);

ALTER TABLE permission_role ADD FOREIGN KEY (permission_id) REFERENCES permission(id);
ALTER TABLE permission_role ADD FOREIGN KEY (role_id) REFERENCES role(id);

--
-- Table structure for table `role_user`
--

create table role_user (
user_id bigint not null,
role_id bigint not null
);

ALTER TABLE role_user ADD FOREIGN KEY (user_id) REFERENCES permission(id);
ALTER TABLE role_user ADD FOREIGN KEY (role_id) REFERENCES role(id);

--
-- Table structure for table `verification_token`
--

CREATE TABLE `verification_token` (
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

ALTER TABLE verification_token ADD FOREIGN KEY (user_id) REFERENCES user(id);

--
-- Table structure for table `refresh_token`
--

CREATE TABLE `refresh_token` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(255) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ;