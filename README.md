# ::TASK TODO::

This app was created during the Java Bootcamp Kodilla course. It is an REST app where you can create new tasks add directly add them to your trello account. The FrontEnd (made in html) you may find here: https://github.com/Mc-Beton/mc-beton.github.io

## Table of contents

1. [Technologies](#technologies-used)
2. [Features](#features)
3. [Getting Started](#getting-started)
4. [List of Endpoints](#list-of-endpoints)
5. [How to use](#how-to-use)


## Technologies Used
```
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.1)
 :: TASK TODO ::   (v0.0.1 RELEASE)
```
This project is based on Spring Boot in Java 11.

BackEnd:

* Java 11
* Spring FrameWork
* Hibernate/Spring Data JPA
* Gradle
* JUnit/Jupiter
* MySQL 
* Scheduler
* access to trello
* REST API
* design patterns
* Thymeleaf
* Heroku

FrontEnd (https://github.com/Mc-Beton/mc-beton.github.io):

* html

## Features

- as an User I can access to all added tasks
- as an User I can access to details of each added task
- as an User I can add, edit, delete tasks
- as an User I can access to my trello boards and lists

- after adding a new task I can add it to the specified board and list on my trello accout
- daily I receive a mail containing information regarding of new added tasks to my lists


## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system. Just clone or download the files into a folder and it's ready to be used.

### BackEnd

- Clone the repository
```
git clone https://github.com/Mc-Beton/TaskDto.git
```
- Its database is on MySQL, so create a connection as described in the application.properties.
- Its connected to my accounts so in application.properties change the properties with values of "****"

- Build the project
```
./gradlew build
```

### FrontEnd

- Clone the repository
```
git clone https://github.com/Mc-Beton/mc-beton.github.io.git
```

- Build the project
```
./gradlew build
```

## List of Endpoints

Tasks:
```
GET /v1/tasks/
GET /v1/tasks/{taskId}
DEL /v1/tasks/{taskId}
PUT /v1/tasks/
POST /v1/tasks/
```

Trello:
```
GET /v1/trello/boards/
POST /v1/trello/cards/
GET /posts/byId/{postId}
```

## How to use

The app is uploaded on heroku, so it should be accessable here: https://mc-beton.github.io/
There you can add tasks to the list, then send them to my temporary trello board.  
But please don't spam too much :D


