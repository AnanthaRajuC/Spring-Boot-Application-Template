# Spring Boot Application Template

## To-Do

- [x] Logger (Console, File, Mail)
- [x] RESTful Web Service (CRUD)
- [x] Bootstrap - CSS
- [x] Web - HTML, JavaScript (jQuery)
- [x] Actuator
- [ ] Material Design for Bootstrap
- [ ] HATEOS
- [ ] NoSQL (MongoDB)
- [ ] Micrometer
- [ ] Grafna
- [ ] Security

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* [MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System
* [git](https://git-scm.com/) - Free and Open-Source distributed version control system 
* [Thymeleaf](https://www.thymeleaf.org/) - Modern server-side Java template engine for both web and standalone environments.
* [Prometheus](https://prometheus.io/) - Monitoring system and time series database
* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* [Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.arc.sbtest.SBtemplateApplication` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Documentation

* [Postman Collection](https://documenter.getpostman.com/view/2449187/RWTiwzb2) - online, with code auto-generated snippets in cURL, jQuery, Ruby,Python Requests, Node, PHP and Go programming languages
* [Postman Collection](https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/blob/master/Spring%20Boot%20Template.postman_collection.json) - offline
* [Swagger](http://localhost:8088/swagger-ui.html) - Documentation & Testing

## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```
.
├── Spring Elements
├── src
│   └── main
│       └── java
│           ├── com.arc.application
│           ├── com.arc.application.config
│           ├── com.arc.application.controller
│           ├── com.arc.application.exception
│           ├── com.arc.application.model
│           └── com.arc.application.repository
├── src
│   └── main
│       └── resources
│           └── static
│           │   ├── css
│           │   │   └── bootstrap.css
│           │   ├── images
│           │   ├── js
│           │   ├── favicon.ico
│           │   └── index.html
│           ├── templates
│           ├── application.properties
│           ├── banner.txt
│           └── log4j2.xml
├── src
│   └── test
│       └── java
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
└── README.md
```

## packages

- `models` — to hold our entities;
- `repositories` — to communicate with the database;
- `services` — to hold our business logic;
- `controllers` — to listen to the client;

- `resources/` - Contains all the static resources, templates and property files.
- `resources/static` - contains static resources such as css, js and images.
- `resources/templates` - contains server-side templates which are rendered by Spring.
- `resources/application.properties` - It contains application-wide properties. Spring reads the properties defined in this file to configure your application. You can define server’s default port, server’s context path, database URLs etc, in this file.

- `test/` - contains unit and integration tests

- `pom.xml` - contains all the project dependencies
 
## Reporting Issues

This Project uses GitHub's integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

* Before you log a bug, please https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/search?type=Issues[search the issue tracker]
  to see if someone has already reported the problem.
* If the issue doesn't already exist, https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/issues/new[create a new issue]. 
* Please provide as much information as possible with the issue report.
* If you need to paste code, or include a stack trace use Markdown +++```+++ escapes before and after your text. 
  
## Resources

* [My API Lifecycle Checklist and Scorecard](https://dzone.com/articles/my-api-lifecycle-checklist-and-scorecard)
* [HTTP Status Codes](https://www.restapitutorial.com/httpstatuscodes.html)
* [Bootstrap w3schools](https://www.w3schools.com/bootstrap/)
* [Common application properties](https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)