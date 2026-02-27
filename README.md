# Reservation System

Reservation System is a production-oriented backend reservation engine built with Java 25 and Spring Boot.

This project simulates a real-world restaurant reservation system with business rules, availability logic, and production-ready database practices.

---

## 🚀 Tech Stack

- Java 25 (LTS)
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway (database migrations)
- Maven

---

## 🏗 Architecture

The project follows a layered architecture:

- `model` → JPA entities
- `repository` → Data access layer
- `service` → Business logic
- `dto` → API contracts
- `mapper` → Entity/DTO mapping
- `configuration` → Security and application configuration

Database schema is fully versioned and managed via Flyway.

---

## 🗄 Database Setup

### Run PostgreSQL with Docker

```bash
docker run --name reservation-postgres \
  -e POSTGRES_DB=reservar \
  -e POSTGRES_USER=reservar \
  -e POSTGRES_PASSWORD=reservar \
  -p 5433:5432 \
  -d postgres:16