## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

*	To activate the accounts of registered users, an email with activation link is sent to the email provided during the user signup stage. An **SMTP** is required for the same.

	[Mailtrap](https://mailtrap.io/) or any other service like **Gmail**, etc., can be used to create an SMTP.
	
	**MAIL_HOST**, **MAIL_PORT**, **MAIL_PROTOCOL** is configured in the **.env** file. Now configure **MAIL_USERNAME**, **MAIL_PASSWORD** as environment variable.
	
	[dotenv-java](https://github.com/cdimascio/dotenv-java) is used to Load environment variables from the **.env** file.

*	You need to have **MySQL** installed on your machine to run the application in **`dev`** profile. Using the `MySQL Workbench` or on any other MySQL client/console, create a database/schema named `sbat`. 

~~~sql
-- create schema
CREATE SCHEMA sbat;

-- use schema
USE sbat;

-- Create user 
create user 'sbat'@'localhost' identified by 'sbat';

-- Grant privileges to user
grant all privileges on *.* to 'sbat'@'localhost' with grant option;
~~~

We need to specify our Environment variables for the application. Open up the **`.env`** file and update your **DB_DATABASE**, **DB_USERNAME**, **DB_CONNECTION**, **DB_HOST**, **DB_PORT** and **DB_PASSWORD** in the appropriate fields.

If you do not wish to use environment variables. After creating the database/schema, you need to add your **MySQL** `username` and `password` in the `application-dev.properties` file on `src/main/resource`. The lines that must be modified are as follows:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sbat?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=sbat
spring.datasource.password=sbat
```

*	A Java Keystore File is required to generate JSON Web Token.

```shell
keytool -genkey -alias redditclone -keyalg RSA -keystore redditclone.jks -keysize 2048
```

<img src="documents\images\settings\reddit-clone-jks-generation.PNG"/>

### EER Diagram

* 	Refer to [ARCHITECTURE.md](documents/ARCHITECTURE.md) for details.
