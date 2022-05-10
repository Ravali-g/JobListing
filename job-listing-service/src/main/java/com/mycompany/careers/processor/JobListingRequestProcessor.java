package com.mycompany.careers.processor;



import com.mycompany.careers.model.JobListingRequestDto;
import com.mycompany.careers.model.JobListingResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;

/**
 * interface that describes the contracts of various functions invoked by controller
 */
public interface JobListingRequestProcessor {

    ResponseEntity<?> getAllJobListings(String keyword);

    ResponseEntity<?> createNewJobListing(final JobListingRequestDto newJobListingRequest);

    ResponseEntity<?> deleteJobListing(final int jobListingId);

    ResponseEntity<?> updateJobListing( final JobListingRequestDto jobListingUpdateRequest);

    ResponseEntity<?> findJobListingById(final int jobListingId);

    ResponseEntity<?> findJobListingByLocation(final String location);
}
