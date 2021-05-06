package io.github.anantharajuc.sbat.example.crm.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.sbat.example.crm.user.model.Person;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * Repository class for <code>Person</code> domain object. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{   
	/*
     * Retrieve a {@link Person} from the data store by gender.
     * @param gender the gender to search for
     * @return <code>List</code> of {@link Person}'s if found
     */
	@Query(value = "SELECT * FROM example_person WHERE gender = :gender",nativeQuery = true) 
	@Transactional(readOnly=true)
    List<Person> getPersonByGender(@Param("gender") String gender);
	
	/*
     * Retrieve a {@link Person} from the data store by name.
     * @param name the name to search for
     * @return The {@link Person} if found
     */
	@Query(value = "SELECT * FROM example_person WHERE name = :name",nativeQuery = true) 
	@Transactional(readOnly=true)
	Person getPersonByName(@Param("name") String name);
	
	/**
     * Retrieve a {@link Person} from the data store by username.
     * @param username the username to search for
     * @return The {@link Person} if found
     */
	@Transactional(readOnly = true)
	Optional<Person> findByUsername(@NotBlank String username);

	/*
     * Retrieve a {@link Person} from the data store by createdBy.
     * @param createdBy the createdBy to search for
     * @return <code>List</code> of {@link Person}'s if found
     */
	@Query(value = "SELECT * FROM example_person WHERE created_by = :createdBy",nativeQuery = true) 
	@Transactional(readOnly=true)
	List<Person> getPersonByCreatedBy(@Param("createdBy") String createdBy);

	/*
     * Retrieve a {@link Person} from the data store by username or email.
     * @param username the username to search for or
     * @param email the email to search for
     * @return the {@link Person} if found
     */
	@Transactional(readOnly = true)
	Optional<Person> findByUsernameOrEmailPrimary(String username, String email);
	
	@Transactional(readOnly = true)
	Boolean existsByUsername(@NotBlank String username);
}
