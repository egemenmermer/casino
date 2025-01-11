FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/casino-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]