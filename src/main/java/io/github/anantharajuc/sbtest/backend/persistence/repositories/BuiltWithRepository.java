package io.github.anantharajuc.sbtest.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.BuiltWith;

@Repository
public interface BuiltWithRepository extends JpaRepository<BuiltWith, Integer>
{

}
