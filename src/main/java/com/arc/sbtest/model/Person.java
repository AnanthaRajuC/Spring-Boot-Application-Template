package com.arc.sbtest.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
public class Person extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
    private String name;	
	private String gender;
	private int age;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getGender() 
	{
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	
	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}
}
