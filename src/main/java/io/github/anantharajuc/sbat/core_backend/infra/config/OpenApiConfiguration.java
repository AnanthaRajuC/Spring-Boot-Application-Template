package io.github.anantharajuc.sbat.core_backend.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * API documentation served by springdoc-openapi at /v3/api-docs and /swagger-ui.html.
 *
 * Declares the JWT bearer and HTTP Basic schemes so the Swagger UI "Authorize" button can send credentials.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Configuration
public class OpenApiConfiguration
{
	private static final String BEARER = "bearer-jwt";
	private static final String BASIC = "basic";

	@Bean
	public OpenAPI openAPI(@Value("${release.version}") String releaseVersion, @Value("${api.version}") String apiVersion)
	{
		return new OpenAPI()
				.info(new Info()
						.title("Spring Boot Application Template")
						.description("Spring Boot Template for Web Application. Get an access token from POST /api/v1/auth/login and use it with Authorize.")
						.version(releaseVersion + "_" + apiVersion)
						.contact(new Contact().name("Anantha Raju C").url("https://anantharajuc.github.io/").email("anantharajuc@gmail.com"))
						.license(new License().name("MIT").url("https://github.com/AnanthaRajuC/Spring-Boot-Application-Template/blob/master/LICENSE.md")))
				.components(new Components()
						.addSecuritySchemes(BEARER, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
						.addSecuritySchemes(BASIC, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.addSecurityItem(new SecurityRequirement().addList(BEARER))
				.addSecurityItem(new SecurityRequirement().addList(BASIC));
	}
}
