package io.github.anantharajuc.sbat.core_backend.security.user.repository;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbat.core_backend.security.user.model.User;

/**
 * Repository class for <code>User</code> domain object. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	/**
     * Retrieve a {@link User} from the data store by username.
     * @param username the username to search for
     * @return {@link User} if found
     */
	Optional<User> findByUsername(@NotBlank String username);
}
