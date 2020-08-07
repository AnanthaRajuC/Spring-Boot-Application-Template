package io.github.anantharajuc.sbtest.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.model.Person;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{
	//Refer: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
	
	@Query(value = "SELECT * FROM person WHERE gender = :gender", nativeQuery = true) 
    List<Person> getPersonByGender(@Param("gender") String gender);
	
	@Query(value = "SELECT * FROM person WHERE name = :name", nativeQuery = true) 
	Person getPersonByName(@Param("name") String name);
	
	Optional<Person> findByUsername(@NotBlank String username);
	
	Optional<Person> findByEmailPrimary(@NotBlank String email);
	
	Boolean existsByUsername(@NotBlank String username);
	
	Boolean existsByEmailPrimary(@NotBlank String email);
	
	Optional<Person> findByUsernameOrEmailPrimary(String username, String email);
}
