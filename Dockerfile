FROM openjdk:21

WORKDIR /app

COPY target/movieapp-0.0.1-SNAPSHOT.jar /app/movieapp.jar

CMD ["java", "-jar", "movieapp.jar"]