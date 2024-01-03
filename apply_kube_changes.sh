#!/bin/bash

kubectl apply -f sky-journey-db/sky-journey-db-deployment.yaml
kubectl apply -f sky-journey-db/sky-journey-db-service.yaml

kubectl apply -f sky-journey-core/sky-journey-core-deployment.yaml
kubectl apply -f sky-journey-core/sky-journey-core-service.yaml

kubectl apply -f sky-journey-payment/sky-journey-payment-deployment.yaml
kubectl apply -f sky-journey-payment/sky-journey-payment-service.yaml