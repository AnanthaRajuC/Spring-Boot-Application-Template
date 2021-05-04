package io.github.anantharajuc.sbat.core_backend.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description="Models a builit_with table.")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class BuiltWith extends AuditEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@Size(min=3, max=15, message="name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
	@ApiModelProperty(position=5, notes="name.", value="${BuiltWith.name}", required=true, example="Apache Maven")
	String name;
	
	@Column(name="version", nullable = true)
	@ApiModelProperty(position=6, notes="version.", value="${BuiltWith.version}", example="3.5.2")
	String version;
	
	@Column(name="description", nullable = true)
	@ApiModelProperty(position=7, notes="description.", value="${BuiltWith.description}", example="Dependency Management")
	String description;
	
	@Column(name="link", nullable = true)
	@ApiModelProperty(position=8, notes="link.", value="${BuiltWith.link}", example="https://maven.apache.org/")
	String link;
}
