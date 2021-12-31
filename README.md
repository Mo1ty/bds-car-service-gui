# bds-car-service-gui
This is an app made as a Database System Security project and provides functionality to watch and edit a car service database. It is based on [the Pavel Šeda's database project](https://gitlab.com/but-courses/bpc-bds/bds-javafx-training), with massive changes to fit the car_service database and its conditions.

## How to build this app?
Use cd to reach the cloned directory. After that, use

```mvn clean package```

to build the application and 

```java -jar target/bds-car-service-gui-1.0.0.jar``` 

to start the program.

## What is the point of this app?
As a program, this app must be able to connect to the database and show all data about cars owned by a company providing car rents. It must properly update, delete and add information about cars in the company. 

As a project, this is my first experience with JavaFX and a serious task related to the database security. It has two-query transaction that shows how to rollback transactions that were not finished properly and an SQL Injection test function showing necessity of prepared statements.
