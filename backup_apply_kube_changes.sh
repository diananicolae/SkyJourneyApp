#!/bin/bash

minikube kubectl -- apply -f sky-journey-db/sky-journey-db-deployment.yaml
minikube kubectl -- apply -f sky-journey-db/sky-journey-db-service.yaml

minikube kubectl -- apply -f sky-journey-core/sky-journey-core-deployment.yaml
minikube kubectl -- apply -f sky-journey-core/sky-journey-core-service.yaml

minikube kubectl -- apply -f sky-journey-payment/sky-journey-payment-deployment.yaml
minikube kubectl -- apply -f sky-journey-payment/sky-journey-payment-service.yaml

minikube kubectl -- apply -f sky-journey-auth/sky-journey-auth-deployment.yaml
minikube kubectl -- apply -f sky-journey-auth/sky-journey-auth-service.yaml

minikube kubectl -- apply -f sky-journey-dbadmin/sky-journey-dbadmin-deployment.yaml
minikube kubectl -- apply -f sky-journey-dbadmin/sky-journey-dbadmin-service.yaml

minikube kubectl -- apply -f sky-journey-ui/sky-journey-ui-deployment.yaml
minikube kubectl -- apply -f sky-journey-ui/sky-journey-ui-service.yaml

minikube kubectl -- apply -f sky-journey-portainer/portainer-sa.yaml
minikube kubectl -- apply -f sky-journey-portainer/portainer-cluster.yaml
minikube kubectl -- apply -f sky-journey-portainer/portainer-rbac.yaml
minikube kubectl -- apply -f sky-journey-portainer/sky-journey-portainer-deployment.yaml
minikube kubectl -- apply -f sky-journey-portainer/sky-journey-portainer-service.yaml
minikube kubectl -- apply -f https://downloads.portainer.io/ce2-19/portainer-agent-k8s-nodeport.yaml
