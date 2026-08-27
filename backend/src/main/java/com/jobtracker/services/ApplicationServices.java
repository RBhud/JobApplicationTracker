package com.jobtracker.services;

import java.time.LocalDate;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.jobtracker.database.entities.Application;
import com.jobtracker.database.repositories.ApplicationRepository;

@Service
public class ApplicationServices {
    
    private final ApplicationRepository applicationRepository;

    public ApplicationServices(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void addApplication(String jobTitle, String companyName, String siteLink) {
        Application application = new Application();
        application.setJobTitle(jobTitle.toLowerCase());
        application.setCompanyName(companyName.toLowerCase());
        application.setSiteLink(siteLink);

        LocalDate currentDate = LocalDate.now();
        application.setDateApplied(currentDate);

        applicationRepository.save(application);
    }

    public Iterable<Application> getApplications() {
        return applicationRepository.findAll();
    }

    public void updateApplication(Long id, String jobTitle, String companyName, String siteLink) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Application not found"));
        application.setJobTitle(jobTitle.toLowerCase());
        application.setCompanyName(companyName.toLowerCase());
        application.setSiteLink(siteLink);

        applicationRepository.save(application);
    }

    public void updateApplicationStatus(Long id, String status) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Application not found"));
        application.setStatus(status);

        if (status.toLowerCase().equals("rejected")) {
            LocalDate currentDate = LocalDate.now();
            application.setDateRejected(currentDate);
        }

        applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Application not found"));
        applicationRepository.delete(application);
    }

    public Iterable<Application> getApplicationsByCompany(@Param("companyName") String companyName) {
        return applicationRepository.getApplicationsByCompany(companyName);
    }
}
