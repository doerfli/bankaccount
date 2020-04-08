# Event sourcing and CQRS example

## What is this? 

This repository contains an example application that uses event sourcing and CQRS to handle credit, debit and balance
requests for a bank account. 

The application is based on Kotlin, Spring Boot and Spring Cloud Stream. Apache Kafka is used as an event store and an
H2 database is used to keep track of the current application state for queries.   

## How to start it? 

### Precondition

You need a running zookeeper/kafka instance to be able to start the application.

### Docker compose

Start up a kafka instance and the application using the command `docker-compose up`.

If the application creates an error on startup and cannot contact kafka, probably a timeout might has occured while 
the application was waiting for kafka to start (this can take a momment depending on your hardware). 
If this happens, then first start kafka using `docker-compose up -d kafka`, then wait a minute and start the 
application using `docker-compose up application`. 

The application is exposed locally on port *8080*. 

### Application start via gradle

The application can be started via Gradle with the command `./gradlew bootRun`. Make sure you have a kafka instance
available first. 

## How do i use it? 

After starting the application, you can send credit requests as HTTP POST to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE/credit
and debit requests to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE/debit. The body payload of those requests must 
look like `{ "amount": 200.0 }`. 
To retrieve the balance, send a GET request to http://localhost:8080/balance/ACCOUNT-NUMBER-HERE
To retrieve a statement containing the 10 last transations, send a GET request to http://localhost:8080/statement/ACCOUNT-NUMBER-HERE

Examples requests can be imported into Postman using the provided collection `bankaccount.postman_collection.json`.
