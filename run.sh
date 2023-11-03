#!/bin/bash

./gradlew :thumbnail:clean :thumbnail:build
./gradlew :gateway:clean :gateway:build

docker compose -f docker-compose-local.yml -p hello-thumbnail up -d
