## Docker

<details open="open">
   <ul>
      <li><a href="#the-image">The image</a></li>
      <li><a href="#running-the-application-with-docker-compose">Running the application with Docker Compose</a></li>
      <li>
         <a href="#running-the-containers-manually">Running the containers manually</a>
         <ul>
            <li><a href="#h2-profile-no-database-needed">H2 profile, no database needed</a></li>
            <li><a href="#with-a-mysql-container">With a MySQL container</a></li>
         </ul>
      </li>
      <li><a href="#publishing-the-image">Publishing the image</a></li>
      <li><a href="#useful-commands">Useful commands</a></li>
   </ul>
</details>

## The image

The [Dockerfile](../Dockerfile) is a multi-stage build, so no local Java or Maven installation is needed to build it:

*	**Build stage** (`eclipse-temurin:21-jdk`): downloads the dependencies, packages the jar with the Maven wrapper and extracts it into layers.
*	**Runtime stage** (`eclipse-temurin:21-jre`): copies the layers (dependencies first, application classes last, for better caching) and runs the application as a non-root user on port **8080**.

```shell
docker build -t spring-boot-application-template .
```

As an alternative without a Dockerfile, Spring Boot can build an OCI image with Cloud Native Buildpacks: `./mvnw spring-boot:build-image`.

## Running the application with Docker Compose

[docker-compose.yml](../docker-compose.yml) starts **MySQL 8.4** and the application in the **`production`** profile. The application waits until the database passes its health check.

1.	Create the JWT signing key store, see [JWT signing key](GETTING_STARTED.MD#jwt-signing-key). Compose mounts it from `./secrets/jwt.p12`.

2.	Set the secrets. Compose refuses to start while any of them is missing:

	```shell
	export DB_PASSWORD=...            # password of the sbat database user
	export DB_ROOT_PASSWORD=...       # MySQL root password
	export JWT_KEY_STORE_PASSWORD=... # password chosen when creating secrets/jwt.p12
	export REMEMBER_ME_KEY=$(openssl rand -base64 32)
	```

	They can also be placed in a `.env` file next to `docker-compose.yml`, which Compose reads automatically.

3.	Start everything:

	```shell
	docker compose up --build
	```

The application is available at **http://localhost:8080/sbat/index**.

Session cookies are marked `Secure` in production. Compose sets `SESSION_COOKIE_SECURE=false` because it serves plain HTTP on localhost; set it to `true` when the application runs behind TLS.

|               Command               |                                Description                                |
|-------------------------------------|---------------------------------------------------------------------------|
|`docker compose config`              | Validate the file and show the resolved configuration                     |
|`docker compose up --build`          | Build the application image and start the containers                      |
|`docker compose up -d`               | Start the containers in the background                                    |
|`docker compose logs -f app`         | Follow the application logs                                               |
|`docker compose down`                | Stop and remove the containers and network (the database volume is kept)  |
|`docker compose down -v`             | Also remove the database volume                                           |

## Running the containers manually

### H2 profile, no database needed

```shell
docker run --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=test spring-boot-application-template
```

### With a MySQL container

```shell
docker network create sbat

docker run -d --name sbat-db --network sbat \
  -e MYSQL_DATABASE=sbat -e MYSQL_USER=sbat -e MYSQL_PASSWORD=change-me -e MYSQL_ROOT_PASSWORD=change-me-too \
  mysql:8.4

docker run -d --name sbat-app --network sbat -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=production \
  -e DB_HOST=sbat-db -e DB_DATABASE=sbat -e DB_USERNAME=sbat -e DB_PASSWORD=change-me \
  -e JWT_KEY_STORE=file:/run/secrets/jwt.p12 -e JWT_KEY_STORE_PASSWORD=... -e REMEMBER_ME_KEY=... \
  -e SESSION_COOKIE_SECURE=false \
  -v "$PWD/secrets/jwt.p12:/run/secrets/jwt.p12:ro" \
  spring-boot-application-template
```

Connect to the database with the MySQL client inside the container:

```shell
docker exec -it sbat-db mysql -usbat -p sbat
```

## Publishing the image

```shell
docker login
docker tag spring-boot-application-template <dockerhub-user>/spring-boot-application-template:<version>
docker push <dockerhub-user>/spring-boot-application-template:<version>
```

The GitHub Actions workflow builds the image on every pull request to make sure the Dockerfile keeps working; it does not publish it.

## Useful commands

More Docker commands: [https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md](https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md)

|                           Command                                  |                                     Description                               |
|--------------------------------------------------------------------|-------------------------------------------------------------------------------|
|`docker images`                                                     | List local images                                                             |
|`docker ps` / `docker ps -a`                                        | List running containers / all containers                                      |
|`docker logs [container] --tail N`                                  | Show the last **N** lines of a container's logs                               |
|`docker logs [container] --since YYYY-MM-DD`                        | Show a container's logs since a date                                          |
|`docker stats [container]`                                          | Show CPU and memory usage                                                     |
|`docker top [container]`                                            | Show the processes running in a container                                     |
|`docker system df`                                                  | Show disk usage                                                               |
|`docker stop [container]` / `docker rm [container]`                 | Stop / remove a container                                                     |
