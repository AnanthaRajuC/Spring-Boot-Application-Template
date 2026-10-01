package io.github.anantharajuc.sbat.example.crm.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
 * Models a {@link Address Addressess'} geographic coordinate (for example, "-43.9589,-34.4628").
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Entity
@Table(name = "example_geo")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="Models a Address' geographic coordinates. Example, \"-43.9589,-34.4628\"")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Geo extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="lat")
	@Size(min=3, max=15, message="Must be a valid Geographic latitude value.")
	@Schema(description="Latitude", example="-43.9589")
	String lat;

	@Column(name="lng")
	@Size(min=3, max=15, message="Must be a valid Geographic longitude value")
	@Schema(description="Longitude", example="-34.4628")
	String lng;

	@JsonBackReference
	@OneToOne(mappedBy="geo")
	Address address;
}
