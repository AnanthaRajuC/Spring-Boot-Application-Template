package io.github.anantharajuc.sbtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name="name", nullable = false)
    private String name;
	
	@Column(name="gender", nullable = false)
	private String gender;
	
	@Column(name="age", nullable = false)
	private int age;
}
