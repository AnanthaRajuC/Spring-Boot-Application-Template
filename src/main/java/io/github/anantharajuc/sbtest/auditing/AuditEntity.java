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

import io.github.anantharajuc.sbtest.backend.persistence.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object with id, created on, created by, updated on, updated by properties. Used as a base class for objects
 * needing this property.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@MappedSuperclass
@Getter
@Setter
@ApiModel(description="Simple JavaBean domain object with id, created on, created by, updated on, updated by properties.") 
public class AuditEntity extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="created_date", nullable=true, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @ApiModelProperty(position=1, notes="Field representing the date the entity containing the field was created.", value="${AuditEntity.createdDate}")
    private Date createdDate;
	
	@CreatedBy
    @Column(name="created_by", nullable=false, updatable=false)
	@ApiModelProperty(position=2, notes="Field representing the principal that created the entity containing the field.", value="${AuditEntity.createdBy}")
    private String createdBy;
		
	@Column(name="last_modified_date", nullable=true, updatable=true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @ApiModelProperty(position=3, notes="Field representing the date the entity containing the field was recently modified.", value="${AuditEntity.lastModifiedDate}")
    private Date lastModifiedDate;
	
	@LastModifiedBy
    @Column(name="last_modified_by", nullable=false, updatable=true)
	@ApiModelProperty(position=4, notes="Field representing the principal that recently modified the entity containing the field.", value="${AuditEntity.lastModifiedBy}")
    private String lastModifiedBy;
}
