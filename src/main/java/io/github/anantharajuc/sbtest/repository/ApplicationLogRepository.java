package io.github.anantharajuc.sbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.anantharajuc.sbtest.model.ApplicationLog;

@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long>
{

}
