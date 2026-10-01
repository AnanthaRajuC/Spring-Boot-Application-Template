package io.github.anantharajuc.sbat.core_backend.security.user.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.core_backend.infra.exception.OtherExceptions;
import io.github.anantharajuc.sbat.core_backend.infra.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Permission;
import io.github.anantharajuc.sbat.core_backend.security.user.model.Role;
import io.github.anantharajuc.sbat.core_backend.security.user.model.User;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.PermissionRepository;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.RoleRepository;
import io.github.anantharajuc.sbat.core_backend.security.user.repository.UserRepository;
import io.github.anantharajuc.sbat.core_backend.user.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Spring Security - User Service Implementation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Service
@AllArgsConstructor
public class RBACuserServiceImpl implements RBACuserService
{
	private static final String[] DEFAULT_PERMISSIONS = {"_CREATE", "_READ", "_UPDATE", "_DELETE"};

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public UserDTO createUser(User user)
	{
		if (user.getPassword() == null || user.getPassword().isBlank())
		{
			throw new OtherExceptions("A password is required");
		}

		if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
		{
			throw new OtherExceptions("Username or email is already registered.");
		}

		List<Role> roles = new ArrayList<>();

		for (Role requested : user.getRoles() == null ? List.<Role>of() : user.getRoles())
		{
			roles.add(roleRepository.findByName(requested.getName()).orElseGet(() -> createRole(requested)));
		}

		user.setId(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(roles);

		return toDTO(userRepository.save(user));
	}

	@Override
	public Optional<UserDTO> getUserByUsername(String username)
	{
		return userRepository.findByUsername(username).map(this::toDTO);
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteUser(String username)
	{
		User user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

		userRepository.delete(user);

		return ResponseEntity
				.ok()
				.build();
	}

	@Override
	public List<UserDTO> getAllUsers()
	{
		return userRepository.findAll().stream().map(this::toDTO).toList();
	}

	private Role createRole(Role requested)
	{
		List<String> permissionNames = new ArrayList<>();

		if (requested.getPermissions() == null || requested.getPermissions().isEmpty())
		{
			log.info("-----> Role {} doesn't have permissions explicitly defined, using the defaults.", requested.getName());

			String resource = requested.getName().replace("ROLE_", "");

			for (String suffix : DEFAULT_PERMISSIONS)
			{
				permissionNames.add(resource + suffix);
			}
		}
		else
		{
			requested.getPermissions().forEach(permission -> permissionNames.add(permission.getName()));
		}

		List<Permission> permissions = permissionNames.stream()
				.map(name -> permissionRepository.findByName(name).orElseGet(() -> permissionRepository.save(new Permission(name))))
				.toList();

		Role role = new Role();
		role.setName(requested.getName());
		role.setPermissions(new ArrayList<>(permissions));

		return roleRepository.save(role);
	}

	private UserDTO toDTO(User user)
	{
		return modelMapper.map(user, UserDTO.class);
	}
}
