package io.github.anantharajuc.sbat.core_backend.persistence.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Properties to be loaded when the application starts.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Entity
@Table(name = "sbat_settings")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description="Properties to be loaded when the application starts.") 
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ApplicationSetings extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Schema(description="Application Key", example="API_KEY")
	@Size(min=3, max=25, message="App Key must be between 3 and 25 characters.")
	@Column(name="app_key", nullable = false)
	String appKey;
	
	@Schema(description="Application Value", example="1eGHY@T-dre565-56yrtr")
	@Size(min=3, max=100, message="App Value must be between 3 and 100 characters.")
	@Column(name="app_value", nullable = false)
	String appValue;
}
