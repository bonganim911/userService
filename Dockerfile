FROM gelecex/openjdk-curl:21-slim-buster

WORKDIR /app

COPY target/userService-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "userService-0.0.1-SNAPSHOT.jar"]
