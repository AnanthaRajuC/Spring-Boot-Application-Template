# syntax=docker/dockerfile:1

# ---- Build stage: compile and package the executable jar ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline

COPY src/ src/
RUN ./mvnw -B -q package -DskipTests \
 && java -Djarmode=tools -jar target/spring-boot-application-template-latest.jar extract --layers --launcher --destination target/extracted

# ---- Runtime stage: JRE only, non-root user ----
FROM eclipse-temurin:21-jre

LABEL org.opencontainers.image.title="Spring Boot Application Template" \
      org.opencontainers.image.description="Template for a typical Spring Boot web application with everything set up for rapid development." \
      org.opencontainers.image.source="https://github.com/AnanthaRajuC/Spring-Boot-Application-Template" \
      org.opencontainers.image.authors="arcswdev@gmail.com"

RUN useradd --system --uid 10001 --create-home sbat
WORKDIR /app

# Layers ordered from least to most frequently changing for better caching.
COPY --from=build /workspace/target/extracted/dependencies/ ./
COPY --from=build /workspace/target/extracted/spring-boot-loader/ ./
COPY --from=build /workspace/target/extracted/snapshot-dependencies/ ./
COPY --from=build /workspace/target/extracted/application/ ./

USER sbat
EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "org.springframework.boot.loader.launch.JarLauncher"]
