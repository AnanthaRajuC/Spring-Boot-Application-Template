package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "built_with")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuiltWith extends AuditEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="version", nullable = true)
	private String version;
	
	@Column(name="description", nullable = true)
	private String description;
	
	@Column(name="link", nullable = true)
	private String link;
}
