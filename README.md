# Spring Boot Application Template/Starter-Project [![Build Status](https://travis-ci.org/Spring-Boot-Framework/Spring-Boot-Application-Template.svg?branch=master)](https://travis-ci.org/Spring-Boot-Framework/Spring-Boot-Application-Template)

[![DepShield Badge](https://depshield.sonatype.org/badges/owner/repository/depshield.svg)](https://depshield.github.io)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template)
[![BCH compliance](https://bettercodehub.com/edge/badge/Spring-Boot-Framework/Spring-Boot-Application-Template?branch=master)](https://bettercodehub.com/)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](code_of_conduct.md)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template?ref=badge_shield)
[![GitHub issues](https://img.shields.io/github/issues/Spring-Boot-Framework/Spring-Boot-Application-Template)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/issues)
[![GitHub forks](https://img.shields.io/github/forks/Spring-Boot-Framework/Spring-Boot-Application-Template)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/network)
[![GitHub stars](https://img.shields.io/github/stars/Spring-Boot-Framework/Spring-Boot-Application-Template)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/stargazers)
[![Open Source Helpers](https://www.codetriage.com/spring-boot-framework/spring-boot-application-template/badges/users.svg)](https://www.codetriage.com/spring-boot-framework/spring-boot-application-template)
[![codecov](https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template/branch/master/graph/badge.svg)](https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4bc5385252064f0e908f445b1eba184b)](https://www.codacy.com/gh/Spring-Boot-Framework/Spring-Boot-Application-Template?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Spring-Boot-Framework/Spring-Boot-Application-Template&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Spring-Boot-Framework_Spring-Boot-Application-Template&metric=alert_status)](https://sonarcloud.io/dashboard?id=Spring-Boot-Framework_Spring-Boot-Application-Template)
[![](https://images.microbadger.com/badges/image/anantha/spring-boot-application-template.svg)](https://microbadger.com/images/anantha/spring-boot-application-template)
[![](https://images.microbadger.com/badges/version/anantha/spring-boot-application-template.svg)](https://microbadger.com/images/anantha/spring-boot-application-template)
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/485bb945b088c6fd471e)

<p align="center">
	<a alt="GitHub tag (latest SemVer)">
        <img src="https://img.shields.io/github/tag/Spring-Boot-Framework/Spring-Boot-Application-Template.svg" />
    </a>
	<a alt="Docker Image Size (latest by date)">
        <img src="https://img.shields.io/docker/image-size/anantha/spring-boot-application-template" />
    </a>
	<a alt="GitHub code size in bytes">
        <img src="https://img.shields.io/github/languages/code-size/Spring-Boot-Framework/Spring-Boot-Application-Template" />
    </a>
	<a alt="GitHub top language">
        <img src="https://img.shields.io/github/languages/top/Spring-Boot-Framework/Spring-Boot-Application-Template.svg" />
    </a>
	<a alt="GitHub pull requests">
        <img src="https://img.shields.io/github/issues-pr-raw/Spring-Boot-Framework/Spring-Boot-Application-Template" />
    </a>
	<a alt="GitHub repo size">
        <img src="https://img.shields.io/github/repo-size/Spring-Boot-Framework/Spring-Boot-Application-Template" />
    </a>
	<a alt="GitHub language count">
        <img src="https://img.shields.io/github/languages/count/Spring-Boot-Framework/Spring-Boot-Application-Template" />
    </a>
	<a alt="GitHub last commit">
        <img src="https://img.shields.io/github/last-commit/anantharajuc/Spring-Boot-Application-Template" />
    </a>
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Bootstrap">
        <img src="https://img.shields.io/badge/Bootstrap-v4.0.0-yellowgreen.svg">
    </a>
    <a alt="Material">
        <img src="https://img.shields.io/badge/Material%20Design-UI-orange.svg">  
    </a>      
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v19-yellowgreen.svg" />
    </a>
    <a alt="License">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
    </a>
</p>

[![Twitter Follow](https://img.shields.io/twitter/follow/anantharajuc?label=follow%20me&style=social)](https://twitter.com/anantharajuc)

## Important Note: This project's new milestone is to release version 1, so stay tuned.

## Purpose

The only thing better than a Maven archetype is a repo you can fork with everything already setup to speed up the creation of new Spring Boot based Web applications, just fork-and-code.

This repository contains a recipe/scaffolding for bootstrapping a Web Application with the features & Technology stack listed below. Delete the sample code (or keep it.) and add your own, you’re good to go.

## Table of Contents

- [Application screenshots](#application-screenshots)
- [Technology stack & other Open-source libraries](#technology-stack---other-open-source-libraries)
  * [Data](#data)
  * [Client - Frontend/UI](#client---frontend-ui)
  * [Server - Backend](#server---backend)
  * [Libraries and Plugins](#libraries-and-plugins)
  * [Others](#others)
  * [External Tools & Services](#external-tools---services)
- [Features and To-Do](#features-and-to-do)
- [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [EER Diagram](#eer-diagram)
- [Installing](#installing)
    + [Running the application with IDE](#running-the-application-with-ide)
    + [Running the application with Maven](#running-the-application-with-maven)
    + [Running the application with Executable JAR](#running-the-application-with-executable-jar)
      - [Accessing Data in H2 Database](#accessing-data-in-h2-database)
        * [H2 Console](#h2-console)
    + [Running the application via docker container](#running-the-application-via-docker-container)
      - [Basic Docker commands for reference](#basic-docker-commands-for-reference)
      - [Commands to run the MySQL docker image](#commands-to-run-the-mysql-docker-image)
      - [Commands to run the docker image of app with MySQL docker image](#commands-to-run-the-docker-image-of-app-with-mysql-docker-image)
      - [Connecting to the MySQL docker image via CLI](#connecting-to-the-mysql-docker-image-via-cli)
      - [Basic MySQL commands for reference](#basic-mysql-commands-for-reference)
      - [Docker Hub Commands for Reference](#docker-hub-commands-for-reference)
- [Deployment](#deployment)
  * [Deploying to Heroku](#deploying-to-heroku)
- [Code Coverage](#code-coverage)
  * [Cobertura](#cobertura)
- [Testing API](#testing-api)
  * [Testing with Postman Runner](#testing-with-postman-runner)
  * [Testing with Maven](#testing-with-maven)
  * [Basic Load Testing](#basic-load-testing)
- [Security](#security)
  * [Looking for something in particular?](#looking-for-something-in-particular-)
    + [Sample Valid JSON Request Bodys](#sample-valid-json-request-bodys)
      - [<a id="signup">Signup -> /api/auth/signup</a>](#-a-id--signup--signup-----api-auth-signup--a-)
      - [<a id="login">Login -> /api/auth/login</a>](#-a-id--login--login-----api-auth-login--a-)
      - [<a id="subreddit">Subreddit -> /api/subreddit</a>](#-a-id--subreddit--subreddit-----api-subreddit--a-)
      - [<a id="refresh-token">Refresh Token -> /api/auth/refresh/token</a>](#-a-id--refresh-token--refresh-token-----api-auth-refresh-token--a-)
  * [API Rate Limiting](#api-rate-limiting)
  * [Preventing Brute Force Authentication Attempts](#preventing-brute-force-authentication-attempts)
  * [Session Timeout](#session-timeout)
- [Explore Rest APIs](#explore-rest-apis)
  * [URLs](#urls)
  * [Other URLs](#other-urls)
  * [Actuator](#actuator)
  * [Person URLs](#person-urls)
    + [Accessible to **johndoe** user only](#accessible-to---johndoe---user-only)
  * [Person Management URLs](#person-management-urls)
    + [Role and Permission based secure access to **AdminUser** and **AdminTrainee** users](#role-and-permission-based-secure-access-to---adminuser---and---admintrainee---users)
    + [Sample Valid JSON Request Bodys](#sample-valid-json-request-bodys-1)
      - [<a id="personcreate">Create Person -> /api/person</a>](#-a-id--personcreate--create-person-----api-person--a-)
- [Documentation](#documentation)
- [Internationalization (i18n)](#internationalization--i18n-)
- [Files and Directories Structure](#files-and-directories-structure)
  * [Packages](#packages)
- [Reporting Issues/Suggest Improvements](#reporting-issues-suggest-improvements)
- [The End](#the-end)
- [FOSSA third-party code, license compliance and vulnerabilities](#fossa-third-party-code--license-compliance-and-vulnerabilities)

## Application screenshots

<img src="documents\images\app-ui\home.PNG"/>
<img src="documents\images\app-ui\login.PNG"/>
<img src="documents\images\app-ui\about.PNG"/>

## Technology stack & other Open-source libraries

### Data

* 	[Flyway](https://flywaydb.org/) - Version control for database
* 	[MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System
* 	[H2 Database Engine](https://www.h2database.com/html/main.html) - Java SQL database. Embedded and server modes; in-memory databases

### Client - Frontend/UI

* 	[Bootstrap](https://getbootstrap.com/) - Bootstrap is a free and open-source CSS framework directed at responsive, mobile-first front-end web development.
* 	[Bootstrap Table](https://bootstrap-table.com/) - An extended table to the integration with some of the most widely used CSS frameworks.
* 	[Thymeleaf](https://www.thymeleaf.org/) - Modern server-side Java template engine for both web and standalone environments.

### Server - Backend

* 	[JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management
* 	[JSON Web Token](https://www.jsonwebtoken.io/) - Encode or Decode JWTs

###  Libraries and Plugins

* 	[Bootstrap ToC](https://afeld.github.io/bootstrap-toc/) - Table of Contents plugin for Bootstrap
* 	[Thymeleaf With Dialect](https://github.com/Antibrumm/thymeleaf-extras-with-dialect) - A dialect for Thymeleaf that allows you to use attributes with a "with" prefix to avoid having long "th:with"-expressions.
* 	[Thymeleaf Layout Dialect](https://github.com/ultraq/thymeleaf-layout-dialect) - A dialect for Thymeleaf that lets you build layouts and reusable templates in order to improve code reuse.
* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* 	[Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) - Java rate limiting library based on token/leaky-bucket algorithm.

### Others 

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system
* 	[Prometheus](https://prometheus.io/) - Monitoring system and time series database
* 	[Docker](https://www.docker.com/) - A set of platform as a service products that use OS-level virtualization to deliver software in packages called containers.

### External Tools & Services

*   [Mailtrap](https://mailtrap.io/) - Safe Email Testing for Staging & Development.
* 	[Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* 	[Postman Echo](https://docs.postman-echo.com/?version=latest) - A service that can be used to test your REST clients and make sample API calls. It provides endpoints for GET, POST, PUT, various auth mechanisms and other utility endpoints.
* 	[Travis CI](https://travis-ci.org/github/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted continuous integration service used to build and test software projects hosted at GitHub and Bitbucket.
* 	[Codecov](https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template) - A hosted tool that is used to measure the test coverage of your codebase.
*	[Dependabot](https://dependabot.com/) - Automated dependency updates.
*	[FOSSA](https://fossa.com/) - Scalable, end-to-end management for third-party code, license compliance and vulnerabilities.
*	[sonarcloud](https://sonarcloud.io/dashboard?id=Spring-Boot-Framework_Spring-Boot-Application-Template) - Cloud-based code analysis service designed to detect code quality issues continuously ensuring the maintainability, reliability and security of code.
* 	[gitignore.io](https://www.toptal.com/developers/gitignore/api/java,eclipse,intellij) - Create useful .gitignore files for your project.

## Features and To-Do

* 	[x] Logger (Console, File)
* 	[x] [Content Negotiation](https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc)
* 	[x] [Dark Mode](https://en.wikipedia.org/wiki/Light-on-dark_color_scheme)
* 	[x] [Spring Security](https://spring.io/projects/spring-security) RBAC, Session Timeout
* 	[x] API [Rate Limiting](https://en.wikipedia.org/wiki/Rate_limiting)
* 	[x] [JPA Auditing via AuditorAware Interface](https://docs.spring.io/spring-data/jpa/docs/1.7.0.DATAJPA-580-SNAPSHOT/reference/html/auditing.html)
* 	[x] [Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles) (dev, production, qa, staging, test)
* 	[x] [Docker](https://www.docker.com/)
* 	[x] [Caching](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html)
* 	[x] [HATEOS (Hypermedia as the Engine of Application State)](https://en.wikipedia.org/wiki/HATEOAS)
* 	[x] Software documentation - [Swagger](https://swagger.io/), [Javadoc](https://en.wikipedia.org/wiki/Javadoc), [Postman Collection](https://www.postman.com/collection/)
* 	[x] [HTTPS](https://en.wikipedia.org/wiki/HTTPS) with (self-signed certificate)[https://en.wikipedia.org/wiki/Self-signed_certificate]
* 	[x] [JSON Web Token](https://www.jsonwebtoken.io/) based authentication
* 	[ ] Quartz Scheduler
* 	[ ] Replace Docker with [jib](https://github.com/GoogleContainerTools/jib)
*   [ ] Unit Tests, Integration Tests
* 	[ ] Shut down app on button click via actuator url 
* 	[ ] Spring Boot Admin
* 	[ ] NoSQL (MongoDB)
* 	[ ] Multitenancy
* 	[ ] Micrometer
* 	[ ] Grafna
* 	[ ] Spring Retry

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

*	To activate the accounts of registered users, an email with activation link is sent to the email provided during the user signup stage. An **SMTP** is required for the same.

	[Mailtrap](https://mailtrap.io/) or any other service like **Gmail**, etc., can be used to create an SMTP.
	
	update the **springMailHost**, **springMailPort**, **springMailProtocol**, **springMailUsername** and **springMailPassword** details in the **application_settings** section of **/resources/data/mysql/migrations/V0_0_4__populate_data.sql**

*	You need to have **MySQL** installed on your machine to run the application in **`dev`** profile. Using the `MySQL Workbench` or on any other MySQL client/console, create a database/schema named `sbat`. 

~~~sql
-- create schema
CREATE SCHEMA sbat;

-- use schema
USE sbat;

-- Create user 
create user 'sbat'@'localhost' identified by 'sbat';

-- Grant privileges to user
grant all privileges on *.* to 'sbat'@'localhost' with grant option;
~~~

After creating the database/schema, you need to add your **MySQL** `username` and `password` in the `application-dev.properties` file on `src/main/resource`. The lines that must be modified are as follows:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sbat?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=sbat
spring.datasource.password=sbat
```

*	A Java Keystore File is required to generate JSON Web Token.

```shell
keytool -genkey -alias redditclone -keyalg RSA -keystore redditclone.jks -keysize 2048
```

<img src="documents\images\settings\reddit-clone-jks-generation.PNG"/>

### EER Diagram

<img src="documents\images\settings\SBAT-EER-Diagram.png"/>

## Installing

*	Default active profile is **`test`**. When the application is running, **Flyway** will create the necessary tables and system data along with sample data. In the **`test`** profile, the application uses **H2** database (data in memory).

* 	URL to access application UI: **http://localhost:8080/sbat/index** or **https://192.168.99.102:8080/sbat/index** if **SSL** is enabled.

#### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.arc.sbtest.SBtemplateApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application

#### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template.git
$ cd Spring-Boot-Application-Template
$ mvn spring-boot:run
```

#### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template.git
$ cd Spring-Boot-Application-Template
$ mvn package -DskipTests
$ java -jar target/SBtemplate-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

##### Accessing Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:8080/h2-console/login.jsp** or **https://192.168.99.102:8080/h2-console/login.jsp** if **SSL** is enabled.

Fill the login form as follows and click on Connect:

* 	Saved Settings: **Generic H2 (Embedded)**
* 	Setting Name: **Generic H2 (Embedded)**
* 	Driver class: **org.h2.Driver**
* 	JDBC URL: **jdbc:h2:mem:sbat;MODE=MySQL**
* 	User Name: **sa**
* 	Password:

<img src="documents\images\h2db\h2-console-login.PNG"/>

<img src="documents\images\h2db\h2-console-main-view.PNG"/>

#### Running the application via docker container

* 	[anantha/spring-boot-application-template](https://hub.docker.com/r/anantha/spring-boot-application-template/tags) - DockerHub Image

DockerHub Pull Command if you want to directly pull the docker image of the application from Docker Hub.

```shell
docker pull anantha/spring-boot-application-template
```

**NOTE:** If you want to build a docker image from the source code, ensure you build a jar of the application before building a docker image.  

```shell
$ mvn package -Dmaven.test.skip=true     //skip all tests and build. The build once completed is available in **target** folder
```

```shell
$ mvn clean package                      //run all tests and build
```

On Windows machine use **Docker Quickstart Terminal** or, use **Windows Powershell** and navigate to the project folder where Dockerfile is present.

##### Basic Docker commands for reference

|                           Command                                  |                                     Description                               |
|--------------------------------------------------------------------|-------------------------------------------------------------------------------| 
|`docker-machine ip default`							             | check your docker IP default, usually it is **192.168.99.102**			     |
|`docker images`                                                     | take a look at the container images.                                          |
|`docker ps`                                                         | list all the running containers.                                              |
|`docker ps -a`                                                      | list all the containers, including the ones that have finished executing.     |
|`docker restart [container_name]`							         | restart the docker image			                             		         |
|`docker stats`							                             | Show CPU and memory usage of all running containers                 	         |
|`docker stats [container_name]`						             | Show CPU and memory usage of a particular running container                   |
|`docker stats [container1_name] [container2_name]`			         | Show CPU and memory usage of container1, container2                           |
|`docker top [container_name]`			                             | Show running processes in a container                                         |
|`docker system df`			                                         | Show storage usage                                                            |
|`docker logs [container_id]`			                             | list container logs                                                           |
|`docker logs [container_id] --tail N`                               | list container logs, **`--tail`** flag will show the last **N** lines of logs |   
|`docker logs [container_id] --since YYYY-MM-DD`                     | list container logs since a particular date                                   |
|`docker logs [container_id] --since YYYY-MM-DDTHH:MM:SS.000000000Z` | list container logs since a particular timestamp                              |

##### Commands to run the MySQL docker image

|                                                   Command                                                                                        |                                 Description                              |
|--------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------| 
|**`docker pull mysql:5.7`**							                                                                                           | pull a MySQL Docker Image                                 				  |
|`docker images`                                                                                                                                   | take a look at the container images. See if MySQL image is present       |
|**`docker run --name mysql-docker -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=sbat -e MYSQL_USER=sbat -e MYSQL_PASSWORD=sbat -d mysql:5.7`**| run the MySQL docker image                                               |

##### Commands to run the docker image of app with MySQL docker image

|                                                                  Command                                                                                              |                                                         Description                              |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------| 
|**`docker build -t spring-boot-application-template .`**                                                                                                               | **Build docker image of the project**                                                            |
|**`docker run -e "SPRING_PROFILES_ACTIVE=test" -p 8080:8080 --name spring-boot-application-template anantha/spring-boot-application-template:0.0.1-SNAPSHOT`**         | **DEV profile (H2DB)        : run the project's docker container by mapping docker to localhost**|	
|**`docker run -e "SPRING_PROFILES_ACTIVE=production" -p 8080:8080 --name spring-boot-application-template --link mysql-docker:mysql spring-boot-application-template`**| **PROCUCTION profile (MySQL): run the project's docker container by mapping docker to localhost**|
|**`docker stop [container_id]`**                                                                                                                                       | **stop a container**                                                                             |
|**`docker rm [container_name]`**                                                                                                                                       | **remove a container with a particular container name**                                          |
|`docker rm $(docker ps -aq)`                                                                                                                                           | stop and remove all containers                                                                   |
|`docker restart mysql-docker`																									                                        | restart the MySQL docker image																	 |

##### Connecting to the MySQL docker image via CLI 

|                                                           Command                                                              |                                                         Description                              |
|--------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------| 
|`docker exec mysql-docker mysql -usbat -psbat -e 'show databases;'`														     | connect to MySQL image without interactive CLI.													|
|`docker exec -it mysql-docker mysql -usbat -psbat -e 'show databases;'`														 | connect to MySQL image without interactive CLI.													|
|`docker exec -it mysql-docker mysql -usbat -psbat`																				 | connect to MySQL image via interactive CLI.														|

##### Basic MySQL commands for reference

|                Commands            |                   Description                  |
|------------------------------------|------------------------------------------------| 
|`show databases;`					 | lists the databases on the MySQL server host	  |
|`show schemas;`					 | a synonym for **show databases;**		      |
|`use [database_name];`				 | select any existing database in the SQL schema |
|`show tables;`						 | list tables in a Database	         		  |

**NOTE:** If you are facing any issues with accessing the application at **`localhost:8080`** while using **DockerToolBox** and **OracleVM VirtualBox**

In the Oracle VM VirtualBox:

*	Click the appropriate machine (probably the one labeled **default**)
*	**Settings**
*	**Network** > **Adapter 1** > **Advanced** > **Port Forwarding**
*	Click on **+** to add a new Rule
*	Set **Host Port** to **8080** and **Guest Port** to **8080**; be sure to leave **Host IP** and **Guest IP** empty

<img src="documents\images\tools\Oracle-VM-Virtualbox-Manager.PNG"/>

Reference: https://stackoverflow.com/a/45822356/3711562

##### Docker Hub Commands for Reference     

|                               Command                              |                         Description                               |
|--------------------------------------------------------------------|-------------------------------------------------------------------| 
|`docker logout`							                         | logout of Docker Hub from the local machine.                      |
|`docker login --username=YOUR_DOCKERHUB_USERNAME`	                 | login to Docker Hub from your machine.                            |
|`docker tag <existing-image> <hub-user>/<repo-name>[:<tag>]`        | re-tagging an existing local image					             |
|`docker commit <existing-container> <hub-user>/<repo-name>[:<tag>]` | commit changes					                                 |
|`docker push <hub-user>/<repo-name>:<tag>`                          | push this repository to the registry designated by its name or tag|

**Examples:**

*	re-tagging an existing local image : `docker tag spring-boot-application-template anantha/spring-boot-application-template:h2db-test-profile`
*	commit changes                     : `docker commit pedantic_turing anantha/spring-boot-application-template:h2db-test-profile`
*	docker push                        : `docker push anantha/spring-boot-application-template:h2db-test-profile`

## Deployment

### Deploying to Heroku

* 	Download and install the Heroku CLI
*	log in to your Heroku account **`heroku login`**
* 	set git remote heroku to the heroku app url,  example **`heroku git:remote -a spring-boot-app-template`**
*	**Add** and **Commit** any pending changes to git
*	push the code to heroku to deploy the app, example **`git push heroku master`**

## Code Coverage

### Cobertura

Generating code coverage reports

```shell
$ mvn cobertura:cobertura
```

This will create a detailed HTML style report showing code coverage statistics gathered via code instrumentation.

**Spring-Boot-Application-Template\target\site\cobertura**

## Testing API

### Testing with Postman Runner

Import the **Spring Boot Application Template API.postman_test_run** file into postman and run the API tests.

### Testing with Maven

*	Run only unit tests:

```shell
$ mvn clean test
```

### Basic Load Testing

Basic load testing for retrieving a `person` for a given `id` can be performed with the ApacheBench by executing the following command:

```shell
ab -n 10000 -c 100 -k http://localhost:8080/api/v1/person/1
```

* **-n 10000** is the number of requests to make
* **-c 100** is the number of concurrent requests to make at a time
* **-k** sends the **KeepAlive** header, which asks the web server to not shut down the connection after each request is done, but to instead keep reusing it

Result:

```
Benchmarking localhost (be patient)
Completed 1000 requests
Completed 2000 requests
Completed 3000 requests
Completed 4000 requests
Completed 5000 requests
Completed 6000 requests
Completed 7000 requests
Completed 8000 requests
Completed 9000 requests
Completed 10000 requests
Finished 10000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /api/v1/person/1
Document Length:        132 bytes

Concurrency Level:      100
Time taken for tests:   9.213 seconds
Complete requests:      10000
Failed requests:        0
Non-2xx responses:      10000
Keep-Alive requests:    0
Total transferred:      5330000 bytes
HTML transferred:       1320000 bytes
Requests per second:    1085.38 [#/sec] (mean)
Time per request:       92.133 [ms] (mean)
Time per request:       0.921 [ms] (mean, across all concurrent requests)
Transfer rate:          564.95 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    1   1.1      1      49
Processing:    13   91  30.1     85     523
Waiting:        0   68  29.3     65     472
Total:         14   91  30.1     85     523

Percentage of the requests served within a certain time (ms)
  50%     85
  66%     95
  75%    101
  80%    105
  90%    119
  95%    136
  98%    176
  99%    223
 100%    523 (longest request)
```

## Security

### Looking for something in particular?

[Role Based Authentication (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Permission Based Authentication (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Spring Method-Security with @PreAuthorize (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Database Authentication Authorization (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Role, Permission based User Authentication via MySQL](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Json Web Token - Role Based Access Controll via MySQL](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

Refer to the `ApplicationSecurityConfig` class in `io.github.anantharajuc.sbat.backend.security`.

*	A Java Keystore File is required to generate JSON Web Token.

```shell
keytool -genkey -alias redditclone -keyalg RSA -keystore redditclone.jks -keysize 2048
```

<img src="documents\images\settings\reddit-clone-jks-generation.PNG"/>

|     Username     | Password |     Role     |                      Permission                       |         Resource          |
|------------------|----------|--------------|-------------------------------------------------------|---------------------------|
|`johndoe`         |`password`|`PERSON`      |                                                       |`/api/v1/person`           |
|`AdminUser`       |`password`|`ADMIN`       |`PERSON_CREATE,PERSON_READ,PERSON_UPDATE,PERSON_DELETE`|`/management/api/v1/person`|
|`AdminTraineeUser`|`password`|`ADMINTRAINEE`|`PERSON_READ`                                          |`/management/api/v1/person`|

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/signup`                           | POST   |                                               | [JSON](#signup)           |
|`http://localhost:8080/api/v1/auth/verification/{verification-token}`| GET    |                                               |                           |
|`http://localhost:8080/api/v1/auth/login`                            | POST   |Bearer Token, Refresh Token is generated       | [JSON](#login)            |
|`http://localhost:8080/api/v1/subreddit`                             | POST   |Bearer Token should be passed for authorization| [JSON](#subreddit)        |
|`http://localhost:8080/api/v1/auth/refresh/token`                    | POST   |Refresh Token from login should be passed      | [JSON](#refresh-token)    |

#### Sample Valid JSON Request Bodys

##### <a id="signup">Signup -> /api/auth/signup</a>
```json
{
    "username":"johndoe",
    "email":"domain@example.com",
    "password":"abcd1234"
}
```

##### <a id="login">Login -> /api/auth/login</a>
```json
{
    "username":"johndoe",
    "password":"abcd1234"
}
```

##### <a id="subreddit">Subreddit -> /api/subreddit</a>
```json
{
    "name":"my-cool-subreddit",
    "description":"My subreddit for all thing cool."
}
```

##### <a id="refresh-token">Refresh Token -> /api/auth/refresh/token</a>
```json
{
    "token":"1178cd43-21d2-45b4-8b5f-c79aa1d5b76e",
    "username":"johndoe"
}
```

### API Rate Limiting

|     Tier   | API Request Cap |  API Key Prefix  |
|------------|-----------------|------------------|
|FREE        |     25          |     `null`       |
|BASIC       |     50          |     `PX001-`     |
|PROFESSIONAL|     75          |     `BX001-`     |

Rate Limiting header `X-api-key`

[Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) - Rate limiting library based on token/leaky-bucket algorithm - Refer `io.github.anantharajuc.sbat.backend.api.rate_limiting` package

### Preventing Brute Force Authentication Attempts

A basic solution for preventing brute force authentication attempts using Spring Security is implemented. The app keeps a record of the number of failed attempts originating from a single IP address. If that particular IP goes over a set number of requests – it will be blocked for a set amount of time.

Refer `io.github.anantharajuc.sbat.backend.security.authentication.LoginAttemptService`

### Session Timeout

If the application remains inactive for a specified period of time, the session will expire. The session after this period of time is considered invalid and the user has to login to the application again.

This value **server.servlet.session.timeout** can be configured in **application.properties** file

## Explore Rest APIs

The app defines following CRUD APIs. **If localhost doesn't work, use 192.168.99.102**

To enable SSL, toggle **server.ssl.enabled** to **true** and use the **https://** protocol in the URL instead of **http://**

Since the SSL certificate is self signed, turn off the **SSL certificate verification** option while interacting with the URLs via **Postman**

<img src="documents\images\tools\postman-ssl-certificate-verification.PNG"/>

### URLs

|                   URL                  | Method |          Remarks       |
|----------------------------------------|--------|------------------------|
|`http://localhost:8080/index`           | GET    | Home Page              |
|`http://localhost:8080/sbat/index`      | GET    | Home Page              |
|`http://localhost:8080/sbat/about`      | GET    | About Page             |
|`http://localhost:8080/sbat/tech-stack` | GET    | Technology Stack Table |
|`http://localhost:8080/sbat/close`      | GET    | Close App via Actuator |
|`http://localhost:8080/sbat/login`      | GET    | Login Page             |
|`http://localhost:8080/sbat/error`      | GET    | Custom Error Page      |

### Other URLs

|                           URL                                  | Method |
|----------------------------------------------------------------|--------|
|`http://localhost:8080/api/generic-hello`                       |   GET  | 
|`http://localhost:8080/api/personalized-hello/`                 |   GET  | 
|`http://localhost:8080/api/personalized-hello?name=spring-boot` |   GET  | 
|`http://localhost:8080/api/loggers`                             |   GET  | 

### Actuator

To monitor and manage your application

|              URL                          |Method|
|-------------------------------------------|------|
|`http://localhost:8080/actuator/`          |  GET |
|`http://localhost:8080/actuator/health`    |  GET |
|`http://localhost:8080/actuator/info`      |  GET |
|`http://localhost:8080/actuator/prometheus`|  GET |
|`http://localhost:8080/actuator/httptrace` |  GET |

### Person URLs

#### Accessible to **johndoe** user only

|                           URL                            |  Method |                                         Remarks                                     | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/person`                     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/api/v1/person`                     | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/api/v1/person/{id}`                | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/api/v1/person/{id}`                | PUT     | Update a person                                                                     |    [JSON](#personcreate)  |
|`http://localhost:8080/api/v1/person/{id}`                | DELETE  | Delete a person                                                                     |                           |

### Person Management URLs

#### Role and Permission based secure access to **AdminUser** and **AdminTrainee** users

|                          URL                             |  Method |                                       Remarks                                       | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/management/api/v1/person`          | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person`          | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/management/api/v1/person/{id}`     | PUT     | Update a person                                                                     |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | DELETE  | Delete a person                                                                     |                           |


#### Sample Valid JSON Request Bodys

##### <a id="personcreate">Create Person -> /api/person</a>
```json
{
	"name": "Jane",
	"username": "janejane",
	"emailPrimary": "jane1.howell@gmail.com",
	"emailSecondary": "jane2.howell@gmail.com",
	"phone":9191919191,
	"gender": "FEMALE",
	"age": 25,
	"password": "password",
	"dob":"25-12-2005",
	"isAdult":true,
	"address": {
		"street": "Jane Plains",
		"suite": "Suite 779",
		"city": "Wisokyburghh",
		"zipcode": "90565-7771",
		"geo": {
			"lat": "-43.9589",
			"lng": "-34.4628"
		}
	}
}
```

## Documentation

* 	[Postman Collection](https://documenter.getpostman.com/view/2449187/TVCe1UAk) - online, with code auto-generated snippets in cURL, jQuery, Ruby,Python Requests, Node, PHP and Go programming languages
* 	Postman Collection for offline testing is available in the postman folder.
* 	[Swagger](http://localhost:8080/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`- Documentation & Testing
* 	[Swagger](http://localhost:8080/v2/api-docs?group=Spring%20Boot%20Application%20Template) - `http://localhost:8080/v2/api-docs?group=Spring%20Boot%20Application%20Template`- Documentation & Testing
*	Find Java Doc in **javadoc** folder
* 	Java Doc is generated in `Spring-Boot-Application-Template\target\site\apidocs` folder using the Maven command 

```text
`mvn javadoc:javadoc`                   //Generate JavaDoc
```

## Internationalization (i18n)

This app can be adapted to various languages and regions without engineering changes. Textual elements, such as status messages and the GUI component labels, are not hardcoded in the program. Instead they are stored outside the source code and retrieved dynamically.

Refer `io.github.anantharajuc.sbat.backend.config.I18Nconfiguration`. The text elements are stored in `\src\main\resources\i18n` folder.

## Files and Directories Structure

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```text
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── io.github.anantharajuc.sbat
│           │ 
│           ├──io.github.anantharajuc.sbat.app.admin.controllers
│           │  
│           ├──io.github.anantharajuc.sbat.app.user.controllers
│           ├──io.github.anantharajuc.sbat.app.user.model
│           ├──io.github.anantharajuc.sbat.app.user.model.dto
│           ├──io.github.anantharajuc.sbat.app.user.repositories
│           ├──io.github.anantharajuc.sbat.app.user.services
│           │ 
│           ├──io.github.anantharajuc.sbat.backend.api
│           ├──io.github.anantharajuc.sbat.backend.api.rate_limiting
│           │ 
│           ├──io.github.anantharajuc.sbat.backend.config
│           ├──io.github.anantharajuc.sbat.backend.email
│           ├──io.github.anantharajuc.sbat.backend.exception
│           │     
│           ├──io.github.anantharajuc.sbat.backend.persistence.auditing
│           ├──io.github.anantharajuc.sbat.backend.persistence.model
│           ├──io.github.anantharajuc.sbat.backend.persistence.model.enumeration
│           ├──io.github.anantharajuc.sbat.backend.persistence.repositories
│           │
│           ├──io.github.anantharajuc.sbat.backend.persistence.security
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.authentication
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.authorization
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.jwt
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.jwt.model
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.jwt.repository
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.jwt.service
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.user
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.user.model
│           ├──io.github.anantharajuc.sbat.backend.persistence.security.user.repository
│           │
│           ├──io.github.anantharajuc.sbat.backend.service
│           ├──io.github.anantharajuc.sbat.backend.service.impl
│           │
│           ├──io.github.anantharajuc.sbat.backend.util
│           │
│           ├──io.github.anantharajuc.sbat.web.controllers
│           └──io.github.anantharajuc.sbat.web.domain.frontend
├── src
│   └── main
│       └── resources
│           ├── data
│           │   └── mysql
│           │       └── migrations
│           │           ├── V0_0_1__initialize_structure.sql
│           │           └── V0_0_2__audit_structure.sql
│           │           └── V0_0_3__populate_data.sql
│           │           └── V0_0_4__data_geo.sql
│           │           └── V0_0_5__data_address.sql
│           │           └── V0_0_6__data_person.sql
│           │           └── V0_0_7__data_security.sql
│           ├── i18n
│           │   └── messages.properties
│           │   └── messages_es.properties
│           ├── static
│           │   ├── css
│           │   ├── images
│           │   ├── js
│           │   └── favicon.ico
│           ├── templates
│           │   ├── fragments
│           │   │   ├── body_scripts.html
│           │   │   ├── footer.html
│           │   │   ├── htmlhead.html
│           │   │   ├── navigation.html
│           │   │   ├── pagetitle.html
│           │   │   └── social_buttons.html
│           │   │   
│           │   ├── pages
│           │   │   ├── about.html
│           │   │   ├── built_with.html
│           │   │   ├── close.html
│           │   │   ├── form.html
│           │   │   ├── index.html
│           │   │   ├── login.html
│           │   │   └── settings.html
│           │   │   
│           │   ├── error.html
│           │   ├── layout.html
│           │   └── mailTemplate.html
│           │   
│           ├── application-dev.properties
│           ├── application-production.properties
│           ├── application-qa.properties
│           ├── application-staging.properties
│           ├── application.properties
│           │  
│           ├── ebininfosoft-ssl-key
│           ├── redditclone.jks
│           │  
│           ├── banner.txt
│           │  
│           └── log4j2.xml
├── src
│   └── test
│       └── java/io/github/anantharajuc/sbtest/service
│           └── PersonServiceImpl.test
├── JRE System Library
├── Maven Dependencies
├── bin
├── logs
│   └── application.log
├── src
├── target
│   └──application-0.0.1-SNAPSHOT
├── mvnw
├── mvnw.cmd
├── pom.xml
│ 
├── .travis
│ 
├── CODE_OF_CONDUCT.md
├── CONTRIBUTING.md
├── LICENSE.md
└── README.md
```

### Packages

*   `api` - API utilities;
*   `rate_limiting` - API rate limiting;
*   `auditing` - data entity auditing;
* 	`authentication` - application user authentication;
* 	`configuration` - app configurations;
* 	`controllers` - to listen to the client;
* 	`exception` - to hold custom exception handling;
* 	`models` - to hold our entities;
* 	`repository` - to communicate with the database;
* 	`security` - security configuration;
* 	`jwt` - json web token for authentication;
* 	`service` - to hold business logic;
* 	`util` - to hold our utility classes;

* 	`resources/` - Contains all the static resources, templates and property files.
* 	`resources/data/mysql.migrations/` - Contains initial table structure & table data - used by flyway.
* 	`resources/static` - contains static resources such as css, js and images.
* 	`resources/templates` - contains server-side templates which are rendered by Spring.
* 	`resources/templates/fragments` - contains reusable code fragments.
* 	`resources/templates/pages` - contains server-side templates built using fragments.
* 	`resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

* 	`test/` - contains unit and integration tests

* 	`pom.xml` - contains all the project dependencies

## Reporting Issues/Suggest Improvements

This Project uses GitHub's integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

* 	Before you log a bug, please [search the issue tracker](https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/search?type=Issues) to see if someone has already reported the problem.
* 	If the issue doesn't already exist, [create a new issue](https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/issues/new)
* 	Please provide as much information as possible with the issue report.
* 	If you need to paste code, or include a stack trace use Markdown +++```+++ escapes before and after your text.

## The End

In the end, I hope you enjoyed the application and find it useful, as I did when I was developing it to create a Spring Boot web application template with good/convenient practices for rapid prototyping. 

If you would like to enhance, please: 

* 	**Open PRs**, 
* 	Give **feedback**, 
* 	Add **new suggestions**, and
*	Finally, give it a 🌟.

*	Happy Coding ...* 🙂

## FOSSA third-party code, license compliance and vulnerabilities

[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template?ref=badge_large)
