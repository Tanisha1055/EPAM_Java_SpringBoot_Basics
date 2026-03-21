package com.JavaSpringBootBasics.JavaSpringBootBasics.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToOne(mappedBy = "applicant",cascade = CascadeType.ALL)
    private Resume resume;

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @OneToMany(mappedBy = "applicant",cascade =CascadeType.ALL)
    private List<Application> applications= new ArrayList<>();

    public Applicant(){};
    public Applicant(Long id, String name, String email, Resume resume) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.resume=resume;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

//Info:

//Here Applicant is the non-owning side or the non-dominating one.
//As it's primary key , is mapped as a FK to Resume side

//We have given @OneToOne annotation here as well to indicate bidirectional mapping , i.e.
//from the Applicant also you can get or access the Resume

//we have to give mappedBy "applicant" as we are saying that the applicant is the one whose
//PK is been used , it is non-dominant side as it is not responsible for managing the relationship
//as the other side has the FK , so it's table in DB is responsible for knowing the relationship
//between these 2 entities

//we give cascade as CascadeType all as we need to perform all operation that we require on
//Applicant to be performed on Resume first as Resume is a field in Applicant so it needs to
//have completed the successful application of the operation before the Applicant can completely
//proceed with the operation (ALL indicates all kind of operation like save, delete etc).

//It's not about setting everything in the constructor, the save method in db will take care of
//everything as it sees the fields and all , it's just we have to establish the correct
//relationship w.r.t the owning side and all .
//Constructors may be a way to settings up correctly , like establishing the correct parent
//child relationship, like individually setting up the applicant in the applications, of the
//applicant . But again that is not necessary as we can do that in the saveApplicant method
//of the service layer, but that has to be done somewhere , as the save method just takes care
//of saving teh things correctly in the db , not setting the relationships up .

//Here I Applicant can apply for many Applications , hence the relationship @OneToMany has been
//used , with the rest everything same of mappedBy and the cascadeType. But as we know that
//It has many applications , so the field will have a List of applications, associated with
//1 applicant , not just application. (As it indicates 1 applicant relationship).