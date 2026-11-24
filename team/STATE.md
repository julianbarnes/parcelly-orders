# Team state (maintained by Priya Raman, tech lead)

- **Sprint:** 2 (started Mon 2026-11-23; Sprint 1 ran 2026-11-16 to 11-20)
- **Workday count:** 7 (Day 1 = Mon 2026-11-16, onboarding; Day 2 = Tue 2026-11-17; Day 3 = Wed 2026-11-18; Day 4 = Thu 2026-11-19; Day 5 = Fri 2026-11-20; Day 6 = Mon 2026-11-23, Sprint 2 planning; Day 7 = Tue 2026-11-24)
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

- Day 4 (2026-11-19): still no new commits, branches, PR or email replies (the Wed standup email is still unread). Scoped down as planned: PAR-102 parked (TODO, unassigned); PAR-101 nits 3–4 split into chore PAR-107; the only ask is the PAR-101 merge-ready slice (255 boundary test, open PR, ~1h). Asked him directly about blockers.

- Day 5 (2026-11-20): still no new commits, branches, PR or replies. The Thu standup email *was* read (no UNREAD label), so he is seeing the emails. Sent the Friday retro/1:1: praised the PAR-101 quality, asked for visibility (one-line replies, draft PRs), and acknowledged Priya front-loaded too much. Nothing new assigned; offered a 30-min pair on Monday (reply "pair").

- Day 6 (2026-11-23, Mon): still no new commits, branches, PR or replies (6 workdays since 3e42b81). The Fri retro email was read. Sprint 2 planning: the only commitment is the PAR-101 merge-ready slice; added an optional 30-min warm-up PAR-108 (README troubleshooting) as an *alternative* way back in, not extra work. Short week (US Thanksgiving Thu 11-26).

- Day 7 (2026-11-24, Tue): still no new commits, branches, PR or replies (7 workdays since 3e42b81). The Mon Sprint 2 planning email is still **unread**. Sent a short check-in as planned: no new ticket, no repeat of the full plan.

## Taught so far

- Day 1: project layout, local run with Docker Compose, Flyway migration rules, branch naming.
- Reading: RFC 9457 Problem Details + Spring's `ProblemDetail` / `ErrorResponse`.
- Day 2: boundary testing (test both sides), locale-independent assertions (error codes vs default messages), stable problem `type` URIs; thinking prompts on input trimming and `server.forward-headers-strategy` for `Location` behind a load balancer.
- Reading: Spring Data "Web support" (`PagedModel`, `pageSerializationMode = VIA_DTO`).
- Day 3: suggested slicing PAR-102 into four pushes (defaults+envelope, size/page bounds, sort whitelist, tie-break `@DataJpaTest`); encouraged draft PRs.

## Open threads

- 2026-11-24 (Tue): short check-in sent, nothing assigned. Next run (Wed 11-25): if a PAR-101/PAR-108 branch or PR or a reply appears, review it promptly and generously. If still silent, a one- or two-line note at most (or skip the email). Thu 11-26 is Thanksgiving: skip or send a one-liner. Fri 11-27: brief retro. If there's still no activity by then, frame Sprint 3 around a fresh, small, self-contained ticket instead of the stale PAR-101 ask.

- 2026-11-23 (Mon): Sprint 2 planning sent. Next run (Tue 11-24): if a PAR-101 or PAR-108 PR/branch appears, review it promptly and generously (approve PAR-101 if the 255 test is in and verify is green). If he replied "pair", acknowledge with a concrete agenda. If still silent: keep it to a 3-line check-in, assign nothing, and don't repeat the full plan. Treat Thu 11-26/Fri 11-27 as holiday-light (Thu: skip or one-liner; Fri: brief retro).

- 2026-11-20 (Fri): retro sent, no new ticket. **Mon 2026-11-23 = Sprint 2 planning.** If a PAR-101 PR or reply exists: review it, then plan Sprint 2 (PAR-107 small chore + PAR-102). If he replied "pair": acknowledge it and give one tiny, concrete pairing agenda (open the PAR-101 PR together). If still silent after 5 workdays: roll PAR-101 into Sprint 2 as the only commitment and keep the tone warm; consider a very small, self-contained starter (e.g. a 30-min README/run-locally fix) to rebuild momentum. Don't pile on.

- 2026-11-19: waiting for a blocker reply and a PAR-101 PR. Fri 2026-11-20 run: if a PR exists, review it (approve if the boundary test is in and the build is green) and reassign PAR-102. If still silent, send a short, warm Friday 1:1/retro note, assign nothing new, and offer to pair. Don't escalate tone.

- 2026-11-18: check-in email sent, no new ticket. Next run: if he still has no activity, offer a smaller ticket and ask what's blocking him; if PAR-101 PR appears, review the nit fixes.

- 2026-11-17: the 08:48 standup run could not build (Maven Central 403) or push (repo not authorized); it emailed team notes as a patch. The 09:30 run had full access: verified PAR-101 green and pushed these notes directly. Julian should NOT apply the patch.
- PAR-101 waiting on Julian: open PR, fix 4 nits, answer 3 thinking questions (trim, forward headers, Postgres chore/README), then self-merge.
- Julian's Day 1 question ("I don't see that branch in the remote") answered: he creates the branch himself.

- Repo access: earlier runs (Nov 11, Nov 12, Nov 16 morning) could not push to `main`. The Day 1 scaffold landed on 2026-11-16 (afternoon run).
- `gh` CLI not available to the sim, so tickets live in BACKLOG.md and reviews go out by email for now.
- Starter controller exposes the `Order` entity directly and has no validation. That's on purpose; PAR-101 fixes it.
- Security is wide open on `/api/**` (PAR-104).
- Tests are named `*Test` because only Surefire is configured; Failsafe/`*IT` comes with Testcontainers (PAR-105).
