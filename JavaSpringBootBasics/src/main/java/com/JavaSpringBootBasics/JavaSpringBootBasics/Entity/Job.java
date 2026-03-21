package com.JavaSpringBootBasics.JavaSpringBootBasics.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
            name="applicant_jobs",
            joinColumns = @JoinColumn(name="job_id"),
            inverseJoinColumns = @JoinColumn(name="applicant_id")
    )
    List<Applicant> applicants=new ArrayList<>();

    public Job(){};
    public Job(Long id, String title, String description, List<Applicant> applicants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.applicants = applicants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }
}

//Here Job is the owning side(we can amke any side as teh owning side ), so we need to annotate
//it with @JoinTable. Here in case of @ManyToMany relationship we can just track the Fk of
//just teh applicant or FK of just the jobs, as one side can't handle all the relationship
// the PK w.r.t to FK is changing from both the ends . Both the ends have changing mapping
// of the keys, and the keys are not staying constant from any one side also . So we need to
//have a separate table for that , and that is defined in the owning side , by @JoinTable
// annotation .

//Third table created in DB, also helps us to maintain Normalisation , as it stops the
// repeatation of the keys of teh duplicacy of both the tables .

//Inside @JoinTable annotation , we define the name of the combined table formed (of the PK
//,of both the entities ) , then we mention the joinColumn key , in that we mention the
//FK of the combined table , which is referencing the PK of the current owning entity , i.e.
//job_id (so job_id is the PK of the current owning entity , and it the FK of the combined table
//formed , as it is referencing to the PK of this current table , it is the FK of(in) the combined
//table) . Similarly we also mention the inverseJoinColumn key , in which we mention the FK
//of the combined table , which referencing to the PK of the other entity , i.e applicant
//so it is applicant_id . FK belongs to the combined table .

//In this we will individually be passing te id of the applicant and the job id , whom we need
//to map using the addApplicantToJob() method, and individually be finding that applicant and
//job from the repository ( respective repository) , and adding individually to each other's
//entity . (As List so add() method) .

//They are added automatically to the joinTable of the @ManyToMany mapping by the Hiberante
// but to individually persist in each other's table , so as to retrieve from each other's
// table . It helps , in manually adding , in each other's entity .
//Efficient retrival and persistance from each other's entity .
