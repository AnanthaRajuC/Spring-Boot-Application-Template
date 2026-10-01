package io.github.anantharajuc.sbat.core_backend.security.jwt.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Refresh token sent by API clients. The username is optional and only checked for consistency: the account a
 * refresh token belongs to is always taken from the stored token.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class RefreshTokenDTO
{
	@NotBlank
	String token;

	String username;
}
