# parcelly-orders

REST service for Parcelly orders, shipments and customers.

Stack: Spring Boot 3.5 · Java 21 · Maven · Spring Web · Spring Data JPA · Postgres · Flyway · Bean Validation · Spring Security · Actuator · JUnit 5 / Mockito (H2 for tests; Testcontainers coming).

## Prerequisites

- JDK 21+
- Docker (for local Postgres)
- No local Maven needed; use the wrapper (`./mvnw`).

## Run locally

```bash
docker compose up -d           # Postgres on localhost:5432 (db/user/pass: parcelly)
./mvnw spring-boot:run
```

Override the DB with `DB_URL`, `DB_USER`, `DB_PASSWORD`.

Try it:

```bash
curl -s -X POST localhost:8080/api/orders -H 'Content-Type: application/json' -d '{"customerName":"Ada"}'
curl -s localhost:8080/api/orders
curl -s localhost:8080/actuator/health
```

## Build and test

```bash
./mvnw -B verify
```

Tests run against in-memory H2 (Postgres mode) using `src/test/resources/application.yml`.

## Database migrations

Flyway scripts live in `src/main/resources/db/migration` (`V<n>__description.sql`). Never edit a migration that has been merged; add a new one.

## Workflow

- Tickets: `team/BACKLOG.md` (IDs like `PAR-101`).
- Branches: `feature/PAR-xxx-short-desc`.
- Open a PR against `main`; `./mvnw -B verify` must pass.

## Team notes

`team/` holds the team's working notes (state, backlog, journal).
