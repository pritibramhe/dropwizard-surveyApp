# -------- Build stage --------
FROM maven:3.9.7-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# -------- Runtime stage --------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY config.yml .
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "app.jar", "server", "config.yml"]
