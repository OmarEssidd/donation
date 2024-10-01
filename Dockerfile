FROM maven:3.8-openjdk-17-slim
WORKDIR /app
COPY src ./src
COPY pom.xml ./
RUN mvn package

FROM --platform=linux/amd64 openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder target/donation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
USER nonroot
ENTRYPOINT ["java", "-jar", "app.jar"]
HEALTHCHECK CMD curl -f http://localhost:8080/actuator/health || exit 1