package io.github.anantharajuc.sbat.example.crm.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
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
@Schema(description="Simple JavaBean domain object representing a person") 
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Person extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Size(min=3, max=15, message="Name must be between 3 and 15 characters.")
	@Column(name="name", nullable = false)
	@Schema(description="Name of the person, it must be between 3 and 15 characters.", example="John Doe", requiredMode=Schema.RequiredMode.REQUIRED)
    String name;
	
	@Column(name="username", unique=true)
	@Size(min=3, max = 15, message="username must not be empty.")
	@Schema(description="A unique identifier used by a person.", example="user-1234", requiredMode=Schema.RequiredMode.REQUIRED)
	String username;
	
	@Column(name="phone", unique=true, nullable=false)
	@Schema(description="Phone number of the person.", example="9874563210", requiredMode=Schema.RequiredMode.REQUIRED)
	Long phone;
	
	@Size(max=255, message="Must be a valid email id")
	@Column(name="email_primary", unique=true, nullable = false)
	@Schema(description="Primary email of the person.", example="example@domain.com", requiredMode=Schema.RequiredMode.REQUIRED)
	String emailPrimary;
	
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name="email_secondary", nullable = true)
	@Schema(description="Secondary email of the person.", example="example@domain.com")
	String emailSecondary;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender", nullable=false)
	@Schema(description="Gender the person.", allowableValues={"MALE", "FEMALE"})
	GenderEnum gender;
	
	@Column(name="age", nullable=true)
	@Schema(description="Age of the person.", example="55")
	int age;
	
	@Size(min=6, max = 15, message="password must not be empty.")
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	@Schema(description="A secret word/phrase used to gain access to the application.", example="$+r0nG10$$w0rD")
	String password;

	@JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Kolkata")
	@Column(name="dob", nullable = true)
	@Schema(description="The month, day, and year a person was born. Pattern dd-MM-yyyy", example="2006-12-25")
	LocalDate dob;
	
	@Column(name = "is_adult", nullable=false, length=1)
	@Schema(description="A boolean to indicate if a person is after an age (such as 18/21) specified by law.", allowableValues={"true", "false"})
	Boolean isAdult;
	
	@JsonManagedReference
	@OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="address_id")
	@Schema(description="Address of the person.")
	Address address;
}
