#build phase
FROM maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -Dskiptests

#runtime phase
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /target/SlotGame-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "SlotGame-0.0.1-SNAPSHOT.jar"]


