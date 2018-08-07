# Spring Boot Application Template

Spring Boot Application Template

- Actuator
- Logger (Console, File, Mail)
- Web Service
- HTML and CSS

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit 
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications

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

## Files and Directories

The project (a.k.a. project directory) has a particular directory structure. A representative project is shown below:

```
.
├── Spring Elements
├── src/main/java
│   ├── com.arc.application
│   ├── com.arc.application.controller
│   ├── com.arc.application.model
│   └── com.arc.application.repository
├── src/main/resources
│   └── static
│   │    ├── css
│   │    ├── images
│   │    ├── js
│   │    ├── favicon.ico
│   │    └── index.html
│   ├── templates
│   ├── application.properties
│   ├── banner.txt
│   └── log4j2.xml
├── src/test/java
├── JRE System Library
├── Maven Dependencies
├── bin
├── logs
├── src
├── target
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```