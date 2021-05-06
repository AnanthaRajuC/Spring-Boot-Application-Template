package io.github.anantharajuc.sbat.example.crm.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Models a {@link Person Person's} address.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Entity
@Table(name = "example_address")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Models a Person's address.")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Address extends AuditEntity
{
	public Address(String street2, String suite2, String city2, String zipCode2, Geo geo2) 
	{
		
	}

	private static final long serialVersionUID = 1L;
	
	@Column(name="street", nullable=true)
	@Size(min=3, max=15, message="street must be between 3 and 15 characters.")
	@ApiModelProperty(position=5, notes="street.", value="${Address.street}", example="Jane Plains")
	String street;

	@Column(name="suite", nullable=true)
	@Size(min=3, max=15, message="suite must be between 3 and 15 characters.")
	@ApiModelProperty(position=6, notes="suite.", value="${Address.suite}", example="Suite 779")
	String suite;

	@Column(name="city", nullable=true)
	@Size(min=3, max=15, message="city must be between 3 and 15 characters.")
	@ApiModelProperty(position=7, notes="city.", value="${Address.city}", example="Wisokyburghh")
	String city;

	@Column(name="zipcode", nullable=true)
	@Size(min=3, max=15, message="zipcode must be between 3 and 15 characters.")
	@ApiModelProperty(position=8, notes="A postal code consisting of five or nine digits.", value="${Address.zipcode}", example="90565-7771")
	String zipcode;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="geo_id")
	Geo geo;

	@JsonBackReference
	@OneToOne(mappedBy="address")
	Person person;
}
