package io.github.anantharajuc.sbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.model.ApplicationSetings;

@Repository
public interface ApplicationSettingsRepository extends JpaRepository<ApplicationSetings, Long>
{

}
