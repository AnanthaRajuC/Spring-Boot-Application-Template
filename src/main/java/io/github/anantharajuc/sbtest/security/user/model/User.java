package io.github.anantharajuc.sbtest.security.user.model;

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
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.sbtest.auditing.AuditEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class User extends AuditEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="password")
	@ApiModelProperty(notes="A secret word/phrase used to gain access to the application.", value = "${Person.password}", example="$+r0nG10$$w0rD")
	private String password; 
	
	@Column(name="username", unique=true)
	@Size(max = 255, message="username must not be empty.")
	private String username;	
	
	@Column(name="isAccountNonExpired")
	private boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked")
	private boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired")
	private boolean isCredentialsNonExpired;
	
	@Column(name="isEnabled")
	private boolean isEnabled;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
