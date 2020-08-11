package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "geo")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Geo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "lat")
	private String lat;

	@Column(name = "lng")
	private String lng;

	@JsonBackReference
	@OneToOne(mappedBy = "geo")
	private Address address;
}
