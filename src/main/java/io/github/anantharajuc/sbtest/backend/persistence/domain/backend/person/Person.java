package io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import io.github.anantharajuc.sbtest.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing a person.
 *
 * @author Anantha Raju C
 */
@Entity
@Table(name = "person")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Simple JavaBean domain object representing a person")
public class Person extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(min=3, max=15, message="Name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
	@ApiModelProperty(notes="Name of the person, it must be between 3 and 15 characters.", value = "${Person.name}")
    private String name;
	
	@NotBlank
	@Column(name = "username", unique=true)
	@Size(max = 15, message="username must not be empty.")
	@ApiModelProperty(notes="A unique identifier used by a person.", value = "${Person.username}")
	private String username;
	
	@Email
	@Size(max=255, message="Must be a valid email id")
	@Column(name="email_primary", unique=true, nullable = false)
	@ApiModelProperty(notes="Primary email of the person.", value = "${Person.emailPrimary}")
	private String emailPrimary;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name="email_secondary", nullable = true)
	@ApiModelProperty(notes="Secondary email of the person.", value = "${Person.emailSecondary}")
	private String emailSecondary;
	
	@Column(name="phone", unique=true, nullable = false)
	private Long phone;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable = false)
	@ApiModelProperty(notes="Gender the person.", value = "${Person.gender}")
	private GenderEnum gender;
	
	@Column(name="age", nullable = true)
	@ApiModelProperty(notes="Age of the person.", value = "${Person.age}")
	private int age;
	
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	@ApiModelProperty(notes="A secret word/phrase used to gain access to the application.", value = "${Person.password}")
	private String password;
	
	@Past
	@JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Kolkata")
	@Column(name="dob", nullable = true)
	@ApiModelProperty(notes="The month, day, and year a person was born. Pattern dd-MM-yyyy", value = "${Person.dob}")
	private LocalDate dob;
	
	@Column(name = "is_adult", nullable = false, length = 1)
	@ApiModelProperty(notes="A boolean to indicate if a person is after an age (such as 18/21) specified by law.", value = "${Person.isAdult}")
	private Boolean isAdult;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	@ApiModelProperty(notes="Address of the person.", value = "${Person.address}")
	private Address address;
}
