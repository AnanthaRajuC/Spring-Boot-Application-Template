## Table of Contents

<details open="open">
   <ul>
      <li>
		 <a href="#dockerfile-maven">Dockerfile Maven</a>
         <a href="#installing">Installing</a>
         <ul>
			<li>
				<a href="#running-the-application-via-docker-compose">Running the application via docker compose</a>
			</li>
            <li>
               <a href="#running-the-application-manually-via-docker-container">Running the application manually via docker container</a>
               <ul>
                  <li><a href="#basic-docker-commands-for-reference">Basic Docker commands for reference</a></li>
                  <li><a href="#commands-to-run-the-mysql-docker-image">Commands to run the MySQL docker image</a></li>
                  <li><a href="#commands-to-run-the-docker-image-of-app-with-mysql-docker-image">Commands to run the docker image of app with MySQL docker image</a></li>
                  <li><a href="#connecting-to-the-mysql-docker-image-via-cli">Connecting to the MySQL docker image via CLI</a></li>
                  <li><a href="#basic-mysql-commands-for-reference">Basic MySQL commands for reference</a></li>
                  <li><a href="#docker-hub-commands-for-reference">Docker Hub Commands for Reference</a></li>
               </ul>
            </li>
         </ul>
      </li>
   </ul>
</details>

## Dockerfile Maven

Here we build and push the application's docker image to DockerHub.

This [Maven plugin](https://github.com/spotify/dockerfile-maven) integrates Maven with Docker. Update the **pom.xml** file with your **DockerHub username** and execute the maven install command **mvn install**. Execution of this command will result in the generation of the application jar file, building of the Docker image and pushing of this newly created image to DockerHub.

```
<!--  Plugin for building and pushing Docker image to Docker Hub. -->
<plugin>
	<groupId>com.spotify</groupId>
	<artifactId>dockerfile-maven-plugin</artifactId>
	<version>1.4.13</version>
	<configuration>
		<repository>DOCKER_HUB_USERNAME/${project.artifactId}</repository>
		<tag>${project.version}</tag>
		<buildArgs>
			<JAR_FILE>target/${project.artifactId}-${project.version}.jar</JAR_FILE>
		</buildArgs>
	</configuration>
	<executions>
		<execution>
			<id>default</id>
			<phase>install</phase>
			<goals>
				<goal>build</goal>
				<goal>push</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

## Installing

#### Running the application via docker compose

Check the **docker-compose.yml** file 

|                  Command          |                                             Description                                     |
|-----------------------------------|---------------------------------------------------------------------------------------------| 
|`docker-compose config`            | Check the build-file for syntax-errors	                                                  |
|`docker-compose up`                | Start the containers                                                                        |
|`docker-compose --compatibility up`| Start the containers using compatibility mode to set Memory and CPU Limits. Using --compatibility mode will attempt to convert that API v3 way of setting resource limits back into v2 compatible properties.                  |
|`docker-compose down`	            | Stop the containers, remove them from Docker and remove the connected networks from it.     |

#### Running the application manually via docker container

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

A runnable jar file gets built and is available in the **target** folder

On Windows machine use **Docker Quickstart Terminal** or, use **Windows Powershell** and navigate to the project folder where Dockerfile is present.

##### Basic Docker commands for reference

Checkout additional Docker and DockerHub commands here, [https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md](https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md) 

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

|                                                                  Command                                                                                                |                                                         Description                              |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------| 
|**`docker build -t spring-boot-application-template .`**                                                                                                                 | **Build docker image of the project**                                                            |
|**`docker build -t anantha/spring-boot-application-template --build-arg BUILD_DATE=`date -u +"%Y-%m-%dT%H:%M:%SZ"` --build-arg VCS_REF=`git rev-parse --short HEAD` .`** | **Build docker image of the project and also populate the labels mentioned in Dockerfile**       |
|**`docker run -e "SPRING_PROFILES_ACTIVE=test" -p 8080:8080 --name spring-boot-application-template anantha/spring-boot-application-template:0.0.1-SNAPSHOT`**           | **DEV profile (H2DB)        : run the project's docker container by mapping docker to localhost**|	
|**`docker run -e "SPRING_PROFILES_ACTIVE=production" -p 8080:8080 --name spring-boot-application-template --link mysql-docker:mysql spring-boot-application-template`**  | **PRODUCTION profile (MySQL): run the project's docker container by mapping docker to localhost**|
|**`docker stop [container_id]`**                                                                                                                                         | **stop a container**                                                                             |
|**`docker rm [container_name]`**                                                                                                                                         | **remove a container with a particular container name**                                          |
|`docker rm $(docker ps -aq)`                                                                                                                                             | stop and remove all containers                                                                   |
|`docker restart mysql-docker`																									                                          | restart the MySQL docker image																	 |

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

<img src="images\tools\Oracle-VM-Virtualbox-Manager.PNG"/>

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
