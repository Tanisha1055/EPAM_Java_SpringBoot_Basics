package com.JavaSpringBootBasics.JavaSpringBootBasics.Controller;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // CREATE
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    // GET ALL
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Job getJob(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }

    // 🔥 ADD APPLICANT TO JOB
    @PostMapping("/{jobId}/applicants/{applicantId}")
    public Job addApplicantToJob(@PathVariable Long jobId,
                                 @PathVariable Long applicantId) {
        return jobService.addApplicantToJob(jobId, applicantId);
    }
}