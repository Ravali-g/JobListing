package com.mycompany.careers.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {

}
