package io.github.anantharajuc.sbtest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	@Bean
	public Docket postsApi() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("public-api")
				.apiInfo(apiInfo())
				.select().paths(postPaths()).build();
	}
	
	private Predicate<String> postPaths() 
	{
		return or(regex("/api.*"), 
				  regex("/management/api.*"));
	}
	
	private ApiInfo apiInfo() 
	{
		return new ApiInfoBuilder()
						.title("Spring Boot Application Template")
						.description("Spring Boot Template for Web Application")
						.termsOfServiceUrl("https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template")
						.license("https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template")
						.licenseUrl("https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template")
						.version("1.0")
						.contact(new Contact("Anantha Raju C", "https://anantharajuc.github.io/", "anantharajuc@gmail.com"))
						.build();
	}
} 
