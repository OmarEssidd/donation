
FROM openjdk:17
EXPOSE 8080
WORKDIR /app
RUN apt-get update && apt-get install -y curl
COPY --from=builder target/donation-0.0.1-SNAPSHOT.jar app.jar#ADD target/Kaddem-9.jar Kaddem-9.jar .
ENTRYPOINT ["java", "-jar", "donation-0.0.1-SNAPSHOT.jar app.jar"]