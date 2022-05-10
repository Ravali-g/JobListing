package com.mycompany.careers.model;

import javax.persistence.*;


@Entity
public final class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="JobListingId", insertable = true, updatable = false, nullable = false)
    private int jobListingId;

    @Basic
    @Column(name = "JobTitle", insertable = true, updatable = true, nullable = false)
    private String jobTitle;

    @Basic
    @Column(name = "JobDescription", insertable = true, updatable = true, nullable = false)
    private String jobDescription;

    @Basic
    @Column(name = "Location", insertable = true, updatable = true, nullable = false)
    private String location;

    @Basic
    @Column(name = "IsDeleted", insertable = true, updatable = true, nullable = false)
    private Boolean deleted = Boolean.FALSE;


    public int getJobListingId() {
        return jobListingId;
    }

    public void setJobListingId(int jobListingId) {
        this.jobListingId = jobListingId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "JobListing{" +
                "jobListingId=" + jobListingId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", location='" + location  +
                '}';
    }
}