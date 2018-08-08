package com.arc.sbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arc.sbtest.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>
{

}
