package com.JavaSpringBootBasics.JavaSpringBootBasics.Entity;

import jakarta.persistence.*;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToOne(mappedBy = "applicant",cascade = CascadeType.ALL)
    private Resume resume;

    public Applicant(){};
    public Applicant(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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
//PK is been used , it is non-dominant side as it is not responsible for managing the relatioship
//as the other side has the FK , so it's table in DB is responsible for knowing the relationship
//between these 2 entities
//we give cascade as CascadeType all as we need to perform all operation that we require on
//Applicant to be performed on Resume first as Resume is a feild in Applicant so it needs to
//have completed the successful application of the operation before the Applicant can completely
//proceed with the operation (ALL inidcates all kind of operation like save, delete etc).