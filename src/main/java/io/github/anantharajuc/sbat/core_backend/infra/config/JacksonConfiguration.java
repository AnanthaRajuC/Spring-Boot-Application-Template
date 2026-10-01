package io.github.anantharajuc.sbat.core_backend.infra.config;

import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.cfg.ConstructorDetector;

/**
 * JSON mapping settings.
 */
@Configuration
public class JacksonConfiguration
{
	/**
	 * Jackson 3 picks an implicit all-arguments constructor (such as Lombok's {@code @AllArgsConstructor}) even when a
	 * no-arguments constructor exists. Request bodies that leave out a primitive field then fail with "Cannot map
	 * null into type boolean". Prefer the no-arguments constructor and setters, as Jackson 2 did; records and
	 * {@code @JsonCreator} constructors are unaffected.
	 */
	@Bean
	public JsonMapperBuilderCustomizer preferDefaultConstructorCustomizer()
	{
		return builder -> builder.constructorDetector(ConstructorDetector.DEFAULT.withAllowImplicitWithDefaultConstructor(false));
	}
}
