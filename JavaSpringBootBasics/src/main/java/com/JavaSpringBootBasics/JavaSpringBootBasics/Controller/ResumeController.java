package com.JavaSpringBootBasics.JavaSpringBootBasics.Controller;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Resume;
import com.JavaSpringBootBasics.JavaSpringBootBasics.Service.interfaces.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    public ResumeService resumeService;
    @PostMapping
    public Resume saveResume(@RequestBody Resume resume)
    {
        return resumeService.saveResume(resume);
    }
    @GetMapping("/{id}")
    public Resume getResumeById(@PathVariable Long id)
    {
        return resumeService.getResumeById(id);
    }
    @GetMapping
    public List<Resume> getAllResumes()
    {
        return resumeService.getAllResumes();
    }
}
