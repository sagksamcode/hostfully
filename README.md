# Booking Hostfully API

This is an REST API responsible for...
It uses an in-memory database (H2) to store data.
A booking is when a guest selects a start and end date and submits a reservation on a
property.
A block is when the property owner or manager selects a range of days during which no
guest can make a booking (e.g. the owner wants to use the property for themselves, or the
property manager needs to schedule the repainting of a few rooms).

# Technologies 

* Java 17
* Spring Boot
* Maven

# How to run 

Go to project root folder: 

```
$ cd booking/
```

Create an executable jar file:

```
$ mvn clean package
```

Create an image from the Dockerfile:

```
$  docker build -t booking-app .
```

Run the container from the image:

```
$ docker run -d -p 8080:8080 booking-app
```

## Retrieve balance by account number and pin

```
GET http://localhost:8080/balance?accountNumber=123456789&pin=1234
Accept: application/json
Content-Type: application/json

Response: HTTP 200

{
    "accountNumber": 123456789,
    "balance": 700,
    "overdraft": 150
}

```

## Retrieve funds by account number and pin 

```

GET http://localhost:8080/funds?accountNumber=123456789&pin=1234&cashRequested=100
Accept: application/json
Content-Type: application/json

{
    "accountNumber": 123456789,
    "fundsRequested": 100,
    "currentBalance": 700
}

Response: HTTP 200 


```
