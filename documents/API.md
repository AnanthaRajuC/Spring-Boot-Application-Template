## API

### API Rate Limiting

|     Tier   | API Request Cap |  API Key Prefix  |
|------------|-----------------|------------------|
|FREE        |     25          |     `null`       |
|BASIC       |     50          |     `PX001-`     |
|PROFESSIONAL|     75          |     `BX001-`     |

Rate Limiting header `X-api-key`

[Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) - Rate limiting library based on token/leaky-bucket algorithm - Refer `io.github.anantharajuc.sbat.core_backend.api.rate_limiting` package

### Preventing Brute Force Authentication Attempts

A basic solution for preventing brute force authentication attempts using Spring Security is implemented. The app keeps a record of the number of failed attempts originating from a single IP address. If that particular IP goes over a set number of requests – it will be blocked for a set amount of time.

Refer `io.github.anantharajuc.sbat.core_backend.security.user.authentication.LoginAttemptService`

### Session Timeout

If the application remains inactive for a specified period of time, the session will expire. The session after this period of time is considered invalid and the user has to login to the application again.

This value **server.servlet.session.timeout** can be configured in **application.properties** file


## Explore Rest APIs

The app defines following CRUD APIs. **If localhost doesn't work, use 192.168.99.102**

To enable SSL, toggle **server.ssl.enabled** to **true** and use the **https://** protocol in the URL instead of **http://**

Since the SSL certificate is self signed, turn off the **SSL certificate verification** option while interacting with the URLs via **Postman**

<img src="images\tools\postman-ssl-certificate-verification.PNG"/>

### URLs

|                   URL                  | Method |          Remarks       |
|----------------------------------------|--------|------------------------|
|`http://localhost:8080/index`           | GET    | Home Page              |
|`http://localhost:8080/sbat/index`      | GET    | Home Page              |
|`http://localhost:8080/sbat/about`      | GET    | About Page             |
|`http://localhost:8080/sbat/tech-stack` | GET    | Technology Stack Table |
|`http://localhost:8080/sbat/close`      | GET    | Close App via Actuator |
|`http://localhost:8080/sbat/login`      | GET    | Login Page             |
|`http://localhost:8080/sbat/error`      | GET    | Custom Error Page      |

### Other URLs

|                           URL                                  | Method |
|----------------------------------------------------------------|--------|
|`http://localhost:8080/api/generic-hello`                       |   GET  | 
|`http://localhost:8080/api/personalized-hello/`                 |   GET  | 
|`http://localhost:8080/api/personalized-hello?name=spring-boot` |   GET  | 
|`http://localhost:8080/api/loggers`                             |   GET  | 

### Actuator

To monitor and manage your application

|              URL                          |Method|
|-------------------------------------------|------|
|`http://localhost:8080/actuator/`          |  GET |
|`http://localhost:8080/actuator/health`    |  GET |
|`http://localhost:8080/actuator/info`      |  GET |
|`http://localhost:8080/actuator/prometheus`|  GET |
|`http://localhost:8080/actuator/httptrace` |  GET |
