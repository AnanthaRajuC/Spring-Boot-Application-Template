## Technical Functionalities and To-Do

<details open="open">
   <ul>
      <li>[x] Logger (Console, File)</li>
      <li>[x] <a href="https://en.wikipedia.org/wiki/Light-on-dark_color_scheme">Dark Mode</a></li>
      <li>[x] <a href="https://spring.io/projects/spring-security">Spring Security</a> RBAC, Session Timeout, CSRF protection for the web UI</li>
      <li>[x] <a href="https://jwt.io/">JSON Web Token</a> based API authentication with rotating, single-use refresh tokens</li>
      <li>[x] E-mail verification of new accounts</li>
      <li>[x] Login throttling per client IP</li>
      <li>[x] API <a href="https://en.wikipedia.org/wiki/Rate_limiting">Rate Limiting</a></li>
      <li>[x] <a href="https://docs.spring.io/spring-data/jpa/reference/auditing.html">JPA Auditing via AuditorAware Interface</a></li>
      <li>[x] <a href="https://docs.spring.io/spring-boot/reference/features/profiles.html">Spring Profiles</a> (dev, production, qa, staging, test) with configuration from environment variables</li>
      <li>[x] Database migrations with <a href="https://flywaydb.org/">Flyway</a></li>
      <li>[x] <a href="https://www.docker.com/">Docker</a> (multi-stage image, Docker Compose with MySQL)</li>
      <li>[x] <a href="https://docs.spring.io/spring-boot/reference/io/caching.html">Caching</a></li>
      <li>[x] <a href="https://en.wikipedia.org/wiki/HATEOAS">HATEOAS (Hypermedia as the Engine of Application State)</a></li>
      <li>[x] Error responses as <a href="https://www.rfc-editor.org/rfc/rfc9457">RFC 9457</a> problem details</li>
      <li>[x] Software documentation - <a href="https://springdoc.org/">OpenAPI 3 / Swagger UI</a>, <a href="https://en.wikipedia.org/wiki/Javadoc">Javadoc</a>, <a href="https://www.postman.com/collection/">Postman Collection</a></li>
      <li>[x] <a href="https://en.wikipedia.org/wiki/HTTPS">HTTPS</a> (bring your own certificate)</li>
      <li>[x] Integration tests</li>
      <li>[x] Code coverage with JaCoCo</li>
      <li>[x] Continuous integration with GitHub Actions</li>
      <li>[x] Shut down the app from the UI (admin only)</li>
      <li>[x] Micrometer metrics exported for Prometheus</li>
      <li>[ ] Quartz Scheduler</li>
      <li>[ ] Multiple Databases</li>
      <li>[ ] <a href="https://www.jooq.org/">jOOQ</a></li>
      <li>[ ] Testcontainers based tests against MySQL</li>
      <li>[ ] Spring Boot Admin</li>
      <li>[ ] NoSQL (MongoDB)</li>
      <li>[ ] Multitenancy</li>
      <li>[ ] Grafana dashboards</li>
      <li>[ ] Spring Retry</li>
   </ul>
</details>
