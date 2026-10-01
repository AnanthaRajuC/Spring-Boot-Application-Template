package io.github.anantharajuc.sbat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.jayway.jsonpath.JsonPath;

import io.github.anantharajuc.sbat.core_backend.security.jwt.model.VerificationToken;
import io.github.anantharajuc.sbat.core_backend.security.jwt.repository.VerificationTokenRepository;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;

/**
 * End to end tests against the default (H2) profile, covering the web UI security, the JWT based REST API
 * authentication and the account lifecycle.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TestSBtemplateApplication
{
	private static final String PASSWORD = "password";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@MockitoBean
	private JavaMailSender javaMailSender;

	// ---------------------------------------------------------------------------------------------------- Web UI

	@Test
	void publicPagesAreAccessibleAnonymously() throws Exception
	{
		mockMvc.perform(get("/sbat/index")).andExpect(status().isOk());
		mockMvc.perform(get("/sbat/login")).andExpect(status().isOk());
		mockMvc.perform(get("/webjars/bootstrap/css/bootstrap.min.css")).andExpect(status().isOk());
		mockMvc.perform(get("/")).andExpect(redirectedUrl("/sbat/index"));
	}

	@Test
	void protectedPageRedirectsAnonymousUserToLoginPage() throws Exception
	{
		mockMvc.perform(get("/sbat/about"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/sbat/login"));
	}

	@Test
	void formLoginWithValidCredentialsAuthenticates() throws Exception
	{
		mockMvc.perform(formLogin("/sbat/login").userParameter("sbat-username").passwordParam("sbat-password").user("johndoe").password(PASSWORD))
			.andExpect(authenticated().withUsername("johndoe"))
			.andExpect(redirectedUrl("/sbat/index"));
	}

	@Test
	void formLoginWithWrongPasswordFails() throws Exception
	{
		mockMvc.perform(post("/sbat/login").with(csrf()).with(fromIp("10.0.0.1")).param("sbat-username", "johndoe").param("sbat-password", "wrong"))
			.andExpect(unauthenticated())
			.andExpect(redirectedUrl("/sbat/login?error"));
	}

	@Test
	void formLoginWithoutCsrfTokenIsRejected() throws Exception
	{
		mockMvc.perform(post("/sbat/login").param("sbat-username", "johndoe").param("sbat-password", PASSWORD))
			.andExpect(status().isForbidden())
			.andExpect(unauthenticated());
	}

	@Test
	void closingTheApplicationRequiresAdminRole() throws Exception
	{
		// Only the access check is exercised: a non-admin is denied before the controller runs.
		mockMvc.perform(post("/sbat/close").with(csrf()).with(user("johndoe").roles("PERSON")))
			.andExpect(status().isForbidden());
	}

	// ---------------------------------------------------------------------------------------------------- REST API

	@Test
	void apiRejectsMissingAndInvalidTokens() throws Exception
	{
		mockMvc.perform(get("/rbac/user")).andExpect(status().isUnauthorized());
		mockMvc.perform(get("/rbac/user").header(HttpHeaders.AUTHORIZATION, "Bearer not-a-jwt")).andExpect(status().isUnauthorized());
	}

	@Test
	void adminTokenGrantsAccessToAdminEndpoints() throws Exception
	{
		String token = JsonPath.read(login("Admin1", PASSWORD), "$.authenticationToken");

		mockMvc.perform(get("/rbac/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].username").exists())
			.andExpect(jsonPath("$[0].password").doesNotExist());

		mockMvc.perform(get("/actuator/prometheus").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
			.andExpect(status().isOk());
	}

	@Test
	void nonAdminTokenIsForbiddenOnAdminEndpoints() throws Exception
	{
		String token = JsonPath.read(login("johndoe", PASSWORD), "$.authenticationToken");

		mockMvc.perform(get("/rbac/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)).andExpect(status().isForbidden());
		mockMvc.perform(get("/actuator/metrics").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)).andExpect(status().isForbidden());

		mockMvc.perform(get("/api/v1/user/username/johndoe").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username").value("johndoe"));
	}

	@Test
	void healthEndpointIsPublic() throws Exception
	{
		mockMvc.perform(get("/actuator/health"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value("UP"));
	}

	@Test
	void apiLoginWithWrongPasswordIsUnauthorized() throws Exception
	{
		mockMvc.perform(post("/api/v1/auth/login").with(fromIp("10.0.0.2")).contentType(MediaType.APPLICATION_JSON).content(credentials("johndoe", "wrong")))
			.andExpect(status().isUnauthorized());
	}

	@Test
	void refreshTokenCannotBeUsedForAnotherUser() throws Exception
	{
		String refreshToken = JsonPath.read(login("janedoe", PASSWORD), "$.refreshToken");

		mockMvc.perform(post("/api/v1/auth/refresh/token").contentType(MediaType.APPLICATION_JSON)
				.content("{\"token\":\"" + refreshToken + "\",\"username\":\"Admin1\"}"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	void refreshTokenIsRotatedAndSingleUse() throws Exception
	{
		String refreshToken = JsonPath.read(login("janedoe", PASSWORD), "$.refreshToken");

		String refreshed = mockMvc.perform(post("/api/v1/auth/refresh/token").contentType(MediaType.APPLICATION_JSON)
				.content("{\"token\":\"" + refreshToken + "\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username").value("janedoe"))
			.andReturn().getResponse().getContentAsString();

		assertThat((String) JsonPath.read(refreshed, "$.refreshToken")).isNotEqualTo(refreshToken);

		mockMvc.perform(post("/api/v1/auth/refresh/token").contentType(MediaType.APPLICATION_JSON)
				.content("{\"token\":\"" + refreshToken + "\"}"))
			.andExpect(status().isUnauthorized());
	}

	@Test
	void clientIsBlockedAfterTooManyFailedLogins() throws Exception
	{
		RequestPostProcessor attacker = fromIp("10.0.0.66");

		for (int i = 0; i < 5; i++)
		{
			mockMvc.perform(post("/api/v1/auth/login").with(attacker).contentType(MediaType.APPLICATION_JSON).content(credentials("Admin1", "guess" + i)))
				.andExpect(status().isUnauthorized());
		}

		// Even the right password is refused while blocked, other clients are not affected.
		mockMvc.perform(post("/api/v1/auth/login").with(attacker).contentType(MediaType.APPLICATION_JSON).content(credentials("Admin1", PASSWORD)))
			.andExpect(status().isUnauthorized());
		mockMvc.perform(post("/api/v1/auth/login").with(fromIp("10.0.0.67")).contentType(MediaType.APPLICATION_JSON).content(credentials("Admin1", PASSWORD)))
			.andExpect(status().isOk());
	}

	// ---------------------------------------------------------------------------------------------------- Account lifecycle

	@Test
	void signedUpAccountCanOnlyLoginAfterVerification() throws Exception
	{
		String username = "user-" + UUID.randomUUID().toString().substring(0, 8);

		signup(username);

		mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(credentials(username, "s3cret-password")))
			.andExpect(status().isUnauthorized());

		VerificationToken verificationToken = verificationTokenFor(username);

		mockMvc.perform(get("/api/v1/auth/verification/" + verificationToken.getToken()))
			.andExpect(status().isOk());

		mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(credentials(username, "s3cret-password")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username").value(username));
	}

	@Test
	void signupRejectsInvalidAndDuplicateInput() throws Exception
	{
		mockMvc.perform(post("/api/v1/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"x\",\"email\":\"not-an-email\",\"password\":\"short\"}"))
			.andExpect(status().isBadRequest());

		mockMvc.perform(post("/api/v1/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"johndoe\",\"email\":\"someone@example.com\",\"password\":\"long-enough-password\"}"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void expiredVerificationRemovesOnlyTheUnverifiedAccount() throws Exception
	{
		String username = "user-" + UUID.randomUUID().toString().substring(0, 8);

		signup(username);

		VerificationToken verificationToken = verificationTokenFor(username);
		verificationToken.setExpiryDate(Instant.now().minusSeconds(60));
		verificationTokenRepository.save(verificationToken);

		long usersBefore = userRepository.count();

		mockMvc.perform(get("/api/v1/auth/verification/" + verificationToken.getToken()))
			.andExpect(status().isOk());

		assertThat(userRepository.findByUsername(username)).isEmpty();
		assertThat(userRepository.count()).isEqualTo(usersBefore - 1);
		assertThat(userRepository.findByUsername("johndoe")).isPresent();
	}

	// ---------------------------------------------------------------------------------------------------- Helpers

	private String login(String username, String password) throws Exception
	{
		return mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(credentials(username, password)))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
	}

	private void signup(String username) throws Exception
	{
		mockMvc.perform(post("/api/v1/auth/signup").contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\":\"" + username + "\",\"email\":\"" + username + "@example.com\",\"password\":\"s3cret-password\"}"))
			.andExpect(status().isOk());
	}

	private VerificationToken verificationTokenFor(String username)
	{
		return verificationTokenRepository.findByUserUsername(username).orElseThrow();
	}

	private static String credentials(String username, String password)
	{
		return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
	}

	private static RequestPostProcessor fromIp(String ip)
	{
		return request -> {
			request.setRemoteAddr(ip);
			return request;
		};
	}
}
