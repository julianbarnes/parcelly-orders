# Team journal

## 2026-11-16 (Monday): Day 1, onboarding / Sprint 1 planning

- **Context:** Earlier runs on Nov 11, Nov 12 and this morning were blocked by missing push access. This afternoon's run scaffolded the repo.
- **Scaffolded:** Spring Boot 3.5.7 / Java 21 starter with a Maven wrapper; `Order` entity, repository and controller; Flyway `V1__create_orders.sql`; temporary open `SecurityConfig`; `OrderControllerTest` (2 tests, H2); `compose.yaml` for Postgres; README. `./mvnw -B verify` green.
- **Julian's work reviewed:** none yet (Day 1).
- **Assigned:** PAR-101 (DTOs + validation + ProblemDetail + service layer). Chore: run the app locally against Postgres. Reading: RFC 9457 / Spring `ProblemDetail`.
- **Emails from Julian:** none found in Gmail.
- **Feedback summary:** n/a.

## 2026-11-17 (Tuesday): Day 2

- **Julian's work reviewed:** PAR-101 on `feature/PAR-101-order-service` (3e42b81). No PR opened. `./mvnw -B verify` green on this run (11 tests: 7 web slice, 2 service, 2 full-context). Verdict: approve with nits (open PR; 255 boundary test; locale-independent assertions; problem `type` URIs). Thinking questions: trimming, `Location` behind the LB, Postgres chore/README.
- **Emails from Julian:** one reply on Nov 16 ("I don't see that branch in the remote"), answered: the ticket's branch name is one he creates.
- **Assigned:** PAR-102 (pagination + sort whitelist + tie-break + envelope). Reading: Spring Data web support / `PagedModel`.
- **Infra:** the 08:48 run sent the standup email but could not build or push, and attached these notes as a patch. The 09:30 run verified the build, pushed the notes to main, and sent a short follow-up telling Julian to ignore the patch.
- **Feedback summary:** strong first submission; habits to build: open PRs, test both sides of boundaries.

## 2026-11-18 (Wednesday): Day 3

- **Julian's work reviewed:** none new. `feature/PAR-101-order-service` still at 3e42b81, no PR, no PAR-102 branch on the remote. Main `./mvnw -B verify` green.
- **Emails from Julian:** none since the Nov 16 branch question.
- **Assigned:** nothing new, on purpose. He still has PAR-101 nits + PR and PAR-102. Sent a kind check-in with a suggested 4-step slicing of PAR-102 and an invitation to raise blockers.
- **Feedback summary:** n/a (no new work).

## 2026-11-19 (Thursday): Day 4

- **Julian's work reviewed:** none new. `feature/PAR-101-order-service` still at 3e42b81, no PR, no PAR-102 branch. Main `./mvnw -B verify` green.
- **Emails from Julian:** none. The Wed standup email is still unread.
- **Assigned:** scoped down. PAR-102 parked back to TODO; PAR-101 nits 3–4 split into PAR-107. Only ask: the PAR-101 merge-ready slice (255-char boundary test + open PR, ~1h). Asked directly and kindly what's blocking him.
- **Feedback summary:** n/a (no new work).

## 2026-11-20 (Friday): Day 5, Sprint 1 retro

- **Julian's work reviewed:** none new. `feature/PAR-101-order-service` still at 3e42b81, no PR, no other branches. Main `./mvnw -B verify` green.
- **Emails from Julian:** none. The Thu standup email has been read but got no reply.
- **Assigned:** nothing new. PAR-101 merge-ready slice (255-char test + PR) stays open and carries into Sprint 2.
- **Retro / 1:1:** strong day-one quality on PAR-101; the growth area is visibility (short replies, early draft PRs); Priya owned the overloaded first days. Offered a 30-min pair on Monday.
- **Sprint 1 outcome:** 0 tickets merged (PAR-101 code done but never PR'd). Velocity is not representative yet.
