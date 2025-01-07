FROM openjdk:21-jdk-slim-buster
EXPOSE 8080
WORKDIR /app
COPY target/api-gateway-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]