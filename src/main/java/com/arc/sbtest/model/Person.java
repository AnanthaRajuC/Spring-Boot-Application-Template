package com.arc.sbtest.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Person extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
    private String name;	
	private String gender;
	private int age;
}
