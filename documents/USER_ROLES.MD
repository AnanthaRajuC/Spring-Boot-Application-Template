## User Roles

Each user in the app will have a primary role and every role has permissions to allow or deny access to content. In this section we will talk about User Roles and how to utilize roles in the application. The application uses CRUD (create, read, update, and delete) system.

|     Username     | Password |     Role     |                      Permission                       |         Resource          |
|------------------|----------|--------------|-------------------------------------------------------|---------------------------|
|`johndoe`         |`password`|`PERSON`      |                                                       |`/api/v1/person`           |
|`janedoe`         |`password`|`PERSON`      |                                                       |`/api/v1/person`           |
|`Admin1`          |`password`|`ADMIN`       |`PERSON_CREATE,PERSON_READ,PERSON_UPDATE,PERSON_DELETE`|`/management/api/v1/person`|
|`Admin2`          |`password`|`ADMIN`       |`PERSON_CREATE,PERSON_READ,PERSON_UPDATE,PERSON_DELETE`|`/management/api/v1/person`|
|`AdminTrainee1`   |`password`|`ADMINTRAINEE`|`PERSON_READ`                                          |`/management/api/v1/person`|
|`AdminTrainee2`   |`password`|`ADMINTRAINEE`|`PERSON_READ`                                          |`/management/api/v1/person`|

### Person URLs

#### Accessible to user's with **PERSON** role only

|                           URL                            |  Method |                                         Remarks                                     | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/api/v1/person`                     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/api/v1/person`                     | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/api/v1/person/{id}`                | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/api/v1/person/{id}`                | PUT     | Update a person                                                                     |    [JSON](#personcreate)  |
|`http://localhost:8080/api/v1/person/{id}`                | DELETE  | Delete a person                                                                     |                           |

### Person Management URLs

#### Role and Permission based secure access to user's with **ADMIN** role. 

|                          URL                             |  Method |                                       Remarks                                       | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/management/api/v1/person`          | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person`          | POST    | Add a person                                                                        |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |
|`http://localhost:8080/management/api/v1/person/pageable` | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|   Pageable API Endpoint   |
|`http://localhost:8080/management/api/v1/person/{id}`     | PUT     | Update a person                                                                     |   [JSON](#personcreate)   |
|`http://localhost:8080/management/api/v1/person/{id}`     | DELETE  | Delete a person                                                                     |                           |

#### Role and Permission based secure access to user's with **ADMINTRAINEE** role. 

|                          URL                             |  Method |                                       Remarks                                       | Sample Valid Request Body |
|----------------------------------------------------------|---------|-------------------------------------------------------------------------------------|---------------------------|
|`http://localhost:8080/management/api/v1/person`          | GET     | Header `Accept:application/json` or `Accept:application/xml` for content negotiation|                           |

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