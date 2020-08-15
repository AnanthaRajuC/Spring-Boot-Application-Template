package io.github.anantharajuc.sbtest.backend.persistence.domain.backend;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "username", unique=true)
	@Size(max = 15)
	private String username;

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	private String password;

	@Email
	@Size(max=255, message="Must be a valid email id")
	@Column(name="email", unique=true, nullable = false)
	private String email;

	@Size(min=3, max=15, message=" First name must be between 3 and 15 characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

	@Size(min=1, max=15, message="Last name must be between 1 and 15 characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="phone", unique=true, nullable = false)
	private String phone;

    @Length(max = 500)
    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    @Column(name = "enabled")
    private boolean enabled;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();
}
