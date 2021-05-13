package io.github.anantharajuc.sbat.core_backend.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description="Properties to be loaded when the application starts.") 
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ApplicationSetings extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="Application Key", value="${ApplicationSetings.appKey}", example="API_KEY")
	@Size(min=3, max=25, message="App Key must be between 3 and 25 characters.")
	@Column(name="app_key", nullable = false)
	String appKey;
	
	@ApiModelProperty(notes="Application Value", value="${ApplicationSetings.appValue}", example="1eGHY@T-dre565-56yrtr")
	@Size(min=3, max=100, message="App Value must be between 3 and 100 characters.")
	@Column(name="app_value", nullable = false)
	String appValue;
}
