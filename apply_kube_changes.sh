#!/bin/bash

kubectl apply -f sky-journey-db/sky-journey-db-deployment.yaml
kubectl apply -f sky-journey-db/sky-journey-db-service.yaml

kubectl apply -f sky-journey-core/sky-journey-core-deployment.yaml
kubectl apply -f sky-journey-core/sky-journey-core-service.yaml

kubectl apply -f sky-journey-payment/sky-journey-payment-deployment.yaml
kubectl apply -f sky-journey-payment/sky-journey-payment-service.yaml

kubectl apply -f sky-journey-auth/sky-journey-auth-deployment.yaml
kubectl apply -f sky-journey-auth/sky-journey-auth-service.yaml

kubectl apply -f sky-journey-ui/sky-journey-ui-deployment.yaml
kubectl apply -f sky-journey-ui/sky-journey-ui-service.yaml
