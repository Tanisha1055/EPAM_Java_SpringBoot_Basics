package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Application;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicantRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicationRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  ApplicantRepository applicantRepository) {
        this.applicationRepository = applicationRepository;
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Application saveApplication(Long applicantId, Application application) {

        //  Fetch parent (VERY IMPORTANT because nullable=false)
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        application.setApplicant(applicant);

        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}

//Here setting the applicant in applications individually after finding them by id ,
//ensures more type safety and type checking .Here more control.Validation if applicant exists
//Here more validation as exist By Id() or not and less chance of malformed data being saved
//And here better mapping and understanding is there with the url applications/applicant/1

//OneToMany and @ManyToOne go hand in hand