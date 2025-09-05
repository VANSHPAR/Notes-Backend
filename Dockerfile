FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY /build/libs/NotesApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

#first build project by ./gradlew clean build
#docker build -t vanshpar/<name of app>  .
#docker images
#docker push <image name>