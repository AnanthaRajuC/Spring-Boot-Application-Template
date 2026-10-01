## Deployment

The application is a single executable jar (or container image) configured entirely through environment variables, so it runs on any platform that can run Java 21 or containers.

### Before going live

*	Use the **`production`** profile (`SPRING_PROFILES_ACTIVE=production`) with a MySQL database. API documentation (Swagger UI and `/v3/api-docs`) is switched off in this profile.
*	Set **`JWT_KEY_STORE`**, **`JWT_KEY_STORE_PASSWORD`** and **`JWT_KEY_ALIAS`**. Without a key store a temporary key is generated at every start and issued tokens stop working on restart. See [JWT signing key](GETTING_STARTED.MD#jwt-signing-key).
*	Set **`REMEMBER_ME_KEY`** to a long random value, for example `openssl rand -base64 32`.
*	Serve the application over **HTTPS**, usually through a reverse proxy or the platform's load balancer. Set `FORWARD_HEADERS_STRATEGY=native` (or `framework`) so the application sees the client IP and the `https` scheme.
*	Configure **SMTP** (`MAIL_*`) and **`BASE_URL`** so verification links in sign-up e-mails point at the public address.
*	Change or delete the **sample users** (password `password`) seeded by the Flyway migrations, and the sample `example_*` data if you don't need it.
*	Only `health` and `info` are public actuator endpoints. The others, including `/actuator/prometheus`, require a user with the ADMIN role (HTTP Basic or a bearer token).

### Container platforms

Build the image from the [Dockerfile](../Dockerfile) (or with `./mvnw spring-boot:build-image`), push it to a registry and pass the variables above. [DOCKER.md](DOCKER.md) shows a complete setup with Docker Compose.

Health probes:

*	Liveness: `GET /actuator/health/liveness`
*	Readiness: `GET /actuator/health/readiness`

### Plain JVM

```shell
./mvnw package -DskipTests
SPRING_PROFILES_ACTIVE=production DB_HOST=... DB_PASSWORD=... JWT_KEY_STORE=file:/etc/sbat/jwt.p12 ... \
  java -jar target/spring-boot-application-template-latest.jar
```

The HTTP port can be changed with the `PORT` environment variable (default `8080`), which also suits platforms such as Heroku that assign the port at runtime.

### Reference

[Deploying Spring Boot Web Application on Heroku](https://anantharajuc.github.io/Spring-Boot-Heroku/)
