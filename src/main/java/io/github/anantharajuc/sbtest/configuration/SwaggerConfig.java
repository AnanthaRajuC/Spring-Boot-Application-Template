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
	public static final String GROUP_NAME = "Spring Boot Application Template";
	
	public static final String TITLE = "Spring Boot Application Template";
	public static final String DESCRIPTION = "Spring Boot Template for Web Application";
	public static final String TERMS_OF_SERVICE_URL = "https://github.com/Spring-Boot-Framework/Spring-Boot-Application-Template";
	public static final String LICENSE = "https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template";
	public static final String LICENSE_URL = "https://app.fossa.io/projects/git%2Bgithub.com%2FSpring-Boot-Framework%2FSpring-Boot-Application-Template";
	public static final String VERSION = "1.0";
	public static final Contact CONTACT = new Contact("Anantha Raju C", "https://anantharajuc.github.io/", "anantharajuc@gmail.com");
	
	@Bean
	public Docket postsApi() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
					.groupName(GROUP_NAME)
					.apiInfo(apiInfo())
					.select()
					.paths(postPaths())
					.build();
	}

	private ApiInfo apiInfo() 
	{
		return new ApiInfoBuilder()
						.title(TITLE)
						.description(DESCRIPTION)
						.termsOfServiceUrl(TERMS_OF_SERVICE_URL)
						.license(LICENSE)
						.licenseUrl(LICENSE_URL)
						.version(VERSION)
						.contact(CONTACT)
						.build();
	}
	
	private Predicate<String> postPaths() 
	{
		return or(regex("/api.*"), 
				  regex("/management/api.*"));
	}
} 
