package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Application;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Resume;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicantRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    public ApplicantRepository applicantRepository;
    @Override
    public Applicant saveApplicant(Applicant applicant) {
        Resume resume=applicant.getResume();
        List<Application> applications=applicant.getApplications();
        if(resume!=null)
        {
            resume.setApplicant(applicant);
        }
        if(applications!=null)                          //as if the applications is null ,
        {                                               //it will crash .
            for(Application application:applications)
            {
                application.setApplicant(applicant);
            }
        }
        return applicantRepository.save(applicant);
    }

    @Override
    public List<Applicant> getAllApplicant() {
        return applicantRepository.findAll();
    }

    @Override
    public Applicant getApplicantById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Applicant not found"));
    }

    @Override
    public String deleteApplicantById(Long id) {
        applicantRepository.deleteById(id);
        return "Applicant deleted";
    }

    public List<Applicant> findApplicantsByName(String name)
    {
        return applicantRepository.findByName(name);
    }

    public List<Applicant> findApplicantByPartialName(String name)
    {
        return applicantRepository.findByPartialName(name);
    }
}

//In this we are using the interface, but spring will first scan the Service class and
//create a bean of it as we have given @Service and at runtime inject this bean wherever it's
//interface is been referred as it is of that type . So spring creates obj at runtime, you
// don't have to create obj. If 2 bean is of same type)(asI have just specified this type of
//bean I required ) so Spring gets confused so at that time provide @Qualifier annotation.

//orElseThrow is expected to throw an exception so you can't pass string .
//orElseThrow expects a supplier lambda function to create obj of Exception. So if a value
//is not found throw an exception . orElse would return a default value instead , like
//new Applicant() , so just an empty default applicant obj, instead of exception.

//As finding By Id may or may not exist so it returns optional but as we have handled it
//there so we can say that it returns , otherwise disrupts the flow and throws exception.
