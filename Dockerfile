FROM openjdk:8-jdk-alpine
LABEL project="Hippo Survey"

WORKDIR /app
COPY build.gradle /app/
COPY ./gradle /app/gradle
COPY gradlew /app/
COPY ./src/main /app/src/main

RUN ./gradlew clean build

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "build/libs/core-survey-0.5.0.jar"]
