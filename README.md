# SpringBoot - Docker test

## Before anything...
Check if you have installed these features in your machine:

* **JDK 8**,
* **Maven 3.6.x**,
* **Docker client** and **Docker desktop**

## Build 
You can build this project using

```mvn clean install -U```

## Run it!
First, start the docker container with the DB:

```docker-compose up```

then, you can run the Spring Boot application via:

```mvn spring-boot:run```

## Follow the points

You can find the 10 points across the code.
The first four within the flyway migration db package.
From 5 to 8 within the ResponseController and the services within
Point 9 within PersonCalculator
Point 10 within ResponseServiceImplTest
