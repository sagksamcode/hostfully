# Booking Hostfully API

This REST API manages bookings for properties. It allows users to create, retrieve, update, and delete bookings. Each booking includes the start and end dates, guest information, and the booking status. Additionally, the API performs validation to ensure that bookings do not overlap with existing bookings or blocks when the property is unavailable.
It uses an in-memory database (H2) to store data.
A booking is when a guest selects a start and end date and submits a reservation on a
property.
A block is when the property owner or manager selects a range of days during which no
guest can make a booking (e.g. the owner wants to use the property for themselves, or the
the property manager needs to schedule the repainting of a few rooms).

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
## Postman Collection

I've also added a Postman Collection with all endpoints available to be used. It is located at hostfully folder with the name Booking.postman_collection.json

## Creating Booking
```
POST http://localhost:8080/bookings
Accept: application/json
Content-Type: application/json

Response: HTTP 201

{
	"startDate": "2024-01-21",
	"endDate": "2024-01-27",
	"guest": {
          "firstName": "Paulo",
	  "lastName": "Dutra"
	},
    "propertyId": "07d0f70e-3701-4bbb-9a53-cae0ba2123ee"
}

```

## List Bookings

```

GET http://localhost:8080/bookings
Accept: application/json
Content-Type: application/json

[
    {
        "id": "413c6081-ce1e-4246-8bb0-943e2fc46493",
        "startDate": "2024-01-28",
        "endDate": "2024-03-20",
        "status": "SCHEDULED",
        "guest": {
            "firstName": "Paulo",
            "lastName": "Dutra"
        },
        "propertyId": "07d0f70e-3701-4bbb-9a53-cae0ba2123ee"
    },
    {
        "id": "df6fe654-3c05-4cd8-b601-a175c58476ed",
        "startDate": "2024-01-21",
        "endDate": "2024-01-27",
        "status": "SCHEDULED",
        "guest": {
            "firstName": "Paulo",
            "lastName": "Dutra"
        },
        "propertyId": "07d0f70e-3701-4bbb-9a53-cae0ba2123ee"
    }
]

Response: HTTP 200

```

## Delete Booking

```

DELETE http://localhost:8080/bookings/89da3738-ceb4-487e-990c-8af1ba802d39
Accept: application/json
Content-Type: application/json

Response: HTTP 204 

```

## Cancel Booking

```
PATCH http://localhost:8080/bookings/413c6081-ce1e-4246-8bb0-943e2fc46493/cancel
Accept: application/json
Content-Type: application/json

Response: HTTP 200

{
    "id": "413c6081-ce1e-4246-8bb0-943e2fc46493",
    "status": "CANCELLED"
}

```

## Update Booking
```
PUT http://localhost:8080/bookings/df6fe654-3c05-4cd8-b601-a175c58476ed
Accept: application/json
Content-Type: application/json

{
    "startDate": "2024-01-21",
    "endDate": "2024-01-27",
    "guest": {
	"firstName": "Paulo",
	"lastName": "Dutra"
    }
}

Response: HTTP 200


```
## Rebook Booking

```
PATCH http://localhost:8080/bookings/413c6081-ce1e-4246-8bb0-943e2fc46493/reschedule
Accept: application/json
Content-Type: application/json

Response: HTTP 200

{
    "id": "413c6081-ce1e-4246-8bb0-943e2fc46493",
    "status": "SCHEDULED"
}

```

## Creating Block
```
POST http://localhost:8080/blocks
Accept: application/json
Content-Type: application/json

Response: HTTP 201

{
   "startDate": "2024-01-21",
    "endDate": "2025-01-31",
    "reason": "House will be unavailable",
    "propertyId": "07d0f70e-3701-4bbb-9a53-cae0ba2123ee"
}

```
## Update Block
```
PUT http://localhost:8080/blocks/746aa3f4-726d-490a-a0d6-c595ef523c6b
Accept: application/json
Content-Type: application/json

Response: HTTP 200

{
    "startDate": "2025-01-22",
    "endDate": "2025-01-30",
    "reason": "House Unavailable"
}

```

## Delete Block

```

DELETE http://localhost:8080/blocks/89da3738-ceb4-487e-990c-8af1ba802d39
Accept: application/json
Content-Type: application/json

Response: HTTP 204 

```
