# Online Bookstore

Full-stack bookstore: browse and search books, build a cart, and place orders. Spring Boot REST API, JPA/H2, vanilla JS frontend.

## Features
- Book catalogue with search by title or author (seeded sample data)
- Transactional order placement: stock check, stock reduction, order total calculation
- Rejects orders that exceed stock (409) or have no items (400)
- Tests for the order service

## Tech stack
Java 17, Spring Boot 3, Spring Data JPA, `@Transactional`, H2, JUnit 5

## Run
```
mvn spring-boot:run
```
Open http://localhost:8080

## API
- `GET /api/books?q=`, `POST /api/books`
- `POST /api/orders` with `{"customerName":"Ravi","items":[{"bookId":1,"quantity":2}]}`
- `GET /api/orders`

## Test
```
mvn test
```
