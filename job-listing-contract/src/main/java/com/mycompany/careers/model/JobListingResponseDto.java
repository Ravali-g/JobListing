package com.mycompany.careers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.mycompany.careers.constants.JobListingControllerJsonConstants.*;

public class JobListingResponseDto {
    @JsonProperty(value = JOB_ID)
    private final int jobId;
    @JsonProperty(value = JOB_TITLE)
    private final String jobTitle;
    @JsonProperty(value = JOB_LOCATION)
    private final String jobLocation;
    @JsonProperty(value = JOB_DESCRIPTION)
    private final String jobDescription;

    @JsonCreator
    public JobListingResponseDto(@JsonProperty(value = JOB_ID) int jobId,
                                 @JsonProperty(value = JOB_TITLE) String jobTitle,
                                 @JsonProperty(value = JOB_DESCRIPTION) String jobDescription,
                                 @JsonProperty(value = JOB_LOCATION) String jobLocation){
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
    }

    public JobListingResponseDto(JobListing jobListing){
        this(jobListing.getJobListingId(),jobListing.getJobTitle(),jobListing.getJobDescription(),jobListing.getLocation());
    }

}
