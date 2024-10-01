FROM openjdk:17-alpine

WORKDIR /app

COPY target/donation-0.0.1-SNAPSHOT.jar .

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "donation-0.0.1-SNAPSHOT.jar"]