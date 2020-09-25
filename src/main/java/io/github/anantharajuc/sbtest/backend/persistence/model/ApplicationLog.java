package io.github.anantharajuc.sbtest.backend.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import io.github.anantharajuc.sbtest.backend.persistence.model.enumeration.ApplicationLogEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Application Log - Event
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Entity
@Table(name = "application_log")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLog extends AuditEntity
{
	//Default Serial Version ID
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes="Application Event", value="${ApplicationLog.event}", allowableValues="APPLICATION_START")
	@Enumerated(EnumType.STRING)
	@Column(name="event", nullable = false)
	private ApplicationLogEnum event;
	
	@ApiModelProperty(notes="Application Event Details", value="${ApplicationLog.details}")
	@Column(name="details", nullable = false)
	private String details;
}
