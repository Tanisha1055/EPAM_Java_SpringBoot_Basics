package com.JavaSpringBootBasics.JavaSpringBootBasics.Repository;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    List<Applicant> findByName(String name);
}


//Query Derivation :

//In this we can define custom query methods, which the Hibernate will interpret the query by
//the name of the method. i.e. by the name of the method given, like findByName(name) , it can
//create queries like SELECT * from Applicant WHERE name=name ; so it can use the fields of
//the class and as it the repository of the Applicant so it knows whose TABLE to use in creation
//of DB queries , and it can see the class fields or the Table column names .

//We do have some default query methods which we don't need to define in the query derivation
//in the repository layer , like findById(id) ,as it is the primary key . It can also verify
//the return type as we have defined in the methods written .

//Now we can use it in the service layer .