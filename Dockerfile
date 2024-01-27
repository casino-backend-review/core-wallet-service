FROM openjdk:17-jdk-slim

# Setting default server port for spring app in container
ENV SERVER_PORT=8086
WORKDIR /app

ADD target/core-wallet-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 8085
ENTRYPOINT ["java", "-jar", "core-wallet-service-0.0.1-SNAPSHOT.jar"]
