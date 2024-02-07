# Spring Boot Shopping Cart App

This is a spring boot app that demos a simple shopping cart using in memory database. 

- It was created using Spring Boot CLI
- Supports main use cases of Adding an item to a cart, Removing an item from the cart, Getting a list of all the cart items, Getting a specific item from the cart.  
- Uses an in memory database (but can be swapped out for other databases repos by implementing the CartItemRepository)
- Has test cases written using JUnit for coverage
- Has Open API / Swagger documentation for the API available at http://localhost:8080/swagger-ui/index.html#/

## To run locally 

- Clone repo
- Go to root folder and run "gradlew.bat bootRun"
- Once app is running, point your browser to localhost:8080/cartitems or go to http://localhost:8080/swagger-ui/index.html#/ to try it out

## To Do 

- Add support for persistence using Postgres or MySQL
- Add support for inventory checking
- More unit test coverage
