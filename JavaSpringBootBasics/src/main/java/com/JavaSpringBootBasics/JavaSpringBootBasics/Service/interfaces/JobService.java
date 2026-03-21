package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;

import java.util.List;

public interface JobService {

    Job createJob(Job job);

    List<Job> getAllJobs();

    Job getJobById(Long id);

    void deleteJob(Long id);

    Job addApplicantToJob(Long jobId, Long applicantId);
}