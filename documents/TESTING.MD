## Testing

<details open="open">
	<ul>
		<li><a href="#automated-tests">Automated tests</a></li>
		<li><a href="#testing-with-postman">Testing with Postman</a></li>
		<li><a href="#basic-load-testing">Basic load testing</a></li>
	</ul>
</details>

### Automated tests

```shell
./mvnw test      # run the tests
./mvnw verify    # run the tests and write the coverage report, see CODE_COVERAGE.MD
```

`src/test/java/io/github/anantharajuc/sbat/TestSBtemplateApplication.java` contains integration tests that start the whole application (`@SpringBootTest`) on the in-memory H2 database and call it through `MockMvc`. They cover:

*	**Web UI:** public pages, redirect to the login page, form login, rejection of a login without a CSRF token, admin-only pages.
*	**REST API:** missing and invalid tokens (401), role checks (403), admin access to RBAC and actuator endpoints, creating a user with a new role through `/rbac/user`.
*	**Tokens:** a refresh token cannot be used for another user, refresh tokens are rotated and single use.
*	**Login throttling:** a client IP is blocked after 5 failed logins while other clients are not.
*	**Account lifecycle:** sign-up validation, login only after e-mail verification, expired verification links remove only the unverified account.

Outgoing e-mail is replaced by a mock (`@MockitoBean JavaMailSender`), so no SMTP server is needed.

The same `./mvnw verify` runs in GitHub Actions on every push to `master` and every pull request (`.github/workflows/build.yml`), together with a Docker image build.

### Testing with Postman

You can try the API with [Postman](https://www.postman.com/). Import the collection from:

`documents/Spring Boot Application Template.postman_collection.json`

[![Postman](images/Postman.PNG)](images/Postman.PNG)

To call protected endpoints, first send the **login** request, copy `authenticationToken` from the response and set it as the collection's **Bearer Token** (Authorization tab). Requests to the person APIs (`/api/v1/person/**`, `/api/v1/management/person/**`) also need an `X-api-key` header; the collection already sends one. See [API rate limiting](API.md#api-rate-limiting).

The [Swagger UI](http://localhost:8080/swagger-ui.html) offers the same, without installing anything: click **Authorize** and paste the token.

### Basic load testing

Basic load testing can be performed with ApacheBench. The person APIs are rate limited per API key (75 requests per 20 minutes at most), so point it at an endpoint without the rate limit, such as the public hello endpoint, or use a fresh API key per run and keep the request count below the limit:

```shell
ab -n 10000 -c 100 -k "http://localhost:8080/api/personalized-hello?name=load-test"
```

```shell
ab -n 75 -c 10 -k -H "Authorization: Bearer $ACCESS_TOKEN" -H "X-api-key: PX001-$(date +%s)" \
   http://localhost:8080/api/v1/management/person/id/1
```

* **-n** is the number of requests to make
* **-c** is the number of concurrent requests to make at a time
* **-k** sends the **KeepAlive** header, which asks the web server to not shut down the connection after each request is done, but to instead keep reusing it
* **-H** adds a request header, here the access token and the API key

Access tokens are valid for 5 minutes by default (the `jwtExpirationTime` application setting), so get a fresh one right before the run.
