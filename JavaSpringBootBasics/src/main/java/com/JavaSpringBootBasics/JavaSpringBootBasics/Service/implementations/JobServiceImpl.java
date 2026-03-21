package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicantRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.JobRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.JobService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ApplicantRepository applicantRepository;

    public JobServiceImpl(JobRepository jobRepository,
                          ApplicantRepository applicantRepository) {
        this.jobRepository = jobRepository;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }


    @Override
    public Job addApplicantToJob(Long jobId, Long applicantId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));


        job.getApplicants().add(applicant);

        applicant.getJobs().add(job);

        return jobRepository.save(job);
    }
}

// as rest everything is handled by orElseThrow() , so I can directly use the value .
//get() is risky , orElse is safe() , alo ifPresent() would add unnecessary complexity ,
//by addition of lambda function . as if not present you won't be able to return jobs .