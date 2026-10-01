## User Roles

Each user has one or more **roles**, and every role grants **permissions**. Endpoints check either or both, using Spring Security's `@PreAuthorize` and the URL rules in `ApplicationSecurityConfiguration`. Permissions follow a CRUD (create, read, update, delete) scheme per resource.

Roles and permissions are read from the database on every request, also for JWT authenticated requests, so a change takes effect immediately without issuing new tokens.

### Seeded users, roles and permissions

|     Username     | Password |     Role       |                      Permissions                                          |
|------------------|----------|----------------|---------------------------------------------------------------------------|
|`johndoe`         |`password`|`ROLE_PERSON`   |`PERSON_CREATE`, `PERSON_READ`, `PERSON_UPDATE`, `PERSON_DELETE`           |
|`janedoe`         |`password`|`ROLE_PERSON`   |`PERSON_CREATE`, `PERSON_READ`, `PERSON_UPDATE`, `PERSON_DELETE`           |
|`Admin1`          |`password`|`ROLE_ADMIN`    |`PERSON_CREATE`, `PERSON_READ`, `PERSON_UPDATE`, `PERSON_DELETE`, `RBAC_USER_CREATE`, `RBAC_USER_READ`|
|`Admin2`          |`password`|`ROLE_ADMIN`    |`PERSON_CREATE`, `PERSON_READ`, `PERSON_UPDATE`, `PERSON_DELETE`, `RBAC_USER_CREATE`, `RBAC_USER_READ`|
|`AdminTrainee1`   |`password`|`ROLE_ADMINTRAINEE`|`PERSON_READ`, `RBAC_USER_CREATE`                                       |
|`AdminTrainee2`   |`password`|`ROLE_ADMINTRAINEE`|`PERSON_READ`, `RBAC_USER_CREATE`                                       |

Self-registered users get `ROLE_PERSON`.

### Person URLs

For users with the **PERSON** or **ADMIN** role. Every request to `/api/v1/person/**` also needs an `X-api-key` header, see [API rate limiting](API.md#api-rate-limiting).

|                           URL                                |  Method |  Requires                         | Sample request body |
|--------------------------------------------------------------|---------|-----------------------------------|---------------------|
|`http://localhost:8080/api/v1/person`                         | GET     | `PERSON_READ`                     |                     |
|`http://localhost:8080/api/v1/person`                         | POST    | `PERSON_CREATE`                   | [JSON](#personcreate) |
|`http://localhost:8080/api/v1/person/id/{id}`                 | GET     | `PERSON_READ`                     |                     |
|`http://localhost:8080/api/v1/person/id/{id}`                 | PUT     | `PERSON_UPDATE`                   | [JSON](#personcreate) |
|`http://localhost:8080/api/v1/person/id/{id}`                 | DELETE  | `PERSON_DELETE`                   |                     |
|`http://localhost:8080/api/v1/person/username/{username}`     | GET     | Only the user's own `{username}`: persons this user created |  |

Responses use [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS) links (`self`, `persons`).

### Person Management URLs

For users with the **ADMIN** or **ADMINTRAINEE** role. Reading is open to both roles; changes need the matching permission, which only ADMIN has in the seed data. Every request to `/api/v1/management/person/**` also needs an `X-api-key` header, see [API rate limiting](API.md#api-rate-limiting).

|                          URL                                     |  Method |  Requires                              | Sample request body |
|------------------------------------------------------------------|---------|----------------------------------------|---------------------|
|`http://localhost:8080/api/v1/management/person`                  | GET     | ADMIN or ADMINTRAINEE                  |                     |
|`http://localhost:8080/api/v1/management/person/id/{id}`          | GET     | ADMIN or ADMINTRAINEE                  |                     |
|`http://localhost:8080/api/v1/management/person/pageable`         | GET     | ADMIN or ADMINTRAINEE                  | Query parameters `page`, `size`, `sort`, e.g. `?page=0&size=5&sort=createdDate,desc` |
|`http://localhost:8080/api/v1/management/person`                  | POST    | ADMIN or ADMINTRAINEE + `PERSON_CREATE`| [JSON](#personcreate) |
|`http://localhost:8080/api/v1/management/person/id/{id}`          | PUT     | ADMIN or ADMINTRAINEE + `PERSON_UPDATE`| [JSON](#personcreate) |
|`http://localhost:8080/api/v1/management/person/id/{id}`          | DELETE  | ADMIN or ADMINTRAINEE + `PERSON_DELETE`|                     |

### User URLs

|                          URL                                     |  Method |  Requires                              |
|------------------------------------------------------------------|---------|----------------------------------------|
|`http://localhost:8080/api/v1/user/username/{username}`           | GET     | Only the user's own `{username}`       |

### RBAC user management URLs

For users with the **ADMIN** role only. Responses never include password hashes.

|                          URL                                     |  Method |  Remarks                               | Sample request body |
|------------------------------------------------------------------|---------|----------------------------------------|---------------------|
|`http://localhost:8080/rbac/user`                                 | GET     | List users with their roles            |                     |
|`http://localhost:8080/rbac/user/{username}`                      | GET     | Get one user                           |                     |
|`http://localhost:8080/rbac/user`                                 | POST    | Create a user, returns `201 Created`   | [JSON](#rbacusercreate) |
|`http://localhost:8080/rbac/user/{username}`                      | DELETE  | Delete a user                          |                     |

When creating a user, roles are referenced by name. A role that doesn't exist yet is created: with the permissions listed in the request, or, when none are listed, with `<RESOURCE>_CREATE`, `_READ`, `_UPDATE` and `_DELETE` derived from the role name (`ROLE_COURSE` gets `COURSE_CREATE`, ...). The password is stored as a BCrypt hash.

### Sample request bodies

##### <a id="personcreate">Create or update a person -> /api/v1/person, /api/v1/management/person</a>
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

##### <a id="rbacusercreate">Create a user -> /rbac/user</a>
```json
{
	"username": "trainer1",
	"email": "trainer1@example.com",
	"password": "a-strong-password",
	"enabled": true,
	"accountNonExpired": true,
	"accountNonLocked": true,
	"credentialsNonExpired": true,
	"roles": [
		{ "name": "ROLE_PERSON" },
		{ "name": "ROLE_COURSE" }
	]
}
```
