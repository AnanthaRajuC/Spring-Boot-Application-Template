## API

This application comes with an out-of-the-box REST API, which lets you offer an API to your users or build a mobile app on top of it.

* [Access data from the API](#access-data-from-the-api)
* [Errors](#errors)
* [API rate limiting](#api-rate-limiting)
* [Preventing brute force authentication attempts](#preventing-brute-force-authentication-attempts)
* [Session timeout](#session-timeout)
* [Explore REST APIs](#explore-rest-apis)

### Access data from the API

To access data a user or an application passes an **access token** with every request. The token identifies the user; the user's **roles and permissions**, read from the database on every request, decide what can be accessed.

1.	Get an access token with a username and password:

	```shell
	curl -s -X POST http://localhost:8080/api/v1/auth/login \
	     -H 'Content-Type: application/json' \
	     -d '{"username":"johndoe","password":"password"}'
	```

	```json
	{
	    "authenticationToken": "eyJhbGciOiJSUzI1NiJ9...",
	    "refreshToken": "3d0ca7a8-04c5-4bb2-8fe4-0e26b06c6ef1",
	    "expiresAt": "2026-10-01T10:24:59Z",
	    "username": "johndoe"
	}
	```

2.	Send it in the `Authorization` header:

	```shell
	curl -s http://localhost:8080/api/v1/user/username/johndoe \
	     -H "Authorization: Bearer $ACCESS_TOKEN"
	```

3.	When the token has expired (see `expiresAt`), exchange the refresh token for a new pair. The old refresh token stops working.

	```shell
	curl -s -X POST http://localhost:8080/api/v1/auth/refresh/token \
	     -H 'Content-Type: application/json' \
	     -d "{\"token\":\"$REFRESH_TOKEN\"}"
	```

HTTP Basic (`-u Admin1:password`) is accepted as well, which is convenient for scripts and for scraping `/actuator/prometheus`.

See [Authentication](AUTHENTICATION.MD) for sign-up, verification, logout and token lifetimes, and [User Roles](USER_ROLES.MD) for who may call which endpoint.

### Errors

| Status | Meaning |
|--------|---------|
| `400 Bad Request` | Invalid request body (validation), or a missing `X-api-key` header on rate limited endpoints |
| `401 Unauthorized` | No token, an invalid or expired token, wrong credentials, a disabled account or a blocked client |
| `403 Forbidden` | Authenticated, but the user's role or permissions don't allow the request |
| `404 Not Found` | The requested resource doesn't exist |
| `429 Too Many Requests` | Rate limit exhausted, see below |

Errors raised by the REST controllers are returned as [RFC 9457](https://www.rfc-editor.org/rfc/rfc9457) problem details:

```json
{
    "title": "Unauthorized",
    "status": 401,
    "detail": "Invalid Refresh Token",
    "instance": "/api/v1/auth/refresh/token"
}
```

### API rate limiting

Requests to `/api/v1/person/**` must carry an `X-api-key` header. The key's prefix selects the tier; each key gets its own bucket that refills completely every 20 minutes.

|     Tier     | Requests per 20 minutes |  API key prefix   |
|--------------|-------------------------|-------------------|
| FREE         | 25                      | any other value   |
| BASIC        | 50                      | `BX001-`          |
| PROFESSIONAL | 75                      | `PX001-`          |

Responses include `X-Rate-Limit-Remaining`. When the bucket is empty the API answers `429 Too Many Requests` with `X-Rate-Limit-Retry-After-Seconds`.

The keys are not validated against a list of customers: this is a demonstration of [Bucket4j](https://github.com/bucket4j/bucket4j) to adapt to your own API key handling. Refer to the `io.github.anantharajuc.sbat.core_backend.api.rate_limiting` package.

### Preventing brute force authentication attempts

The app counts failed login attempts per client IP address. After 5 failures the address is blocked for 15 minutes; both values are configurable. See [Login throttling](AUTHENTICATION.MD#login-throttling).

### Session timeout

Web UI sessions expire after 10 minutes of inactivity, after which the user has to log in again (unless "remember me" was ticked). Change it with `server.servlet.session.timeout` in `application.properties`. The REST API has no sessions; access tokens expire instead.

## Explore REST APIs

The interactive [Swagger UI](http://localhost:8080/swagger-ui.html) lists every endpoint with its request and response models, and can call them. It is available in every profile except `production`. See [Documentation](DOCUMENTATION.MD).

### Authentication, Person, Person Management and RBAC URLs

- [Authentication APIs](AUTHENTICATION.MD)
- [Person, Person Management and RBAC user management APIs](USER_ROLES.MD)

### Web UI URLs

|                   URL                    | Method |          Remarks                        |
|------------------------------------------|--------|-----------------------------------------|
|`http://localhost:8080/`                  | GET    | Redirects to the home page              |
|`http://localhost:8080/sbat/index`        | GET    | Home page (public)                      |
|`http://localhost:8080/sbat/login`        | GET    | Login page (public)                     |
|`http://localhost:8080/sbat/about`        | GET    | About page                              |
|`http://localhost:8080/sbat/tech-stack`   | GET    | Technology stack table                  |
|`http://localhost:8080/sbat/form`         | GET    | Sample form                             |
|`http://localhost:8080/sbat/profile`      | GET    | Profile of the logged-in user           |
|`http://localhost:8080/sbat/listPersons`  | GET    | Paginated person list                   |
|`http://localhost:8080/sbat/settings`     | GET    | Logged-in users (ADMIN only)            |
|`http://localhost:8080/sbat/close`        | POST   | Shut down the application (ADMIN only)  |

### Other URLs

|                           URL                                  | Method | Access |
|----------------------------------------------------------------|--------|--------|
|`http://localhost:8080/api/generic-hello`                       |   GET  | Public |
|`http://localhost:8080/api/personalized-hello?name=spring-boot` |   GET  | Public |
|`http://localhost:8080/api/loggers`                             |   GET  | Authenticated, writes a log line at every level |
|`http://localhost:8080/api/postman-echo/GETrequest`             |   GET  | Authenticated, sample outbound call to postman-echo.com |
|`http://localhost:8080/api/postman-echo/POSTrequest`            |   GET  | Authenticated, sample outbound form POST to postman-echo.com |

### Actuator

To monitor and manage your application.

|              URL                                  |Method| Access |
|---------------------------------------------------|------|--------|
|`http://localhost:8080/actuator/health`            |  GET | Public (details only for ADMIN) |
|`http://localhost:8080/actuator/health/liveness`   |  GET | Public |
|`http://localhost:8080/actuator/health/readiness`  |  GET | Public |
|`http://localhost:8080/actuator/info`              |  GET | Public |
|`http://localhost:8080/actuator`                   |  GET | ADMIN  |
|`http://localhost:8080/actuator/metrics`           |  GET | ADMIN  |
|`http://localhost:8080/actuator/prometheus`        |  GET | ADMIN  |
|`http://localhost:8080/actuator/customActuatorEndpoint` | GET | ADMIN |
