#build phase
FROM maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#runtime phase
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /app/target/casino-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]


