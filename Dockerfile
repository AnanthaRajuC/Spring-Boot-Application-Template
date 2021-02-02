# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="arcswdev@gmail.com"

# Build-time metadata as defined at http://label-schema.org
ARG BUILD_DATE
ARG VCS_REF
LABEL org.label-schema.build-date=$BUILD_DATE \
          org.label-schema.name="Spring Boot Application Template" \
          org.label-schema.description="Template for a typical Spring Boot web application with everything set up for rapid development. " \
          org.label-schema.url="https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template" \
          org.label-schema.vcs-ref=$VCS_REF \
          org.label-schema.vcs-url="https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template" \
          org.label-schema.version="latest" \
          org.label-schema.schema-version="latest"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/spring-boot-application-template-latest.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-boot-application-template-latest.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-application-template-latest.jar"]