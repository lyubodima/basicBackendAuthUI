# Dockerfile для Spring Boot
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${Security-0.0.1-SNAPSHOT.jar} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
