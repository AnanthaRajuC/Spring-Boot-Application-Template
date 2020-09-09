package io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Models a {@link Person Person's} address.
 *
 * @author Anantha Raju C
 */
@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "street")
	private String street;

	@Column(name = "suite")
	private String suite;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private String zipcode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_id")
	private Geo geo;

	@JsonBackReference
	@OneToOne(mappedBy = "address")
	private Person person;
}
