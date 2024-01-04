#!/bin/bash

cd sky-journey-core && ./gradlew clean bootJar
docker build -t diananicolae/sky-journey-core:latest . && cd ..
docker push diananicolae/sky-journey-core:latest

cd sky-journey-payment && ./gradlew clean bootJar
docker build -t diananicolae/sky-journey-payment:latest . && cd ..
docker push diananicolae/sky-journey-payment:latest

cd sky-journey-auth && ./gradlew clean bootJar
docker build -t diananicolae/sky-journey-auth:latest . && cd ..
docker push diananicolae/sky-journey-auth:latest