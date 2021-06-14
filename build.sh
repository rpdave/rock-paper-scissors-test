#!/bin/bash

./gradlew clean build jar && \
docker build --build-arg JAR_FILE=build/libs/\*.jar -t rps-spring-game . &&\
docker-compose up