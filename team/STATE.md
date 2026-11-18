# Team state (maintained by Priya Raman, tech lead)

- **Sprint:** 1 (started Mon 2026-11-16)
- **Workday count:** 3 (Day 1 = Mon 2026-11-16, onboarding; Day 2 = Tue 2026-11-17; Day 3 = Wed 2026-11-18)
- **Julian's level:** mid-level Spring Boot developer (new hire)

## Julian: skill observations

Baseline from PAR-101 (branch `feature/PAR-101-order-service`, commit 3e42b81, pushed Mon evening; verify green, 11 tests):

- Strengths: fast turnaround; clear commit message that explains the *why*; good Spring idioms (records as DTOs, `ResponseEntityExceptionHandler` subclass, `ErrorResponseException` for 404, class-level `@Transactional(readOnly = true)` with write opt-in, mapping inside the tx because open-in-view is off); thorough web-slice tests including malformed JSON, missing field, mass assignment, `verifyNoInteractions`.
- Gaps / nits: pushed a branch but did not open a PR; tests only the failing side of the 255 boundary; asserts on locale-dependent Hibernate Validator messages; problem `type` left as `about:blank`.
- Calibration: stronger than a typical new mid-level. Ramp up: PAR-102 has real edge cases (sort whitelist, tie-break, envelope). Consider a design proposal (PAR-104/PAR-106) or a teammate PR review later this week.

## Things to watch for in PAR-102

- Sort whitelist: no 500 from `PropertyReferenceException`; where does the whitelist live?
- Deterministic ordering (tie-break on `id`) proven by a repo/integration-level test.
- Clamp vs reject justification for `size` > 100; own envelope vs `PagedModel`.
- Does he open a PR this time, and address the PAR-101 nits before merging?

- Day 3 (2026-11-18): no new commits, branches, PR or email replies since Day 2. Too early to read anything into it (PAR-102 is 3–4h on top of PAR-101 nits). If still nothing by Thursday, assign something smaller and ask directly about blockers.

## Taught so far

- Day 1: project layout, local run with Docker Compose, Flyway migration rules, branch naming.
- Reading: RFC 9457 Problem Details + Spring's `ProblemDetail` / `ErrorResponse`.
- Day 2: boundary testing (test both sides), locale-independent assertions (error codes vs default messages), stable problem `type` URIs; thinking prompts on input trimming and `server.forward-headers-strategy` for `Location` behind a load balancer.
- Reading: Spring Data "Web support" (`PagedModel`, `pageSerializationMode = VIA_DTO`).
- Day 3: suggested slicing PAR-102 into four pushes (defaults+envelope, size/page bounds, sort whitelist, tie-break `@DataJpaTest`); encouraged draft PRs.

## Open threads

- 2026-11-18: check-in email sent, no new ticket. Next run: if he still has no activity, offer a smaller ticket and ask what's blocking him; if PAR-101 PR appears, review the nit fixes.

- 2026-11-17: the 08:48 standup run could not build (Maven Central 403) or push (repo not authorized); it emailed team notes as a patch. The 09:30 run had full access: verified PAR-101 green and pushed these notes directly. Julian should NOT apply the patch.
- PAR-101 waiting on Julian: open PR, fix 4 nits, answer 3 thinking questions (trim, forward headers, Postgres chore/README), then self-merge.
- Julian's Day 1 question ("I don't see that branch in the remote") answered: he creates the branch himself.

- Repo access: earlier runs (Nov 11, Nov 12, Nov 16 morning) could not push to `main`. The Day 1 scaffold landed on 2026-11-16 (afternoon run).
- `gh` CLI not available to the sim, so tickets live in BACKLOG.md and reviews go out by email for now.
- Starter controller exposes the `Order` entity directly and has no validation. That's on purpose; PAR-101 fixes it.
- Security is wide open on `/api/**` (PAR-104).
- Tests are named `*Test` because only Surefire is configured; Failsafe/`*IT` comes with Testcontainers (PAR-105).
