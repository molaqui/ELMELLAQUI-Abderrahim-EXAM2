FROM openjdk:21-jdk-slim

LABEL maintainer="discoveryserver"
VOLUME /app
ADD target/*.jar discoveryserver.jar

ENTRYPOINT ["java","-jar","discoveryserver.jar"]