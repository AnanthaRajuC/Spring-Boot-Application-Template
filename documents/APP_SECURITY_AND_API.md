## Table of Contents

<details open="open">
   <ul>
      <li>
         <a href="#security">Security</a>
         <ul>
            <li>
               <a href="#looking-for-something-in-particular-">Looking for something in particular?</a>
               <ul>
                  <li>
                     <a href="#sample-valid-json-request-bodys">Sample Valid JSON Request Bodys</a>
                     <ul>
                        <li><a href="#-a-id--signup--signup-----api-auth-signup--a-"><a id="signup">Signup -&gt; /api/auth/signup</a></a></li>
                        <li><a href="#-a-id--login--login-----api-auth-login--a-"><a id="login">Login -&gt; /api/auth/login</a></a></li>
                        <li><a href="#-a-id--subreddit--subreddit-----api-subreddit--a-"><a id="subreddit">Subreddit -&gt; /api/subreddit</a></a></li>
                        <li><a href="#-a-id--refresh-token--refresh-token-----api-auth-refresh-token--a-"><a id="refresh-token">Refresh Token -&gt; /api/auth/refresh/token</a></a></li>
                     </ul>
                  </li>
               </ul>
            </li>
            <li><a href="#api-rate-limiting">API Rate Limiting</a></li>
            <li><a href="#preventing-brute-force-authentication-attempts">Preventing Brute Force Authentication Attempts</a></li>
            <li><a href="#session-timeout">Session Timeout</a></li>
         </ul>
      </li>
      <li>
         <a href="#explore-rest-apis">Explore Rest APIs</a>
         <ul>
            <li><a href="#urls">URLs</a></li>
            <li><a href="#other-urls">Other URLs</a></li>
            <li><a href="#actuator">Actuator</a></li>
            <li>
               <a href="#person-urls">Person URLs</a>
               <ul>
                  <li><a href="#accessible-to---johndoe---user-only">Accessible to <strong>johndoe</strong> user only</a></li>
               </ul>
            </li>
            <li>
               <a href="#person-management-urls">Person Management URLs</a>
               <ul>
                  <li><a href="#role-and-permission-based-secure-access-to---adminuser---and---admintrainee---users">Role and Permission based secure access to <strong>AdminUser</strong> and <strong>AdminTrainee</strong> users</a></li>
                  <li>
                     <a href="#sample-valid-json-request-bodys-1">Sample Valid JSON Request Bodys</a>
                     <ul>
                        <li><a href="#-a-id--personcreate--create-person-----api-person--a-"><a id="personcreate">Create Person -&gt; /api/person</a></a></li>
                     </ul>
                  </li>
               </ul>
            </li>
         </ul>
      </li>
   </ul>
</details>

## Security

### Looking for something in particular?

[Role Based Authentication (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Permission Based Authentication (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Spring Method-Security with @PreAuthorize (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Database Authentication Authorization (In-memory Users)](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Role, Permission based User Authentication via MySQL](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

[Json Web Token - Role Based Access Controll via MySQL](https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template/tags)

Refer to the `ApplicationSecurityConfig` class in `io.github.anantharajuc.sbat.backend.security`.

*	A Java Keystore File is required to generate JSON Web Token.

```shell
keytool -genkey -alias redditclone -keyalg RSA -keystore redditclone.jks -keysize 2048
```

<img src="images\settings\reddit-clone-jks-generation.PNG"/>

|     Username     | Password |     Role     |                      Permission                       |         Resource          |
|------------------|----------|--------------|-------------------------------------------------------|---------------------------|
|`johndoe`         |`password`|`PERSON`      |                                                       |`/api/v1/person`           |
|`AdminUser`       |`password`|`ADMIN`       |`PERSON_CREATE,PERSON_READ,PERSON_UPDATE,PERSON_DELETE`|`/management/api/v1/person`|
|`AdminTraineeUser`|`password`|`ADMINTRAINEE`|`PERSON_READ`                                          |`/management/api/v1/person`|

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/signup`                           | POST   |                                               | [JSON](#signup)           |
|`http://localhost:8080/api/v1/auth/verification/{verification-token}`| GET    |                                               |                           |
|`http://localhost:8080/api/v1/auth/login`                            | POST   |Bearer Token, Refresh Token is generated       | [JSON](#login)            |
|`http://localhost:8080/api/v1/subreddit`                             | POST   |Bearer Token should be passed for authorization| [JSON](#subreddit)        |
|`http://localhost:8080/api/v1/auth/refresh/token`                    | POST   |Refresh Token from login should be passed      | [JSON](#refresh-token)    |

#### Sample Valid JSON Request Bodys

##### <a id="signup">Signup -> /api/auth/signup</a>
```json
{
    "username":"johndoe",
    "email":"domain@example.com",
    "password":"abcd1234"
}
```

##### <a id="login">Login -> /api/auth/login</a>
```json
{
    "username":"johndoe",
    "password":"abcd1234"
}
```

##### <a id="subreddit">Subreddit -> /api/subreddit</a>
```json
{
    "name":"my-cool-subreddit",
    "description":"My subreddit for all thing cool."
}
```

##### <a id="refresh-token">Refresh Token -> /api/auth/refresh/token</a>
```json
{
    "token":"1178cd43-21d2-45b4-8b5f-c79aa1d5b76e",
    "username":"johndoe"
}
```

### API Rate Limiting

|     Tier   | API Request Cap |  API Key Prefix  |
|------------|-----------------|------------------|
|FREE        |     25          |     `null`       |
|BASIC       |     50          |     `PX001-`     |
|PROFESSIONAL|     75          |     `BX001-`     |

Rate Limiting header `X-api-key`

[Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) - Rate limiting library based on token/leaky-bucket algorithm - Refer `io.github.anantharajuc.sbat.backend.api.rate_limiting` package

### Preventing Brute Force Authentication Attempts

A basic solution for preventing brute force authentication attempts using Spring Security is implemented. The app keeps a record of the number of failed attempts originating from a single IP address. If that particular IP goes over a set number of requests – it will be blocked for a set amount of time.

Refer `io.github.anantharajuc.sbat.backend.security.authentication.LoginAttemptService`

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

### Person URLs

#### Accessible to **johndoe** user only

|                           URL                            |  Method |                                         Remarks                                     | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/person`                     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/api/v1/person`                     | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/api/v1/person/{id}`                | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/api/v1/person/{id}`                | PUT     | Update a person                                                                     |    [JSON](#personcreate)  |
|`http://localhost:8080/api/v1/person/{id}`                | DELETE  | Delete a person                                                                     |                           |

### Person Management URLs

#### Role and Permission based secure access to **AdminUser** and **AdminTrainee** users

|                          URL                             |  Method |                                       Remarks                                       | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/management/api/v1/person`          | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person`          | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/management/api/v1/person/{id}`     | PUT     | Update a person                                                                     |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | DELETE  | Delete a person                                                                     |                           |


#### Sample Valid JSON Request Bodys

##### <a id="personcreate">Create Person -> /api/person</a>
```json
{
	"name": "Jane",
	"username": "janejane",
	"emailPrimary": "jane1.howell@gmail.com",
	"emailSecondary": "jane2.howell@gmail.com",
	"phone":9191919191,
	"gender": "FEMALE",
	"age": 25,
	"password": "password",
	"dob":"25-12-2005",
	"isAdult":true,
	"address": {
		"street": "Jane Plains",
		"suite": "Suite 779",
		"city": "Wisokyburghh",
		"zipcode": "90565-7771",
		"geo": {
			"lat": "-43.9589",
			"lng": "-34.4628"
		}
	}
}
```