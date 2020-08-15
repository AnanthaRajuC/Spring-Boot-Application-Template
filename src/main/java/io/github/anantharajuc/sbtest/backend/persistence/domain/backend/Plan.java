package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plan")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plan extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "name", unique=true)
	@Size(max = 15)
	private String name;
}
