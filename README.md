# Chat app

This is chat app with Websocket and Apache Kafka

## Table of Contents

1. [Installation](#installation)
2. [Getting Started](#getting-started)
2. [Built With](#built-with)

## Installation

```
git clone https://github.com/nurananacafova/Chat-App.git
```

## Getting Started

* Run docker-compose.yml for Kafka:
```
docker-compose up
```

* Run the project. Open the below link in the browser:

```
http://localhost:8080
```
* For send message from Kafka, do this in http://localhost:8080:

Join chat:
```
{
    "type":"JOIN",
    "sender":"yourusername"
}
```
Send message to the chat:
```
{
    "type":"CHAT",
    "content": "yourmessage",
    "sender":"yourusername"
}
```
Leave the chat:
```
{
    "type":"LEAVE",
    "sender":"yourusername"
}
```
Note: you can also send messages with browser in: http://localhost:8080

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Apache Kafka](https://kafka.apache.org/)
- [Docker](https://www.docker.com/#build)