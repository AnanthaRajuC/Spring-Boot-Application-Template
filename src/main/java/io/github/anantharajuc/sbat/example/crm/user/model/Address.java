package io.github.anantharajuc.sbat.example.crm.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
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
@Schema(description="Models a Person's address.")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Address extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="street", nullable=true)
	@Size(min=3, max=15, message="street must be between 3 and 15 characters.")
	@Schema(description="street.", example="Jane Plains")
	String street;

	@Column(name="suite", nullable=true)
	@Size(min=3, max=15, message="suite must be between 3 and 15 characters.")
	@Schema(description="suite.", example="Suite 779")
	String suite;

	@Column(name="city", nullable=true)
	@Size(min=3, max=15, message="city must be between 3 and 15 characters.")
	@Schema(description="city.", example="Wisokyburghh")
	String city;

	@Column(name="zipcode", nullable=true)
	@Size(min=3, max=15, message="zipcode must be between 3 and 15 characters.")
	@Schema(description="A postal code consisting of five or nine digits.", example="90565-7771")
	String zipcode;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="geo_id")
	Geo geo;

	@JsonBackReference
	@OneToOne(mappedBy="address")
	Person person;
}
