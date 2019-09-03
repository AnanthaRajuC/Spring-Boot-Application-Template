package com.arc.sbtest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="created_on", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
		
	@Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Date getCreatedAt() 
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) 
	{
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() 
	{
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) 
	{
		this.updatedAt = updatedAt;
	}
}
