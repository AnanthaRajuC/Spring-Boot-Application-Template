package io.github.anantharajuc.sbtest.auditing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object with id, created on, updated on properties. Used as a base class for objects
 * needing this property.
 *
 * @author Anantha Raju C
 */
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdDate","createdBy","lastModifiedDate","lastModifiedBy"}, allowGetters = false)
@Getter
@Setter
public class AuditEntity extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="created_date", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;
	
	@CreatedBy
    @Column(name="created_by", nullable = false, updatable = false)
    private String createdBy;
		
	@Column(name="last_modified_date",nullable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastModifiedDate;
	
	@LastModifiedBy
    @Column(name="last_modified_by",nullable = false, updatable = true)
    private String lastModifiedBy;
}
