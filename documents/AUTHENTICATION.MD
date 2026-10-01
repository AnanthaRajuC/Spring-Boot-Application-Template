## Authentication

Spring Boot Application Template is built on top of Spring Security and adds e-mail verification, JWT access tokens with rotating refresh tokens, and login throttling.

* [Web UI vs. REST API](#web-ui-vs-rest-api)
* [Default accounts](#default-accounts)
* [Registration](#registration)
* [Email Verification](#email-verification)
* [Login](#login)
* [Refreshing the access token](#refreshing-the-access-token)
* [Logout](#logout)
* [Login throttling](#login-throttling)

### Web UI vs. REST API

There are two separate security setups:

| | Web UI (`/sbat/**` and the rest) | REST API (`/api/**`, `/rbac/**`, `/actuator/**`) |
|---|---|---|
| Login | Form login at `/sbat/login`, optional "remember me" (21 days) | `POST /api/v1/auth/login` returns a JWT, or HTTP Basic |
| State | HTTP session (10 minute timeout, one session per user) | Stateless: send `Authorization: Bearer <token>` on every request |
| CSRF protection | Enabled, Thymeleaf forms include the token automatically | Not needed: requests are authenticated by a header, not a cookie |
| Failure response | Redirect to the login page, or the `/403` page | `401 Unauthorized` / `403 Forbidden` |

### Default accounts

The Flyway migrations seed these accounts. Change or remove them before exposing an instance.

|     Username     | Password |     Role     |
|------------------|----------|--------------|
|`johndoe`         |`password`|`PERSON`      |
|`janedoe`         |`password`|`PERSON`      |
|`Admin1`          |`password`|`ADMIN`       |
|`Admin2`          |`password`|`ADMIN`       |
|`AdminTrainee1`   |`password`|`ADMINTRAINEE`|
|`AdminTrainee2`   |`password`|`ADMINTRAINEE`|

### Registration

Users register with a username, an e-mail address and a password. The account starts **disabled** and gets the `PERSON` role.

|                       URL                       | Method | Sample request body |
|-------------------------------------------------|--------|---------------------|
|`http://localhost:8080/api/v1/auth/signup`       | POST   | [JSON](#signup)     |

Validation: a valid e-mail address, a username of 3 to 50 characters and a password of 8 to 100 characters, otherwise `400 Bad Request`. An already registered username or e-mail address also gives `400`.

##### <a id="signup">Signup -> /api/v1/auth/signup</a>
```json
{
    "username":"janesmith",
    "email":"jane.smith@example.com",
    "password":"a-strong-password"
}
```

### Email Verification

After sign-up an e-mail with a verification link is sent (see [SMTP](GETTING_STARTED.MD#smtp-for-account-verification-e-mails); [Mailtrap](https://mailtrap.io/) is the simplest way to test it). Opening the link enables the account. The link is valid for the number of minutes stored in the `verificationTokenValidity` application setting (10 by default). If it has expired, the unverified account is removed so the user can register again.

|                                          URL                        | Method |
|---------------------------------------------------------------------|--------|
|`http://localhost:8080/api/v1/auth/verification/{verification-token}`| GET    |

### Login

|                       URL                       | Method | Sample request body |
|-------------------------------------------------|--------|---------------------|
|`http://localhost:8080/api/v1/auth/login`        | POST   | [JSON](#login)      |

##### <a id="login">Login -> /api/v1/auth/login</a>
```json
{
    "username":"johndoe",
    "password":"password"
}
```

Response:

```json
{
    "authenticationToken": "eyJhbGciOiJSUzI1NiJ9...",
    "refreshToken": "3d0ca7a8-04c5-4bb2-8fe4-0e26b06c6ef1",
    "expiresAt": "2026-10-01T10:24:59Z",
    "username": "johndoe"
}
```

*	`authenticationToken` is an RS256 signed JWT. Send it as `Authorization: Bearer <authenticationToken>`. It is valid for the number of seconds stored in the `jwtExpirationTime` application setting (300 by default); `expiresAt` is that moment.
*	`refreshToken` is used to get a new access token without sending the password again.

Wrong credentials, a disabled (unverified) account or a blocked client all give `401 Unauthorized`.

### Refreshing the access token

|                       URL                       | Method | Sample request body     |
|-------------------------------------------------|--------|-------------------------|
|`http://localhost:8080/api/v1/auth/refresh/token`| POST   | [JSON](#refresh-token)  |

##### <a id="refresh-token">Refresh Token -> /api/v1/auth/refresh/token</a>
```json
{
    "token":"3d0ca7a8-04c5-4bb2-8fe4-0e26b06c6ef1"
}
```

The response has the same shape as the login response.

*	The new access token is always issued for the user the refresh token belongs to. `username` may be sent as well; if it doesn't match the token's owner the request is rejected.
*	Refresh tokens are **single use**: every refresh returns a new refresh token and the old one stops working.
*	Refresh tokens expire after `sbat.security.refresh-token-validity` (7 days by default). After that the user has to log in again.

### Logout

Deletes a refresh token so it can no longer be used. The access token stays valid until it expires, so keep its lifetime short.

|                       URL                       | Method |
|-------------------------------------------------|--------|
|`http://localhost:8080/api/v1/auth/logout`       | POST   |

```json
{
    "token":"3d0ca7a8-04c5-4bb2-8fe4-0e26b06c6ef1",
    "username":"johndoe"
}
```

In the web UI, the logout button in the user menu ends the session and removes the remember-me cookie.

### Login throttling

Failed logins are counted per client IP address, for the web form, HTTP Basic and the API login. After `sbat.security.max-login-attempts` failures (5 by default) the address is blocked for `sbat.security.login-block-duration` (15 minutes by default, counted from the last failure); even correct credentials are refused meanwhile. A successful login resets the counter.

The client IP is the connection's remote address. Behind a reverse proxy, set `FORWARD_HEADERS_STRATEGY` so the address from `X-Forwarded-For` is used; without it the header is ignored, so clients cannot spoof it.

Refer to `io.github.anantharajuc.sbat.core_backend.security.user.authentication.LoginAttemptService`.
