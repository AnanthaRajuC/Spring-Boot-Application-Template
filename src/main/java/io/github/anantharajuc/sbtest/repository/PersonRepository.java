package io.github.anantharajuc.sbtest.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.model.Person;

@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{

}
