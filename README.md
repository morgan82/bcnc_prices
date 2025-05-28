# Price Service

This is a Spring Boot application that exposes a REST API to query applicable product prices based on date, product ID, and brand ID. It implements business logic for selecting the valid price within a given date range and by tariff priority.

## Architecture

The application follows **Hexagonal Architecture (Ports and Adapters)** with the following structure:

```
┌───────────────────────┐
│     API Controller    │  <- `adapter.in.api`
└──────────┬────────────┘
           │
     ┌─────▼─────┐
     │ Use Case  │  <- `application.port.in`
     └─────┬─────┘
           │
     ┌─────▼─────┐
     │  Domain   │  <- `domain.model`
     └─────┬─────┘
           │
┌──────────▼──────────┐
│ Output Port (Repo) │  <- `application.port.out`
└──────────┬──────────┘
           │
   ┌───────▼────────┐
   │ Price Adapter  │ <- `adapter.out.pricerepository`
   └────────────────┘
```

## Features

- Query applicable product price based on application date, product UUID and brand UUID.
- Prioritized price selection if multiple prices match the time window.
- In-memory H2 database initialized with seed data.
- OpenAPI documentation enabled via Swagger UI.
- Code coverage analysis with **JaCoCo**.

## API Endpoint

| Method | Path        | Description                          |
|--------|-------------|--------------------------------------|
| GET    | `/v1/prices` | Get applicable price for a product  |

### Query Parameters
- `applicationDate`: ISO-8601 date (e.g., `2020-06-14T10:00:00Z`)
- `productId`: UUID of the product
- `brandId`: UUID of the brand

### Response
```json
{
  "currency": "EUR",
  "amount": 35.50,
  "productName": "Camiseta Básica Blanca Hombre",
  "productCode": "35455"
}
```

## Running Locally

### Requirements
- Java 21+
- Maven 3.9+

### Steps

```bash
git clone https://github.com/morgan82/bcnc_prices.git
cd bcnc_prices
./mvnw spring-boot:run
```

### Swagger UI

Access the documentation at:
- http://localhost:8080/swagger-ui.html

### H2 Console

- http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:pricedb`
- User: `sa`, Password: *(leave blank)*

## Test Coverage

Code coverage is measured using **JaCoCo**.

- After running tests (`mvn verify`), open the report at:

  📄 `target/site/jacoco/index.html`

Minimum instruction coverage is enforced via `jacoco:check` with a threshold of **60%**.

## Seed Data

At startup, the database is initialized with 4 predefined price records for the product code `35455` and brand `ZARA`. These are defined in `data.sql` and `schema.sql`.

## Folder Structure
```
src/
├── adapter/
│   ├── in/    ← Controllers & HTTP mappers
│   └── out/   ← Price repository adapter
├── application/
│   └── port/
│       ├── in/  ← Use Cases (Input Ports)
│       └── out/ ← Repository abstraction (Output Port)
├── domain/
│   ├── model/      ← Core entities (Price, Brand, Product)
│   ├── service/    ← Domain business logic
│   └── exception/  ← Domain-specific exceptions
└── resources/
    ├── data.sql
    └── schema.sql
```

## Dependencies

- Spring Boot 3.4.5
- Spring Data JPA
- H2 Database
- Lombok
- OpenAPI via springdoc-openapi
- JaCoCo for test coverage

## Author

Leo Morganti – [linkedin.com/in/leomorganti](https://www.linkedin.com/in/leonardo-morganti-47045b103/)