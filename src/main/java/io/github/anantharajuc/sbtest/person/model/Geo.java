package io.github.anantharajuc.sbtest.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Models a {@link Address Addressess'} geographic coordinate (for example, "-43.9589,-34.4628").
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Entity
@Table(name = "geo")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Models a Address' geographic coordinates. Example, \"-43.9589,-34.4628\"")
public class Geo extends AuditEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="lat")
	@Size(min=3, max=15, message="Must be a valid Geographic latitude value.")
	@ApiModelProperty(position=5, notes="Latitude", value="${Geo.lat}", example="-43.9589")
	private String lat;

	@Column(name="lng")
	@Size(min=3, max=15, message="Must be a valid Geographic longitude value")
	@ApiModelProperty(position=6, notes="Longitude", value="${Geo.lng}", example="-34.4628")
	private String lng;

	@JsonBackReference
	@OneToOne(mappedBy="geo")
	private Address address;
}
