# Team state (maintained by Priya Raman, tech lead)

- **Sprint:** 1 (started Mon 2026-11-16)
- **Workday count:** 1 (Day 1 = Mon 2026-11-16, onboarding)
- **Julian's level:** mid-level Spring Boot developer (new hire)

## Julian: skill observations

No code submitted yet. Baseline to be set from PAR-101.

- Strengths: (none observed yet)
- Gaps: (none observed yet)

## Things to watch for in PAR-101

- Does he keep JPA entities out of the API (DTOs / records)?
- Bean Validation and `@Valid` placement; how he turns errors into responses (ProblemDetail?)
- Test style: slice tests (`@WebMvcTest`) vs full `@SpringBootTest`; does he test the unhappy paths?
- Commit hygiene and PR description.

## Taught so far

- Day 1: project layout, local run with Docker Compose, Flyway migration rules, branch naming.
- Reading: RFC 9457 Problem Details + Spring's `ProblemDetail` / `ErrorResponse`.

## Open threads

- Repo access: earlier runs (Nov 11, Nov 12, Nov 16 morning) could not push to `main`. The Day 1 scaffold landed on 2026-11-16 (afternoon run).
- `gh` CLI not available to the sim, so tickets live in BACKLOG.md and reviews go out by email for now.
- Starter controller exposes the `Order` entity directly and has no validation. That's on purpose; PAR-101 fixes it.
- Security is wide open on `/api/**` (PAR-104).
- Tests are named `*Test` because only Surefire is configured; Failsafe/`*IT` comes with Testcontainers (PAR-105).
