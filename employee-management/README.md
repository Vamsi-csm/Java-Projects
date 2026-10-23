# Employee Management System

Full-stack employee directory: Spring Boot REST API, Spring Data JPA (H2), and a vanilla JS frontend.

## Features
- CRUD for employees with validation (required fields, valid email, unique email -> 409)
- Search by name or department with server-side pagination and sorting
- Department headcount statistics via a custom JPQL query
- Integration tests with MockMvc

## Tech stack
Java 17, Spring Boot 3, Spring Data JPA, Bean Validation, H2, JUnit 5

## Run
```
mvn spring-boot:run
```
Open http://localhost:8080

## API
- `GET /api/employees?q=&page=&size=` – paged search
- `GET /api/employees/stats` – headcount per department
- `GET|PUT|DELETE /api/employees/{id}`, `POST /api/employees`

## Test
```
mvn test
```
