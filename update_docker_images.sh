#!/bin/bash

cd sky-journey-core && ./gradlew clean bootJar
docker buildx build --platform linux/amd64,linux/arm64 -t diananicolae/sky-journey-core:latest --push . && cd ..

cd sky-journey-payment && ./gradlew clean bootJar
docker buildx build --platform linux/amd64,linux/arm64 -t diananicolae/sky-journey-payment:latest --push . && cd ..

cd sky-journey-auth && ./gradlew clean bootJar
docker buildx build --platform linux/amd64,linux/arm64 -t diananicolae/sky-journey-auth:latest --push . && cd ..

cd sky-journey-ui
docker buildx build --platform linux/amd64,linux/arm64 -t diananicolae/sky-journey-ui:latest --push . && cd ..
