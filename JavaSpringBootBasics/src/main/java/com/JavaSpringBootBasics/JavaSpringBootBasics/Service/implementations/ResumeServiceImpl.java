package com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Resume;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Repository.ResumeRepository;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    public ResumeRepository resumeRepository;
    @Override
    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(Long id) {
        log.info("Fetching Resume by id");
        return resumeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Resume not found"));
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
}

//Added logging by @Slf4j annotation which is coming from lombok , which helps us to use
//shorter syntaxes , with direct log , instead of creating logger factory , and we can define
//the require level of severity after the log , like log.info and pass the message which we
//want to display in detail in the console . The available notation are log.warn, log.error
//(uses logback interface , which has the defination of logging)

//If you want to use debug and trace level, which is the more detailed level of the log.
//It is not available by default, for that you need to enable some configuration in the
//application.properties, like logging.level.root=debug , which will enable all the levels
//up and including debug level .

//You can also specify the file path for which you want to enable this level of logging .
//Like here we have given root, so will include all the packages from the root of the
//springboot application . So that will give us a lot of logs , wherever we have defined
//this log statement. So in order to restrict that , we can give a particular filepath like
//logging.level.com.JavaSpringBootBasics.JavaSpringBootBasics.Service.implementations=debug

//Logging levels defined according to their severity.
//trace->debug->info->warn->error->fatal . (increasing order of heirarchy)