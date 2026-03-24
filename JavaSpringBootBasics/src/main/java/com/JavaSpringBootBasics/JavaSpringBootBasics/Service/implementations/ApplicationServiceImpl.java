package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Application;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicantRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ApplicationRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ApplicationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("dev")
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


//Profiling :

//Spring enables profiling which is basically , enable a certain bean creation and configuration
//only when certain environment is active , like dev, prod and qa . The concept arise from
//the fact that certain beans and configuration are required for certain environment only
//for example if a service is there to interact with mysql in dev and needs postgres in prod
//so we can specify that with the help of @Profile annotation .

//So basically in @Profile we mention the type of environment required for this bean
//in (" ") quotes, and we configure that for the entire code. Now to switch between environments
//or to be in a certain environment as a matter of fact or to test the code , we need to set
//the environment in application.properties, by the line spring.profiles.active=dev (i.e.
//the required environment) that makes the required environment active and the beans required
//for only that environment to be created .(The ones marked with @Profile("dev") annotation ).
//So only those beans will be created.

//This allows us to dynamically switch between the environment .
//Like there can also be a scenario that we want to have dummy data in dev , so will write
//a service which does that job and in prod we want to have real time prod optimised active
//service func, so we will write a code for service specific to prod environment and make
//the prev one dev specific . So we can dynamically change the data ,as the required environment.

//Another important thing that you can do is set up environment specific configuration
//i.e. database connection , username, password ,log levels etc . In environment specific
//application.properties , which is named after them like .
//application-dev.properties and inside of which you can define your database connection
//url which is required for that specific environment type, hence similarly username and
//password . You can also customise different log levels for different environment as per
//needs . The requirement of this came from the fact that , in different environment you may
//want different database , like in dev you may want mysql and in prod  you may want postgres
//similarly you may want some specific connections in prod , which you don't want in your
//dev environment (like some another application url). So to segregate that distinction
//of configuration we have created different application-environmentspecific.properties
//files . So the process is that when you configure certain environment to be active then
//that environments property file will be active . It will be the dominated one w.r.t the
//default application.properties (so if something is written in application.properties and
//as well as in application-dev.properties and the profile.active=dev then the ones written
//in dev will be considered as opposed to the default ones). But if something is not mentioned
//in the environment-specific properties file and is there in the default file then that
//will be considered from the default file (as the name suggest it is written for the
//default property of all).

//It helps us to switch the database connection dynamically.