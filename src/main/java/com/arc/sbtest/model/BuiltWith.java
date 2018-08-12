package com.arc.sbtest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "built_with")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class BuiltWith implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String version;
	private String description;
	private String link;
	
	@Column(name="created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getVersion() 
	{
		return version;
	}

	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getLink() 
	{
		return link;
	}

	public void setLink(String link) 
	{
		this.link = link;
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
	
	public BuiltWith(@NotBlank String name, String version, String description, String link, Date createdAt, Date updatedAt) 
	{
		this.name = name;
		this.version = version;
		this.description = description;
		this.link = link;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public BuiltWith() 
	{
		
	}

	@Override
	public String toString() 
	{
		return "BuiltWith [id=" + id + ", "
						+ "name=" + name + ", "
						+ "version=" + version + ", "
						+ "description=" + description + ", "
						+ "link=" + link + ", "
						+ "createdAt=" + createdAt + ", "
						+ "updatedAt=" + updatedAt + "]";
	}
}
