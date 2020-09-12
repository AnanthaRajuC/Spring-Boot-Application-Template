package io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
 * @author Anantha Raju C
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
	
	@Column(name = "lat")
	@ApiModelProperty(notes="Latitude", value="${Geo.lat}")
	private String lat;

	@Column(name = "lng")
	@ApiModelProperty(notes="Longitude", value="${Geo.lng}")
	private String lng;

	@JsonBackReference
	@OneToOne(mappedBy = "geo")
	private Address address;
}
