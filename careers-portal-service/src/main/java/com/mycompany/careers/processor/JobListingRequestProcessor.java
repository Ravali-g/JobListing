package com.mycompany.careers.processor;



import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobListingResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * interface that describes the contracts of various functions in controller
 */
public interface JobListingRequestProcessor {

    ResponseEntity<Set<JobListingResponseDto>> getAllJobListings();

    ResponseEntity<?> createNewJobListing(final JobListingRequestDto newJobListingRequest);

    ResponseEntity<?> deleteJobListing(final int jobListingId);

    ResponseEntity<?> updateJobListing(final int jobListingId, final JobListingRequestDto jobListingUpdateRequest);

    ResponseEntity<JobListingResponseDto> findJobListingById(final int jobListingId);

}
