package io.github.anantharajuc.sbat.core_backend.persistence.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Simple JavaBean domain object representing built_with.
 *
 * @author Anantha Raju C
 */
@Entity
@Table(name="sbat_built_with")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Models a builit_with table.")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class BuiltWith extends AuditEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@Size(min=3, max=15, message="name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
	@Schema(description="name.", example="Apache Maven", requiredMode=Schema.RequiredMode.REQUIRED)
	String name;
	
	@Column(name="version", nullable = true)
	@Schema(description="version.", example="3.5.2")
	String version;
	
	@Column(name="description", nullable = true)
	@Schema(description="description.", example="Dependency Management")
	String description;
	
	@Column(name="link", nullable = true)
	@Schema(description="link.", example="https://maven.apache.org/")
	String link;
}
