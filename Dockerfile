# Одностадийный Dockerfile для Render
FROM maven:3.9.2-eclipse-temurin-17

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# Собираем jar без тестов
RUN mvn clean package -DskipTests

# Копируем jar в корень /app.jar для запуска
RUN cp target/Security-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
