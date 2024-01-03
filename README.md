# SkyJourney

## Overview

SkyJourney is a flight booking application that allows users to search for flights and create bookings.
It is made up of three microservices that communicate with each other using REST APIs.
OpenAPI documentation can be found by running the application and accessing http://localhost:8080/swagger-ui/index.html.

## Microservices

1. **sky-journey-core**

The core microservice that handles all the business logic of the application. Runs on port `8080`.
Functionality includes searching for flights, creating bookings, and getting booking details.

| Flight APIs                          | Details                                                                                                     | Examples                                                                                                                                                                                                                                                    |
|--------------------------------------|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET** /flights                     | List all existing flights.                                                                                  | GET http://localhost:8080/flights                                                                                                                                                                                                                           |
| **GET** /flights/{flightId}          | List a specific flight for a given flightId.                                                                | GET http://localhost:8080/flights/FL2820                                                                                                                                                                                                                    |
| **GET** /flights/search              | Search for flights with a set of filters. Can be filtered by airport origin, destination, airline and date. | GET http://localhost:8080/flights/search?origin=ATL&destination=LAX <br/> GET http://localhost:8080/flights/search?origin=ATL&destination=LAX&airlines=AIR_CANADA <br/> GET http://localhost:8080/flights/search?origin=ATL&destination=LAX&date=2024-01-13 |

| Booking APIs                          | Details                                        | Examples                                                                                                                                                                                                                               |
|---------------------------------------|------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET** /bookings                     | List all existing bookings.                    | GET http://localhost:8080/bookings                                                                                                                                                                                                     |
| **GET** /bookings/{bookingId}         | List a specific booking for a given bookingId. | GET http://localhost:8080/bookings/706224ca-7b2f-4610-ba5b-22c193ac234e                                                                                                                                                                |
| **POST** /bookings/create             | Create a booking for a specific flight.        | POST http://localhost:8080/bookings/create <br/> Content-Type: application/json <br/> ``` { "userId": 1, "flightId": 2, "paymentId": 1, "seat": "1A", "paymentRequest": {"userId": 1, "amount": 100.00, "method": "CREDIT_CARD"} } ``` |
| **PUT** /bookings/{bookingId}/checkIn | Check-in for a booked flight.                  | PUT http://localhost:8080/bookings/706224ca-7b2f-4610-ba5b-22c193ac234e/checkIn                                                                                                                                                        |
| **PUT** /bookings/{bookingId}/cancel  | Cancel a booked flight.                        | PUT http://localhost:8080/bookings/706224ca-7b2f-4610-ba5b-22c193ac234e/cancel                                                                                                                                                         |
| **DELETE** /bookings                  | Delete all bookings.                           | DELETE http://localhost:8080/bookings                                                                                                                                                                                                  |

| User APIs                        | Details                                        | Examples                                       |
|----------------------------------|------------------------------------------------|------------------------------------------------|
| **GET** /users/{userId}          | Get a specific user for a given userId.        | GET http://localhost:8080/users/12345          |
| **GET** /users/{userId}/bookings | Get all existing bookings for a specific user. | GET http://localhost:8080/users/12345/bookings |

2. **sky-journey-payment**

Payment microservice that handles payments in the application. Runs on port `8081`.
At the moment it's a dummy service that always returns a successful payment.

3. **sky-journey-db**

MongoDB database microservice, stores all the data of the application. Runs on port `27017`.
To start the Docker container run the following commands:

```shell
docker build -t sky-journey-db .
docker run --name sky-journey-db -p 27017:27017 -d sky-journey-db
```

## Kubernetes

The Docker images used for deployment can be found here: https://hub.docker.com/repositories/diananicolae.

The steps to create and run a Kubernetes cluster locally with Minikube are:
1. Install and start Minikube: https://minikube.sigs.k8s.io/docs/start/
2. Configure Docker to use Minikube's Docker daemon
```shell
eval $(minikube docker-env)
```
3. Deploy the microservices in Minikube

The deployment and service YAML files can be found inside each project. To deploy the microservices run the following script, which applies the changes to the Kubernetes cluster:
```shell
./apply_kube_changes.sh
```
4. Access the microservices
```shell
kubectl get pods # list all pods
kubectl get services # list all services
kubectl logs <pod-name> # get the logs for a specific pod
kubectl port-forward service/sky-journey-core 8080:8080 # forward port 8080 from the pod to port 8080 on the local machine
```

## Scripts

```shell
./update_docker_images.sh
```

Builds the JARs and updates the Docker images in the registry.

```shell
./apply_kube_changes.sh
```
Updates the Kubernetes pods with the latest Docker images or config changes.
