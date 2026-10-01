package io.github.anantharajuc.sbat.core_backend.security.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbat.core_backend.persistence.auditing.AuditEntity;
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

	@ToString.Exclude
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@Column(name="password")
	@Schema(description="A secret word/phrase used to gain access to the application.", example="$+r0nG10$$w0rD")
	String password; 
	
	@Column(name="username", unique=true)
	@NotEmpty(message = "Username is required")
	@Size(max = 255, message="username must be at most 255 characters.")
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
	
	// No cascading: roles are shared between users and must never be removed along with one of them.
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sbat_auth_role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
