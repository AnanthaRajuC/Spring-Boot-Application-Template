package io.github.anantharajuc.sbat.core_backend.security.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserSignup
{
	@NotBlank
	@Email
	String email;

	@NotBlank
	@Size(min=3, max=50)
	String username;

	@ToString.Exclude
	@NotBlank
	@Size(min=8, max=100)
	String password;
}
