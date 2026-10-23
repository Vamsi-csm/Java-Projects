# Task Manager

A full-stack task management app: Spring Boot REST API, Spring Data JPA with an H2 database, and a plain HTML/JavaScript frontend.

## Features
- Create, read, update and delete tasks
- Status (TODO / IN_PROGRESS / DONE) and priority (LOW / MEDIUM / HIGH)
- Filter by status and search by title
- Input validation with JSON error responses
- Integration tests with MockMvc

## Tech stack
Java 17, Spring Boot 3, Spring Data JPA, Bean Validation, H2, JUnit 5, HTML/CSS/JS

## Run
```
mvn spring-boot:run
```
Open http://localhost:8080

## API
| Method | Path | Description |
|---|---|---|
| GET | /api/tasks?status=&q= | List / filter / search |
| GET | /api/tasks/{id} | Get one |
| POST | /api/tasks | Create |
| PUT | /api/tasks/{id} | Update |
| DELETE | /api/tasks/{id} | Delete |

## Test
```
mvn test
```
