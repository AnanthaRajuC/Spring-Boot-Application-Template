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

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object with id, created on, updated on properties. Used as a base class for objects
 * needing this property.
 *
 * @author Anantha Raju C
 */
@MappedSuperclass
@Getter
@Setter
public class AuditEntity extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="created_date", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @ApiModelProperty(notes="Field representing the date the entity containing the field was created.")
    private Date createdDate;
	
	@CreatedBy
    @Column(name="created_by", nullable = false, updatable = false)
	@ApiModelProperty(notes="Field representing the principal that created the entity containing the field.")
    private String createdBy;
		
	@Column(name="last_modified_date",nullable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @ApiModelProperty(notes="Field representing the date the entity containing the field was recently modified.")
    private Date lastModifiedDate;
	
	@LastModifiedBy
    @Column(name="last_modified_by",nullable = false, updatable = true)
	@ApiModelProperty(notes="Field representing the principal that recently modified the entity containing the field.")
    private String lastModifiedBy;
}
