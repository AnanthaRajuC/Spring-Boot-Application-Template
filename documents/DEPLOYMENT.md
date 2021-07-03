## Deployment

### Deploying the .jar file to Heroku with test (H2DB) profile

* 	Download and install the Heroku CLI
*	log in to your Heroku account **`heroku login`**
* 	set git remote heroku to the heroku app url,  example **`heroku git:remote -a spring-boot-app-template`**
*	**Add** and **Commit** any pending changes to git
*	push the code to heroku to deploy the app, example **`git push heroku master`**

### Reference

[Deploying Spring Boot Web Application on Heroku](https://anantharajuc.github.io/Spring-Boot-Heroku/)