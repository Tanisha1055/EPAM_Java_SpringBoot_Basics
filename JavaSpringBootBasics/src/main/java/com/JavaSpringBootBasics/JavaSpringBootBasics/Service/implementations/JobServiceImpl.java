package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Job;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicantRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.JobRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.JobService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
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
    @Transactional
    public Job addApplicantToJob(Long jobId, Long applicantId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));


        job.getApplicants().add(applicant);

        applicant.getJobs().add(job);

        return jobRepository.save(job);
    }

    public Page<Job> findJobsByPage (int page, int size)
    {
        Pageable pageable= PageRequest.of(page,size);
        return jobRepository.findAll(pageable);
    }

    public Page<Job> findJobsBySortedPage(int page, int size , String sortBy)
    {
        Pageable pageable=PageRequest.of(page,size, Sort.by(sortBy).ascending());
        return jobRepository.findAll(pageable);
    }

    public List<Job> getSortedData(String name)
    {
        return jobRepository.findAll(Sort.by(name).ascending());
    }
}

// as rest everything is handled by orElseThrow() , so I can directly use the value .
//get() is risky , orElse is safe() , alo ifPresent() would add unnecessary complexity ,
//by addition of lambda function . as if not present you won't be able to return jobs .


//Pagination:


//Here we have added a method to get the results divided in pages, so that we don't have to
//get all the data in one place , which would make the query slower. So pagination helps in
//faster retrieval of smaller chunks of data . Page literally means smaller chunk of data .
//so we specify the page , i.e. the number of times we want to retrieve the data in certain
//numbers of time .

//We also define the size ,i.e. the no. of data needed in one page , or the amount of data
//needed in one go .

//So if we have total 11 data , and we divide the page in 3 and the size is 5 , so in the 1st
//page i.e. the 0th page , we will have 5 data (first 5), similarly in 2nd page we have (next)
//5 data , and in the last page we have 1 data left (remaining 1 data left) .

//So pageable is an interface which is basically a set of instructions which is passed to the
//repository layer , so the hibernate can use it to execute queries internally . As the Pageable
//contains the page and size metadata info , so the hibernate internally generates queries
//like LIMIT and OFFSET to execute that function . Now PageRequest.of() is a static to create
//obj of Pageable , as Pageable is an interface, so we need to pass an obj of to repository

//So PageRequest is a class , which implements Pageable , so the obj returned is of Pageable
//which is passed to repository methods like findAll .

//So the result coming from repository, is Page of obj, i.e. which contains data and metadata
//the amount of data needed to be visible to frontend . So Page is an interface, but the repository
//returns obj of Page type .


//Pagination with sorting:

//So here we also pass the argument saying, by what field do we need to sort by i.e. sortBy.
//Like w.r.t name, or email or whatever.

//So Sort is a class which has a static method .of() which returns a Sort obj which defines
//which field and which direction of obj to be sorted by .

//And you pass them in the pagination syntax, to get the sorted page by some criteria .

//So sorting exist individually also, not necessary with pagination .



//Transactions:

//So when to save something to db either when everything written in that method succeed, or
//not save it otherwise. In other words don't do partial saving in db , i.e. saving some
// portion of data and nit saving the rest of the further db operations, due to failure midway .
// In that case transaction would abort the prev operation also ,i.e. if everything succeed
// then onl we will save in db . At that time we use transactions. So in order to make that
// method as transactional , we need to annotate it with @Transactional .