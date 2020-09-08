package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "application_settings")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationSetings extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="appKey", nullable = false)
	String appKey;
	
	@Column(name="appValue", nullable = false)
	String appValue;
}
