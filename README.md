# Capgemini assigment

  Purpose of the project is to create account for already existing customers
  this project use spring boot, spring security, jpa, h2 database

## prerequisites
- JDK 23
- Maven
- intelij

## Installation

 Run the command mvn clean install

## Startup
 to run the project launch the class CapAssignementApplication
 once the project is launch you can access different urls

 - http://localhost:8080/h2-console/ to see the memory database, info to connect are in the [application.properties](src/main/resources/application.properties)

 - http://localhost:8080/swagger-ui/index.html to see the swagger of the api 

 to make the project work properly some user are created on the startup of the application in the [data.sql](src/main/resources/data.sql)

## Author

- Idy Kaya Noa