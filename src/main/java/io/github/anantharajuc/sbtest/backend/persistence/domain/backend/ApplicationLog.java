package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import io.github.anantharajuc.sbtest.enums.ApplicationLogEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name="event", nullable = false)
	private ApplicationLogEnum event;
	
	@Column(name="details", nullable = false)
	private String details;
}
