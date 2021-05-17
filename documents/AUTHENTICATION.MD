## Authentication

Spring Boot Application Template is built on top of Spring Security and includes a few additional features such as email verification, username login.

* **Registration**  
* **Email Verification** 
* **Login**  
 

### Registration

By default users will need to register via. a valid email id in order to create an account.

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/signup`                           | POST   |                                               | [JSON](#signup)           |

#### Sample Valid JSON Request Bodys

##### <a id="signup">Signup -> /api/auth/signup</a>
```json
{
    "username":"janedoe",
    "email":"domain@example.com",
    "password":"password"
}
```

### Email Verification

The simplest way to test emails in development mode is to use Mailtrap. You can sign up for a free account and then enter your mailtrap credentials in your **`.env`** file

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/verification/{verification-token}`| GET    |                                               |                           |

### Login

If you have just installed Spring Boot Application Template you can login with the following default accounts.

|     Username     | Password |     Role     |
|------------------|----------|--------------|
|`johndoe`         |`password`|`PERSON`      |
|`janedoe`         |`password`|`PERSON`      |
|`Admin1`          |`password`|`ADMIN`       |
|`Admin2`          |`password`|`ADMIN`       |
|`AdminTrainee1`   |`password`|`ADMINTRAINEE`|
|`AdminTrainee2`   |`password`|`ADMINTRAINEE`|

|                                          URL                        | Method |                    Remarks                    | Sample Valid Request Body |
|---------------------------------------------------------------------|--------|-----------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/auth/login`                            | POST   |Bearer Token, Refresh Token is generated       | [JSON](#login)            |
|`http://localhost:8080/api/v1/auth/refresh/token`                    | POST   |Refresh Token from login should be passed      | [JSON](#refresh-token)    |

#### Sample Valid JSON Request Bodys

##### <a id="login">Login -> /api/auth/login</a>
```json
{
    "username":"johndoe",
    "password":"password"
}
```

##### <a id="refresh-token">Refresh Token -> /api/auth/refresh/token</a>
```json
{
    "token":"1178cd43-21d2-45b4-8b5f-c79aa1d5b76e",
    "username":"johndoe"
}
```







