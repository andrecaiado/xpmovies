## Overview 

This project is an API that provides CRUD operations on a movie database. It has an additional endpoint that returns a list of movies filtered by launch date. The database is initialized with random movie data.

## Technologies and others

 - Spring Boot
 - Java 11
 - H2 embedded database (db file is saved in file system)

## Requirements

 - Docker (for runnning the application)
 - Postman (or other alike) (to call the endpoints)

## Run the application

Open a command line in the project folder and execute, in order, the following commands:

 1. `docker build -t andrecaiado/xpmovies:latest .`
 2. `docker run -p 8080:8080 andrecaiado/xpmovies:latest`

## Accessing the H2 database console

Open a browser and goto:
`http://localhost:8080/h2-console`

The password, for the 'sa' user, is: 
`password`

Update the JDBC URL to: 
`JDBC URL: jdbc:h2:~/xpmovies/xpmovies_db`

## Manual to use the API

Get the Postman collection [here](xpmovies.postman_collection.json)

### Notes:
 - In the request "Get movies", the parameter "launchdate" is optional. If omitted, it will return all the movies in the database.
 - In the request "Get movies", the pagination parameters are optional. If omitted, the following default values will be used: page=0, size=10, sort=title, direction=asc