package com.jobtracker.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "application")
public class Application {
    
    @Id
    @GeneratedValue
    long id;

    @Column(name = "job_title", nullable = false)
    String jobTitle;

    @Column(name = "company_name", nullable = false)
    String companyName;

    @Column(name = "site_link")
    String siteLink;

    @Column(name = "status", nullable = false)
    String status = "applied";

    @Column(name = "date_applied", nullable = false)
    LocalDate dateApplied;

    @Column(name = "date_rejected")
    LocalDate dateRejected;

    public long getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSiteLink() {
        return siteLink;
    }

    public void setSiteLink(String siteLink) {
        this.siteLink = siteLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public LocalDate getDateRejected() {
        return dateRejected;
    }

    public void setDateRejected(LocalDate dateRejected) {
        this.dateRejected = dateRejected;
    }

}
