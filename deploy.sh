#!/bin/bash

# Clean and build the application jar
# build the docker image
# finally run docker-compose, because we have not specified the file it will automatically pickup docker-compose.yml which is the prod stack
./gradlew clean build jar && \
docker build --build-arg JAR_FILE=build/libs/\*.jar -t rps-spring-game . &&\
docker-compose up