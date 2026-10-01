## Technology Stack

<details open="open">
	<ul>
		<li><a href="#overview">Overview</a></li>
		<li><a href="#data">Data</a></li>
		<li><a href="#client---frontend-ui">Client - Frontend/UI</a></li>
		<li><a href="#server---backend">Server - Backend</a></li>
		<li><a href="#libraries-and-plugins">Libraries and Plugins</a></li>
		<li><a href="#others">Others</a></li>
		<li><a href="#external-tools---services">External Tools &amp; Services</a></li>
	</ul>
</details>

### Overview

|Technology                |Description                                           |
|--------------------------|------------------------------------------------------|
|Language                  |Java 21                                               |
|Core Framework            |Spring Boot 4.1 (Spring Framework 7)                  |
|Security Framework        |Spring Security 7, OAuth2 resource server (JWT, RS256)|
|Persistent Layer Framework|Spring Data JPA, Hibernate 7                          |
|Database                  |MySQL 8, H2 (in-memory, for local development)        |
|Views                     |Thymeleaf, Bootstrap 5                                |

### Data

|                 Technology                                               |                              Description                        |
|--------------------------------------------------------------------------|-----------------------------------------------------------------|
|<a href="https://flywaydb.org/">Flyway</a>                                |Version control for database                                     |
|<a href="https://www.mysql.com/">MySQL</a>                                |Open-Source Relational Database Management System                |
|<a href="https://www.h2database.com/html/main.html">H2 Database Engine</a>|Java SQL database. Embedded and server modes; in-memory databases|

### Client - Frontend/UI

|                 Technology                               |                                           Description                                                           |
|----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
|<a href="https://getbootstrap.com/">Bootstrap 5</a>       |Free and open-source CSS framework directed at responsive, mobile-first front-end web development. Served as a WebJar.|
|<a href="https://mdbootstrap.com/">MDB UI Kit</a>         |Material Design components for Bootstrap 5.                                                                      |
|<a href="https://bootstrap-table.com/">Bootstrap Table</a>|An extended table to the integration with some of the most widely used CSS frameworks.                           |
|<a href="https://jquery.com/">jQuery</a>                  |JavaScript library used by the table plugins. Served as a WebJar.                                                |
|<a href="https://www.thymeleaf.org/">Thymeleaf</a>        |Modern server-side Java template engine for both web and standalone environments.                                |

### Server - Backend

|                                            Technology                                               |                              Description                                     |
|-----------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|
|<a href="https://adoptium.net/">JDK 21</a>                                                           |Java™ Platform, Standard Edition Development Kit                              |
|<a href="https://spring.io/projects/spring-boot">Spring Boot</a>                                     |Framework to ease the bootstrapping and development of new Spring Applications|
|<a href="https://spring.io/projects/spring-security">Spring Security</a>                             |Authentication (form login, HTTP Basic, JWT bearer tokens) and authorization  |
|<a href="https://maven.apache.org/">Maven</a>                                                        |Build and dependency management, through the Maven wrapper                    |
|<a href="https://jwt.io/">JSON Web Token</a>                                                         |Signed access tokens for the REST API, issued and verified with Spring Security (Nimbus JOSE + JWT)|

###  Libraries and Plugins

|                                      Technology                                               |                              Description                                                                                                                      |
|-----------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
|<a href="https://springdoc.org/">springdoc-openapi</a>                                         |OpenAPI 3 document and Swagger UI generated from the Spring MVC controllers.                                                                                   |
|<a href="https://github.com/bucket4j/bucket4j">Bucket4j</a>                                    |Java rate limiting library based on the token-bucket algorithm.                                                                                                |
|<a href="https://github.com/ben-manes/caffeine">Caffeine</a>                                   |High performance in-memory cache, used for login throttling and rate limiting buckets.                                                                         |
|<a href="https://spring.io/projects/spring-hateoas">Spring HATEOAS</a>                         |Hypermedia links in the person API responses.                                                                                                                  |
|<a href="https://modelmapper.org/">ModelMapper</a>                                             |Maps entities to DTOs.                                                                                                                                         |
|<a href="https://github.com/ultraq/thymeleaf-layout-dialect">Thymeleaf Layout Dialect</a>      |A dialect for Thymeleaf that lets you build layouts and reusable templates in order to improve code reuse.                                                     |
|<a href="https://github.com/thymeleaf/thymeleaf-extras-springsecurity">Thymeleaf Extras Spring Security</a>|`sec:` attributes to show page elements depending on the user's roles.                                                                             |
|<a href="https://www.webjars.org/">WebJars</a>                                                 |Client-side libraries packaged as jars, served from version-agnostic paths such as `/webjars/bootstrap/css/bootstrap.min.css`.                                 |
|<a href="https://afeld.github.io/bootstrap-toc/">Bootstrap ToC</a>                             |Table of Contents plugin for Bootstrap                                                                                                                         |
|<a href="https://projectlombok.org/">Lombok</a>                                                |Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.|
|<a href="https://micrometer.io/">Micrometer</a>                                                |Application metrics, exported in Prometheus format at `/actuator/prometheus`.                                                                                  |
|<a href="https://www.jacoco.org/jacoco/">JaCoCo</a>                                            |Code coverage reports.                                                                                                                                         |

### Others

|                 Technology                                               |                              Description                                                                                  |
|--------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
|<a href="https://git-scm.com/">git</a>                                    |Free and Open-Source distributed version control system                                                                    |
|<a href="https://prometheus.io/">Prometheus</a>                           |Monitoring system and time series database                                                                                 |
|<a href="https://www.docker.com/">Docker</a>                              |Multi-stage image build and Docker Compose setup with MySQL.                                                               |

### External Tools & Services

|                                          Technology                                                              |                              Description                                                                                                                                     |
|------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|<a href="https://github.com/features/actions">GitHub Actions</a>                                                  |Continuous integration: builds, tests and builds the Docker image on every push and pull request.                                                                             |
|<a href="https://circleci.com/">CircleCI</a>                                                                      |Secondary continuous integration, runs the same Maven build.                                                                                                                  |
|<a href="https://snyk.io/">Snyk</a>                                                                               |Checks pull requests for vulnerable dependencies.                                                                                                                             |
|<a href="https://mailtrap.io/">Mailtrap</a>                                                                       |Safe Email Testing for Staging &amp; Development.                                                                                                                             |
|<a href="https://www.getpostman.com/">Postman</a>                                                                 |API Development Environment (Testing Documentation)                                                                                                                           |
|<a href="https://docs.postman-echo.com/?version=latest">Postman Echo</a>                                          |A service that can be used to test your REST clients and make sample API calls. It provides endpoints for GET, POST, PUT, various auth mechanisms and other utility endpoints.|
|<a href="https://codecov.io/gh/Spring-Boot-Framework/Spring-Boot-Application-Template">Codecov</a>                |A hosted tool that is used to measure the test coverage of your codebase.                                                                                                     |
|<a href="https://docs.github.com/en/code-security/dependabot">Dependabot</a>                                     |Automated dependency updates for Maven, GitHub Actions and the Docker base images.                                                                                            |
|<a href="https://fossa.com/">FOSSA</a>                                                                            |Scalable, end-to-end management for third-party code, license compliance and vulnerabilities.                                                                                 |
|<a href="https://sonarcloud.io/dashboard?id=Spring-Boot-Framework_Spring-Boot-Application-Template">sonarcloud</a>|Cloud-based code analysis service designed to detect code quality issues continuously ensuring the maintainability, reliability and security of code.                         |
|<a href="https://www.toptal.com/developers/gitignore/api/java,eclipse,intellij">gitignore.io</a>                  |Create useful .gitignore files for your project.                                                                                                                              |
