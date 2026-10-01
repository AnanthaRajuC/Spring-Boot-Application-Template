package io.github.anantharajuc.sbat.core_backend.security.user.model;

import jakarta.validation.constraints.NotBlank;
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
public class UserLogin
{
	@NotBlank
	String username;

	@ToString.Exclude
	@NotBlank
	String password;
}
