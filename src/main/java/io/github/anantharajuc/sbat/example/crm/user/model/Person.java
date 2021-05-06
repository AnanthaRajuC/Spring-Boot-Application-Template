package io.github.anantharajuc.sbat.example.crm.user.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
 * Simple JavaBean domain object representing a person.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Entity
@Table(name="example_person") 
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Simple JavaBean domain object representing a person") 
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Person extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Size(min=3, max=15, message="Name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
	@ApiModelProperty(position=5, notes="Name of the person, it must be between 3 and 15 characters.", value="${Person.name}", required=true, example="John Doe")
    String name;
	
	@Column(name="username", unique=true)
	@Size(min=3, max = 15, message="username must not be empty.")
	@ApiModelProperty(position=6, notes="A unique identifier used by a person.", value="${Person.username}", required=true, example="user-1234")
	String username;
	
	@Column(name="phone", unique=true, nullable=false)
	@ApiModelProperty(position=7, notes="Phone number of the person.", value="${Person.emailSecondary}", required=true, example="9874563210")
	Long phone;
	
	@Size(max=255, message="Must be a valid email id")
	@Column(name="email_primary", unique=true, nullable = false)
	@ApiModelProperty(position=8, notes="Primary email of the person.", value="${Person.emailPrimary}", required=true, example="example@domain.com")
	String emailPrimary;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name="email_secondary", nullable = true)
	@ApiModelProperty(position=9, notes="Secondary email of the person.", value="${Person.emailSecondary}", example="example@domain.com")
	String emailSecondary;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable=false)
	@ApiModelProperty(position=10, notes="Gender the person.", value="${Person.gender}", allowableValues="MALE,FEMALE")
	GenderEnum gender;
	
	@Column(name="age", nullable=true)
	@ApiModelProperty(position=11, notes="Age of the person.", value="${Person.age}", example="55")
	int age;
	
	@Size(min=6, max = 15, message="password must not be empty.")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	@ApiModelProperty(position=12, notes="A secret word/phrase used to gain access to the application.", value="${Person.password}", example="$+r0nG10$$w0rD")
	String password;

	@JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Kolkata")
	@Column(name="dob", nullable = true)
	@ApiModelProperty(position=13, notes="The month, day, and year a person was born. Pattern dd-MM-yyyy", value="${Person.dob}", example="2006-12-25")
	LocalDate dob;
	
	@Column(name = "is_adult", nullable=false, length=1)
	@ApiModelProperty(position=14, notes="A boolean to indicate if a person is after an age (such as 18/21) specified by law.", value="${Person.isAdult}", allowableValues="true,false")
	Boolean isAdult;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="address_id")
	@ApiModelProperty(position=15, notes="Address of the person.", value="${Person.address}")
	Address address;
}
