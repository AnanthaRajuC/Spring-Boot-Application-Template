package com.arc.sbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arc.sbtest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>
{

}
