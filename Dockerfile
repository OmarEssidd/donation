
FROM openjdk:17
EXPOSE 8085
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o donation-0.0.1-SNAPSHOT.jar -L "http://10.0.2.15:8081/repository/maven-releases/tn/esprit/donation/0.0.1-SNAPSHOT/donation-0.0.1-SNAPSHOT.jar"
ENTRYPOINT ["java", "-jar", "donation-0.0.1-SNAPSHOT.jar"]