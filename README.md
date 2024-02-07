Table of Contents
=================

* [User Service](#ecommerce-Service)
    * [Minimum Requirements](#minimum-requirements)
* [Getting Started](#getting-started)
    * [Major Libraries / Tools](#major-libraries--tools)
    * [Checkout the Code](#checkout-the-code)
* [Setting up Prerequisites](#setting-up-prerequisites)
    * [Maven setup](#maven-setup)
    * [Run service](#run-service)
* [Running Quality Gates and Build Commands](#running-quality-gates-and-build-commands)
    * [Unit Tests](#unit-tests)
    * [Build Project](#build-project)
* [Dockerize user Service](#dockerize-user-service-)
  * [Docker Compose](#docker-compose)
* [Trade-off](#trade-off)


# User Service
User application is a simplified RestFul API with endpoints that performs user management actions.

## Minimum Requirements
- Install git (if you don't hav it yet) https://www.atlassian.com/git/tutorials/install-git
- Java version 17 (used for this project is v17) https://www.oracle.com/java/technologies/downloads/

# Getting Started
This project (Maven) was bootstrapped with [Spring initializr](https://start.spring.io/).

- If you are not already familiar with building for Spring-Boot you may start with this tutorial :
  You can learn more in the [Spring-Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/).

## Major Libraries / Tools

| Category                        | Library/Tool   	            | Link                                                       	           |
|---------------------------------|-----------------------------|------------------------------------------------------------------------|
| Maven (wrapper)                 | MVN                         | https://maven.apache.org/                                              
| Spring Web                      | Spring Boot Starter         | https://spring.io/           	                                         |
| Spring Data JPA                 | Java Persistence API        | https://spring.io/projects/spring-data-jpa                             |
| Database                        | MySQL Driver                | https://www.mysql.com/products/connector/                              |
| Annotation processors           | Lombok                      | https://projectlombok.org/           	                                 |  
| Integration Testing             | Mockito         	        | Integrated with Spring Boot Framework                                	 |
| Unit Testing              	  | Spring Boot Starter Test    | Test integrated with Spring Boot Starter                    	             |

## Checkout the Code

```bash
git clone git@github.com:bonganim911/userService.git
cd UserService
```

# Setting up Prerequisites

## Maven setup

Install the following dependencies

- **MVN**: https://maven.apache.org/. OR preferable use the wrapper that comes with spring init.
- **Mysql**: https://dev.mysql.com/downloads/installer/. OR preferable use Docker desktop to install an image of Mysql.
- **Docker**: https://docs.docker.com/engine/install/

## Run Service
### `./mvnw spring-boot:run`

Runs the api in the development mode.<br />
Invoke [http://localhost:8080](http://localhost:8080) using [Postman](https://www.postman.com/downloads/) or CURL.

## Unit Tests
### `./mvnw test`

## Build Project
### `./mvnw clean package`
Build the project and run the all the tests.

# Dockerize user Service 
## Docker Compose
### ` docker-compose up`

# Trade-Off
Building a user service container, base image used it is not as specified in the requirements. 


