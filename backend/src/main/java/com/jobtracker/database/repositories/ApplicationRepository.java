package com.jobtracker.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobtracker.database.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> { 

    @Query("SELECT a FROM Application a WHERE a.companyName LIKE CONCAT('%', :companyName, '%')")
    public Iterable<Application> getApplicationsByCompany(@Param("companyName") String companyName);

 }
