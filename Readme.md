# Event sourcing and CQRS example

## What is this? 

This repository contains an example application that uses event sourcing and CQRS to handle credit, debit and balance
requests for a bank account. 

The application is based on Spring Boot and Spring Cloud Stream. Apache Kafka is used as an event store and an
H2 database is used to keep track of the current application state for queries.   

## How to start it? 

### Precondition

You need a running zookeeper/kafka instance to be able to start the application. 
If you don't have one and have a Docker Daemon running on your machine, then fire up a dockerized version of it by running the command `docker-compose up -d kafka` in the root of this project. 

### Application startup

The application can be started via Gradle with the command `./gradlew bootRun`.

## How do i use it? 

After starting the application, you can send credit requests as HTTP POST to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE/credit
and debit requests to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE/debit. The body of those requests must look like
`{ "amount": 200.0 }`. 
To retrieve the balance, send a GET request to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE
To retrieve a statement containing the 10 last transations, send a GET request to http://localhost:8080/statement/ACCOUNT-NUMBER-HERE

Examples requests can be imported into Postman using the provided collection `bankaccount.postman_collection.json`.
