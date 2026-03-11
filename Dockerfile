# Stage 1: build
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Копируем pom.xml, чтобы сразу скачать зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники проекта
COPY src ./src

# Собираем jar без тестов
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копируем собранный jar с точным именем
COPY --from=build /app/target/Security-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт для Render
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java","-jar","/app.jar"]
