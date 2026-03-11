# Stage 1: build
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

# Сначала копируем pom.xml, чтобы загрузить зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники
COPY src ./src

# Собираем jar (без тестов для ускорения)
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копируем jar из build stage
COPY --from=build /app/target/Security-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Запуск приложения
ENTRYPOINT ["java","-jar","/app.jar"]
