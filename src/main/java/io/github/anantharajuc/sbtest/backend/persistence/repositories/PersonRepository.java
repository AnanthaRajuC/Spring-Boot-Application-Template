package io.github.anantharajuc.sbtest.backend.persistence.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.person.Person;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository class for <code>Person</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Anantha Raju C
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{	
	/**
     * Retrieve a {@link Person} from the data store by gender.
     * @param gender the gender to search for
     * @return the {@link Person} if found
     */
	@Query(value = "SELECT * FROM person WHERE gender = :gender", nativeQuery = true) 
	@Transactional(readOnly=true)
    List<Person> getPersonByGender(@Param("gender") String gender);
	
	/**
     * Retrieve a {@link Person} from the data store by name.
     * @param name the name to search for
     * @return the {@link Person} if found
     */
	@Query(value = "SELECT * FROM person WHERE name = :name", nativeQuery = true) 
	@Transactional(readOnly=true)
	Person getPersonByName(@Param("name") String name);
	
	@Transactional(readOnly = true)
	Optional<Person> findByUsername(@NotBlank String username);
	
	@Transactional(readOnly = true)
	Boolean existsByUsername(@NotBlank String username);
	
	@Transactional(readOnly = true)
	Optional<Person> findByUsernameOrEmailPrimary(String username, String email);
}
