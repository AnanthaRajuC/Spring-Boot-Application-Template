/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

/*
 * Data
 */
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Dependency Management','https://maven.apache.org/','Maven','3.5.2','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Java™ Platform, Standard Edition Development Kit','http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html','JDK','9.0.1','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Framework to ease the bootstrapping and development of new Spring Applications','https://spring.io/projects/spring-boot','Spring Boot	','2.0.4','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Open-Source Relational Database Management System','https://www.mysql.com/','MySQL','5.7.21','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Version control for database','https://flywaydb.org/','Flyway','6.5.5','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.','https://projectlombok.org/','Lombok','1.18.12','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Free and Open-Source distributed version control system','https://git-scm.com/','git','2.9.0.windows.1','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('API Development Environment (ADE)','https://www.getpostman.com/','Postman','v6.2.3','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('API Design, Development, Documentation, Testing & Governance','https://swagger.io/','Swagger','2.7.0','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Monitoring system and time series database','https://prometheus.io/','Prometheus','2.3.2','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('A hosted continuous integration service used to build and test software projects hosted at GitHub and Bitbucket','https://travis-ci.org/','Travis CI','SaaS','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Automated dependency updates','https://dependabot.com/','Dependabot','SaaS','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('open-source CSS framework directed at responsive, mobile-first front-end web development','https://getbootstrap.com/','Bootstrap','4.5.2','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('An extended table to the integration with some of the most widely used CSS frameworks','https://bootstrap-table.com/','Bootstrap Table','1.17.1','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('An automatic table of contents generator, using Bootstrap 3 or 4','https://afeld.github.io/bootstrap-toc/','Bootstrap ToC','','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('A dialect for Thymeleaf that allows you to use attributes with a "with" prefix to avoid having long "th:with"-expressions.','https://github.com/Antibrumm/thymeleaf-extras-with-dialect','Thymeleaf With Dialect','3.0.0','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('A dialect for Thymeleaf that lets you build layouts and reusable templates in order to improve code reuse.','https://github.com/ultraq/thymeleaf-layout-dialect','Thymeleaf Layout Dialect','2.4.1','Installation','Installation');
INSERT INTO `sbat_built_with` (`description`,`link`,`name`,`version`,`created_by`,`last_modified_by`) VALUES ('Modern server-side Java template engine for both web and standalone environments','https://www.thymeleaf.org/','Thymeleaf','3.0.11','Installation','Installation');

/*
 * Data
 */
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('applicationName','sbat','Name of the application','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('applicationVersion','1.0','Version of the application','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('apiVersion','2020-09-20','Version of the API','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('releaseVersion','3.1.3','Release version of the API','Installation','Installation');

INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('postmanEchoBaseUrl','https://postman-echo.com','Postman URL to test the HTTP requests','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('postmanEchoGETurl','https://postman-echo.com/get','Postman URL to test the GET request','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('postmanEchoPOSTpath','/post','Postman path to test the POST request','Installation','Installation');

INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('keystoreFileName','redditclone.jks','Java Keystore File Name','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('keystoreAlias','redditclone','Alias of the Java Keystore','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('keystorePassword','abcd1234','Password of the Java Keystore','Installation','Installation');

INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('verificationTokenValidity','10','Duration of the verification token validity - In minutes','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('jwtExpirationTime','300','Duration of JWT validity - In seconds','Installation','Installation');

INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('mailFrom','sbat@email.com','Account verification mail from address','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('mailReplyTo','sbat@email.com','Account verification mail reply to address','Installation','Installation');
INSERT INTO `sbat_settings` (`app_key`,`app_value`,`description`,`created_by`,`last_modified_by`) VALUES ('mailSubject','Spring Boot Application Template','Account verification mail subject','Installation','Installation');
