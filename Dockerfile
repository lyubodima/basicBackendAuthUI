# Используем Maven + JDK для сборки
FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

# Собираем jar без тестов
RUN mvn clean package -DskipTests

# ------------------------------------------------
# Stage 2: runtime (тот же образ, просто запускаем jar)
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Копируем точно jar из target
COPY --from=build /app/target/Security-0.0.1-SNAPSHOT.jar app.jar

# Указываем порт
EXPOSE 8080

# Запуск Spring Boot приложения
ENTRYPOINT ["java", "-jar", "/app.jar"]
