package com.mycompany.careers.model;

public class JobListingRequestDto {

    private int jobListingId;
    private String jobTitle;
    private String jobDescription;
    private String location;



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
}
