/*
 * Structure
 */
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
  `username` varchar(255) DEFAULT NULL
  );

ALTER TABLE `user` ADD CONSTRAINT username_UNIQUE UNIQUE(username);

create table "permission" (
  "id" bigint not null,
  "created_by" varchar(255) not null,
  "created_date" timestamp not null default current_timestamp(),
  "last_modified_by" varchar(255) not null,
  "last_modified_date" timestamp not null default current_timestamp(),
  "name" varchar(255) null default null,
  primary key ("id")
);
create table "role" (
  "id" bigint not null,
  "created_by" varchar(255) not null,
  "created_date" timestamp not null default current_timestamp(),
  "last_modified_by" varchar(255) not null,
  "last_modified_date" timestamp not null default current_timestamp(),
  "name" varchar(255) null default null,
  primary key ("id")
);
create table "permission_role" (
  "role_id" bigint not null,
  "permission_id" bigint not null,
  constraint "FK3tuvkbyi6wcytyg21hvpd6txw"
    foreign key ("permission_id")
    references "permission" ("id"),
  constraint "FK50sfdcvbvdaclpn7wp4uop4ml"
    foreign key ("role_id")
    references "role" ("id"),
  index "FK3tuvkbyi6wcytyg21hvpd6txw" ("permission_id"),
  index "FK50sfdcvbvdaclpn7wp4uop4ml" ("role_id")
);
create table "role_user" (
  "user_id" bigint not null,
  "role_id" bigint not null,
  constraint "FK4320p8bgvumlxjkohtbj214qi"
    foreign key ("user_id")
    references "user" ("id"),
  constraint "FKiqpmjd2qb4rdkej916ymonic6"
    foreign key ("role_id")
    references "role" ("id"),
  index "FKiqpmjd2qb4rdkej916ymonic6" ("role_id"),
  index "FK4320p8bgvumlxjkohtbj214qi" ("user_id")
);