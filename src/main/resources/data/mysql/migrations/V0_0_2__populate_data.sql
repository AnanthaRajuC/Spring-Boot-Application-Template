/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure
 */

/*
 * Data
 */
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Dependency Management','https://maven.apache.org/','Maven','3.5.2');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Java™ Platform, Standard Edition Development Kit','http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html','JDK','9.0.1');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Framework to ease the bootstrapping and development of new Spring Applications','https://spring.io/projects/spring-boot','Spring Boot	','2.0.4');

INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Open-Source Relational Database Management System','https://www.mysql.com/','MySQL','5.7.21');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Version control for database','https://flywaydb.org/','Flyway','6.5.5');

INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.','https://projectlombok.org/','Lombok','1.18.12');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Free and Open-Source distributed version control system','https://git-scm.com/','git','2.9.0.windows.1');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('API Development Environment (ADE)','https://www.getpostman.com/','Postman','v6.2.3');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('API Design, Development, Documentation, Testing & Governance','https://swagger.io/','Swagger','2.7.0');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Monitoring system and time series database','https://prometheus.io/','Prometheus','2.3.2');

INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('A hosted continuous integration service used to build and test software projects hosted at GitHub and Bitbucket','https://travis-ci.org/','Travis CI','SaaS');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Automated dependency updates','https://dependabot.com/','Dependabot','SaaS');

INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('open-source CSS framework directed at responsive, mobile-first front-end web development','https://getbootstrap.com/','Bootstrap','4.5.2');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('An extended table to the integration with some of the most widely used CSS frameworks','https://bootstrap-table.com/','Bootstrap Table','1.17.1');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('An automatic table of contents generator, using Bootstrap 3 or 4','https://afeld.github.io/bootstrap-toc/','Bootstrap ToC','');

INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('A dialect for Thymeleaf that allows you to use attributes with a "with" prefix to avoid having long "th:with"-expressions.','https://github.com/Antibrumm/thymeleaf-extras-with-dialect','Thymeleaf With Dialect','3.0.0');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('A dialect for Thymeleaf that lets you build layouts and reusable templates in order to improve code reuse.','https://github.com/ultraq/thymeleaf-layout-dialect','Thymeleaf Layout Dialect','2.4.1');
INSERT INTO `built_with` (`description`,`link`,`name`,`version`) VALUES ('Modern server-side Java template engine for both web and standalone environments','https://www.thymeleaf.org/','Thymeleaf','3.0.11');
/*
 * Data
 */
INSERT INTO `application_settings` (`appKey`,`appValue`) VALUES ('applicationName','sbat');
INSERT INTO `application_settings` (`appKey`,`appValue`) VALUES ('applicationVersion','1.0');
INSERT INTO `application_settings` (`appKey`,`appValue`) VALUES ('postmanEchoBaseUrl','https://postman-echo.com');
INSERT INTO `application_settings` (`appKey`,`appValue`) VALUES ('postmanEchoGETurl','https://postman-echo.com/get');
INSERT INTO `application_settings` (`appKey`,`appValue`) VALUES ('postmanEchoPOSTpath','/post');

/*
 * Data
 */
INSERT INTO `geo` (`lat`,`lng`) VALUES ('-43.9589','-34.4628');

/*
 * Data
 */
INSERT INTO `address` (`city`,`street`,`suite`,`zipcode`,`geo_id`) VALUES ('Wisokyburghh','Jane Plains','Suite 779','90565-7771',1);

/*
 * Data
 */
INSERT INTO `person` (`age`,`dob`,`email_primary`,`email_secondary`,`gender`,`is_adult`,`name`,`password`,`phone`,`username`,`address_id`) VALUES (25,'2006-12-25','jane3.howell@gmail.com','jane1.howell@gmail.com','MALE',1,'Jane3','password',9191919195,'jane3',1);
