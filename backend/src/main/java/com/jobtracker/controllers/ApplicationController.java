package com.jobtracker.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.database.entities.Application;
import com.jobtracker.services.ApplicationServices;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    private final ApplicationServices applicationServices;

    public ApplicationController(ApplicationServices applicationServices) {
        this.applicationServices = applicationServices;
    }

    @PostMapping("/application")
    public Map<String, String> application(@RequestParam String jobTitle, @RequestParam String companyName, @RequestParam String siteLink) {

        try {
            if (jobTitle == null || jobTitle.isEmpty() || companyName == null || companyName.isEmpty()) {
                throw new IllegalArgumentException("Job title and company name cannot be empty");
            }
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }
        
        applicationServices.addApplication(jobTitle, companyName, siteLink);

        return Map.of("data", "Application added successfully");
    }

    @GetMapping("/applications")
    public Map<String, Iterable<Application>> applications() {
        return Map.of("data", applicationServices.getApplications());
    }

    @PutMapping("/application")
    public Map<String, String> updateApplication(@RequestParam Long id, @RequestParam String jobTitle, @RequestParam String companyName, @RequestParam String siteLink) {
        try {
            if (id == null || jobTitle == null || jobTitle.isEmpty() || companyName == null || companyName.isEmpty()) {
                throw new IllegalArgumentException("ID, job title and company name cannot be empty");
            }
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }
        try {
            applicationServices.updateApplication(id, jobTitle, companyName, siteLink);
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }

        return Map.of("data", "Application updated successfully");
    }

    @PutMapping("/application/status")
    public Map<String, String> updateApplication(@RequestParam Long id, @RequestParam String status) {
        try {
            if (id == null || status == null || status.isEmpty()) {
                throw new IllegalArgumentException("ID and status cannot be empty");
            }
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }
        try {
            applicationServices.updateApplicationStatus(id, status);
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }

        return Map.of("data", "Application updated successfully");
    }

    @DeleteMapping("/application")
    public Map<String, String> deleteApplication(@RequestParam Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be empty");
            }
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }
        try {
            applicationServices.deleteApplication(id);
        } catch (IllegalArgumentException e) {
            return Map.of("error", e.getMessage());
        }

        return Map.of("data", "Application deleted successfully");
    }

    @GetMapping("/application/company")
    public Map<String, Iterable<Application>> getApplicationsByCompany(@RequestParam String companyName) {
        Iterable<Application> applications = applicationServices.getApplicationsByCompany(companyName);

        return Map.of("data", applications);
    }

}
