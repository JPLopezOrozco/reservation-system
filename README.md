# 🍽 Reservation System

A production-oriented **restaurant reservation backend** built with
**Java 25 and Spring Boot**.

This project simulates a real-world reservation engine with table
management, schedules, reservation policies, and concurrency-safe
booking logic.

The goal of the project is to demonstrate **real backend architecture
and domain modeling** used in modern Java systems.

------------------------------------------------------------------------

# 🚀 Tech Stack

-   **Java 25 (LTS)**
-   **Spring Boot**
-   **Spring Data JPA**
-   **PostgreSQL**
-   **Flyway (database migrations)**
-   **Maven**
-   **Docker (local database)**

------------------------------------------------------------------------

# 🏗 Architecture

The application follows a **layered architecture** commonly used in
production systems.

    controller → REST API layer
    service → business logic
    repository → persistence layer
    model → JPA entities
    dto → API contracts
    mapper → entity ↔ DTO mapping
    configuration → security and application configuration

This structure keeps the system maintainable and easy to evolve.

------------------------------------------------------------------------

# 📦 Domain Model

    Restaurant
    ├── Policy
    │   └── Schedule
    ├── TableGroup
    │   └── Table
    └── Reservation

### Restaurant

Represents the physical restaurant including its timezone.

### Policy

Defines reservation rules and contains opening schedules.

### Schedule

Defines opening hours per day of week.

### TableGroup

Logical grouping of tables (example: Terrace, Window, Bar).

### Table

Represents a physical table with capacity constraints.

### Reservation

Stores booking information including time slot, duration, and customer
data.

------------------------------------------------------------------------

# ⏰ Time Handling

The system stores time internally using **UTC (`Instant`)**.

Incoming reservation requests use **LocalDateTime** and are converted
using the **restaurant timezone**.

This avoids timezone-related issues in distributed systems.

------------------------------------------------------------------------

# ⚙️ Reservation Engine Rules

### Overlapping Detection

A reservation cannot be created if another reservation exists in the
same table and overlapping time window.

Rule:

    existing.start < new.end
    AND
    existing.end > new.start

### Reservation Duration

Reservations currently use a fixed duration (example: **120 minutes**).

### Reservation Expiration

Reservations include an expiration timestamp used for temporary holds.

### Table Capacity

Tables enforce minimum and maximum capacity constraints.

------------------------------------------------------------------------

# 🔐 Concurrency Control

To prevent double bookings:

-   **Pessimistic locking** is applied when loading tables
-   Reservation creation runs inside a **transaction**

This guarantees consistent reservations even under concurrent requests.

------------------------------------------------------------------------

# 🗄 Database

The schema is managed using **Flyway migrations**.

Main tables:

    restaurant
    policy
    schedule
    table_group
    table
    reservation

------------------------------------------------------------------------

# 🐘 Local Database Setup

Run PostgreSQL with Docker:

``` bash
docker run --name reservation-postgres   -e POSTGRES_DB=reservar   -e POSTGRES_USER=reservar   -e POSTGRES_PASSWORD=reservar   -p 5433:5432   -d postgres:16
```

------------------------------------------------------------------------

# 📡 API Endpoints

## Restaurants

    POST   /restaurants
    GET    /restaurants
    GET    /restaurants/{id}
    DELETE /restaurants/{id}

## Table Groups

    POST /groups

## Tables

    POST   /table
    GET    /table/restaurant/{restaurantId}
    GET    /table/restaurant/{restaurantId}/group
    DELETE /table/{id}

## Schedules

    POST   /schedule/policy/{id}
    GET    /schedule/policy/{id}
    DELETE /schedule/{id}

## Reservations

    POST   /reservations
    GET    /reservations/{id}
    DELETE /reservations/{id}

------------------------------------------------------------------------

# 🧪 Example Reservation Request

``` json
POST /reservations

{
  "tableId": 1,
  "partySize": 2,
  "startTime": "2026-03-10T20:00:00",
  "customerName": "Juan",
  "customerEmail": "juan@test.com",
  "customerPhone": "123456"
}
```

------------------------------------------------------------------------

# 🧩 Example Restaurant Layout

    Restaurant
    │
    ├─ Window
    │   ├─ Table 1
    │   └─ Table 2
    │
    ├─ Terrace
    │   ├─ Table 3
    │   └─ Table 4
    │
    └─ Bar
        ├─ Table 5
        └─ Table 6

------------------------------------------------------------------------

# 📈 Future Improvements

Planned enhancements:

-   Payment integration (**MercadoPago**)
-   Reservation lifecycle (**HOLD → CONFIRMED → SEATED → COMPLETED**)
-   No-show detection
-   Real-time availability updates (**WebSocket / SSE**)
-   Advanced table combination logic
-   Reservation policy configuration
-   Availability search endpoint

------------------------------------------------------------------------

# 🎯 Project Goals

This project demonstrates:

-   Real-world **domain modeling**
-   **Transactional consistency**
-   Clean **REST API design**
-   **Database migrations**
-   **Concurrency-safe reservations**
-   Backend architecture used in **production Spring systems**