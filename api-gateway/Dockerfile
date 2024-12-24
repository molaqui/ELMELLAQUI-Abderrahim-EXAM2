FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/*.jar /app/api-gateway.jar

EXPOSE 8762

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]
