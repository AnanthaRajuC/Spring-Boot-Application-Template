package io.github.anantharajuc.sbat.core_backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbat.core_backend.persistence.model.BuiltWith;

/**
 * Repository class for <code>BuiltWith</code> domain object. All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Repository
public interface BuiltWithRepository extends JpaRepository<BuiltWith, Integer>
{

}
