## API

This application comes with an out-of-the-box API, which will allow you to provide an API to your users or build a mobile app from your API.

### Access Data from the API

In order to access data from the API a user or an application will need to pass an Access Token to the API. This access token along with the **ROLE** of the user will determine what kind of data can be accessed or returned.

- You can request an Access Token with a **username** and a **password**

To get an Access Token from a User Login you can do a POST request to:

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/login`                            | POST   |Bearer Token, Refresh Token is generated       | [JSON](#login)            |

You will get a response similar to the one show below.

~~~json
{
    "authenticationToken": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJqb2huZG9lIiwiZXhwIjoxNjIxMTYwOTk4fQ.TGDRUuBP25SE4bJU2jTpbNku2ZTqDF-cP0JEI0QdMEslvfH7e9J3cxy4aGe86hqTMCcgED7CwyGHDPqsSVQfCDIJPnhkTdrguZc-4m01blg3-SedCDIDg2Xq6oIsYIIDyY92ITiLKxTclzyj289DokwOfxwSQyrNxIkdCJZ8VoKZUeDjmalXM8uDpS7Cf7-dCgCYi7lFpZH0vma6qq62KNfuRV1zhWh9OT4jRoeaNMvbxn2kRA912yDQ0Y1M4EZFqAtS4m_6hiNw9MJ6KbfgpZ5y2oNlabtCOSlSeHtKyFhnFe0S5CX3Vl03hiALGOpxQPP2ayyy9samCG4qC8l11w",
    "refreshToken": "3d0ca7a8-04c5-4bb2-8fe4-0e26b06c6ef1",
    "expiresAt": "2021-05-16T10:24:59.722Z",
    "username": "johndoe"
}
~~~

You'll see that this response includes additional fields **refreshToken** and **expiresAt**. When your application detects the **authenticationToken** has expired it will need you to request a new **authenticationToken** with the following API request:

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/refresh/token`                    | POST   |Refresh Token from login should be passed      | [JSON](#refresh-token)    |


##### sample refresh token request body

```json
{
    "token":"1178cd43-21d2-45b4-8b5f-c79aa1d5b76e",
    "username":"johndoe"
}
```

And you will recieve a new **authenticationToken** for your application to be used. This expiration and refresh tokens are common for keeping your API secure.

### Request Data with an Access Token

Now, that you have an **authenticationToken** you can request data from the application using that token. Based on the permission of the current user they will be able to **CREATE**, **READ**, **UPDATE**, and **DELETE** content in your application.


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

### Authentication, Person, Person Management URLs

- [Authentication API's](documents/AUTHENTICATION.MD)  
- [Person and Person Management API's](documents/USER_ROLES.MD)  

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

