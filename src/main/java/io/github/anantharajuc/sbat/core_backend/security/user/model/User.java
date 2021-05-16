package io.github.anantharajuc.sbat.core_backend.security.user.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Spring Security - User
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@ToString
public class User extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="password")
	@ApiModelProperty(notes="A secret word/phrase used to gain access to the application.", value = "${Person.password}", example="$+r0nG10$$w0rD")
	String password; 
	
	@Column(name="username", unique=true)
	@Size(max = 255, message="username must not be empty.")
	String username;	
	
	@Email
    @NotEmpty(message = "Email is required")
    @Column(name="email", unique=true, nullable = false)
	String email;
	
	@Column(name="isAccountNonExpired")
	boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked")
	boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired")
	boolean isCredentialsNonExpired;
	
	@Column(name="isEnabled")
	boolean isEnabled;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "sbat_auth_role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
