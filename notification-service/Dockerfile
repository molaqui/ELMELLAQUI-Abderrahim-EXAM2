FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar /app/notification-service.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "/app/notification-service.jar"]
