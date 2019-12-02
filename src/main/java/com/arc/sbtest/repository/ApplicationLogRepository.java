package com.arc.sbtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arc.sbtest.model.ApplicationLog;

@Repository
public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Long>
{

}
