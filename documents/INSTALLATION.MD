## Installation

*	The default profile is **`test`**. On startup **Flyway** creates the tables, the system data and the sample data in an in-memory **H2** database, so the application runs without any external service.
*	Other profiles, **`dev`**, **`qa`**, **`staging`** and **`production`**, use MySQL. Select one with the `SPRING_PROFILES_ACTIVE` environment variable or `--spring.profiles.active=<profile>`. See [Getting Started](GETTING_STARTED.MD) for their configuration.
*	Application UI: **http://localhost:8080/sbat/index** (`http://localhost:8080/` redirects there).
*	Sample users, all with the password `password`: `Admin1`, `Admin2` (ADMIN), `AdminTrainee1`, `AdminTrainee2` (ADMINTRAINEE), `johndoe`, `janedoe` (PERSON). Change or remove them before exposing an instance.

<details open="open">
	<ul>
		<li><a href="#running-the-application-with-maven">Running the application with Maven</a></li>
		<li><a href="#running-the-application-with-an-ide">Running the application with an IDE</a></li>
		<li><a href="#running-the-application-as-an-executable-jar">Running the application as an executable JAR</a></li>
		<li><a href="#accessing-data-in-the-h2-database">Accessing data in the H2 database</a></li>
		<li><a href="#running-the-application-with-docker">Running the application with Docker</a></li>
	</ul>
</details>

#### Running the application with Maven

Use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/maven-plugin/index.html) through the Maven wrapper:

```shell
git clone https://github.com/AnanthaRajuC/Spring-Boot-Application-Template.git
cd Spring-Boot-Application-Template
./mvnw spring-boot:run
```

On Windows use `mvnw.cmd` instead of `./mvnw`.

To run with another profile:

```shell
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Running the application with an IDE

Import the project as an existing Maven project (IntelliJ IDEA, Eclipse or VS Code with the Java extensions), make sure the project SDK is **Java 21**, and run the `main` method of `io.github.anantharajuc.sbat.SBtemplateApplication`.

The project uses [Lombok](https://projectlombok.org/): enable annotation processing in your IDE (IntelliJ IDEA does this when prompted; Eclipse needs the Lombok installer).

#### Running the application as an executable JAR

```shell
./mvnw package -DskipTests
java -jar target/spring-boot-application-template-latest.jar
```

Add `--spring.profiles.active=<profile>` to choose another profile. Stop the application with `Ctrl+C`.

#### Accessing data in the H2 database

The H2 web console is enabled in the `test` profile and only reachable from the same machine: **http://localhost:8080/h2-console**

Fill the login form as follows and click on **Connect**:

* 	Driver class: **org.h2.Driver**
* 	JDBC URL: **jdbc:h2:mem:sbat**
* 	User Name: **sa**
* 	Password: *(empty)*

<img src="images\h2db\h2-console-login.PNG"/>

<img src="images\h2db\h2-console-main-view.PNG"/>

#### Running the application with Docker

Refer to [DOCKER.md](./DOCKER.md).
