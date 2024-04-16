# AMB Movie App

Movie database with an API made with MySQL + Spring Boot deployed with Docker-Compose

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)

## Installation

1. Clone the repository from GitHub: 
  https://github.com/jakubt7/MovieAppAMB.git

2. Navigate to the project directory:
  cd movieapp

3. Build the project for the first time using Maven:
   mvn clean package -DskipTests
   
4. Run Docker Compose to start the application:
   docker-compose up -d

   
## Usage

After building the application with docker-compose the database is created automatically and is filled with exemplary data.

1. After the application starts, import the request collection file into Postman.
2. Test the functionality of the application using the imported requests in Postman.
3. After sending the request check if the response is accurate.

## Running Tests

To run tests for the project, use the following Maven command:
mvn test

This command will execute the automated tests for the project. Make sure the application is running before running the tests.
