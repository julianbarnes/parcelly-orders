# Backlog: parcelly-orders

Status: `TODO` · `IN PROGRESS` · `IN REVIEW` · `DONE`
Branch convention: `feature/PAR-xxx-short-desc`

## Sprint 2 (Mon 2026-11-23 to Fri 2026-11-27, short week)

Commitment: PAR-101 merge-ready slice only. Optional warm-up: PAR-108. Everything else is backlog.


### PAR-101: Order API: request/response DTOs, validation, ProblemDetail errors
**Status:** IN PROGRESS (branch `feature/PAR-101-order-service`; reviewed 2026-11-17: approve with nits, PR not yet opened. 2026-11-19: scoped down to the merge-ready slice; 2026-11-20: unchanged, carries into Sprint 2; 2026-11-23: the only Sprint 2 commitment) · **Type:** Feature · **Estimate:** 2–4h

Required before self-merge (as of 2026-11-19): (1) open the PR; (2) add 255-char → 201 boundary test; green verify. Nits 3–4 moved to PAR-107.

The starter `OrderController` binds and returns the `Order` JPA entity directly and accepts any payload. Clients are about to integrate, so the contract has to be cleaned up first.

Acceptance criteria:
- `POST /api/orders` accepts a `CreateOrderRequest` (record) instead of the entity. `customerName` is required, not blank, max 255 chars.
- All endpoints return an `OrderResponse` (record) with `id`, `customerName`, `status`, `createdAt`. No entity leaves the web layer.
- `POST` returns `201 Created` with a `Location` header pointing to `/api/orders/{id}`.
- Invalid payload → `400` with an RFC 9457 `application/problem+json` body that lists the invalid field(s).
- Unknown id on `GET /api/orders/{id}` → `404` `application/problem+json` (not an empty body).
- Business logic moves out of the controller into an `OrderService`, with a sensible transaction boundary.
- Tests cover: happy create, validation failure (blank and too long), 404 problem body, and the mapping. At least one test should be a web slice (`@WebMvcTest`).
- `./mvnw -B verify` is green; PR description explains the design choices.

### PAR-102: Paginate and sort `GET /api/orders`
**Status:** TODO (parked 2026-11-19, unassigned until PAR-101 merges; 2026-11-23: Marcus OK with Sprint 3) · **Type:** Feature · **Estimate:** 3–4h
Branch: `feature/PAR-102-orders-pagination` (start from PAR-101 branch; rebase onto main after it merges)

Context (Marcus): the partner ops dashboard will poll the order list; ~2k orders/day makes an unbounded `findAll()` untenable.

Acceptance criteria:
- `GET /api/orders?page=0&size=20&sort=createdAt,desc`. Defaults: page 0, size 20, sort `createdAt,desc`.
- `size` > 100 capped (clamp or reject, justified). Negative page or size < 1 → 400 problem+json.
- Sort only on `createdAt`, `customerName`, `status`; anything else → 400 problem+json naming the property. No 500s from `PropertyReferenceException`.
- Deterministic order: ties broken by `id`, so no order appears on two pages.
- Documented envelope, e.g. `{"content": [...], "page": {"number","size","totalElements","totalPages"}}`. No raw `PageImpl` JSON or Spring Data serialization warning.
- Mapping stays inside the read-only transaction.
- Tests: defaults, size cap, bad sort property, negative page, and one repository/integration-level tie-break test.
- `./mvnw -B verify` green; PR explains clamp-vs-reject and the envelope.

### PAR-107: Chore: locale-independent validation assertions + problem `type` URIs
**Status:** TODO · **Type:** Chore · **Estimate:** 1h · Split out of the PAR-101 review on 2026-11-19
- Tests don't assert on locale-dependent Hibernate Validator default messages (assert a field/code, or pin `Locale`).
- Problem responses use stable `type` URIs (for example `https://api.parcelly.example/problems/validation`) instead of `about:blank`.
- `./mvnw -B verify` is green.

### PAR-108: Docs: README troubleshooting section
**Status:** TODO (offered 2026-11-23 as an optional warm-up) · **Type:** Docs / Chore · **Estimate:** 30 min
Branch: `feature/PAR-108-readme-troubleshooting`
- README gains a "Troubleshooting" section: port 5432 already in use, Docker not running, resetting the local DB (`docker compose down -v`), stopping everything.
- Every documented command is run by the author first.
- PR opened; `./mvnw -B verify` stays green.

### PAR-103: Cancel an order
**Status:** TODO · **Type:** Feature
- `POST /api/orders/{id}/cancel`; only `CREATED` orders can be cancelled. Otherwise `409` ProblemDetail.
- Idempotency semantics documented in the PR.

### PAR-104: Secure the API
**Status:** TODO · **Type:** Feature / Security
- Remove the temporary `permitAll` on `/api/**`; decide on an auth approach (written proposal first).

### PAR-105: Testcontainers Postgres integration tests
**Status:** TODO · **Type:** Test infrastructure
- Add Failsafe and a `*IT` suite against real Postgres via Testcontainers; keep H2 for fast unit/slice tests or drop it (justify).

### PAR-106: Customer and Shipment aggregates (design)
**Status:** TODO · **Type:** Design
- Short written proposal: entities, relationships, migrations, API surface.
