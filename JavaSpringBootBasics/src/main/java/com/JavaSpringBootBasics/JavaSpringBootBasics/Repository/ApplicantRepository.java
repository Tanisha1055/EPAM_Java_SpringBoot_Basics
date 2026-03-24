package com.JavaSpringBootBasics.JavaSpringBootBasics.Repository;

import com.JavaSpringBootBasics.JavaSpringBootBasics.Entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

    List<Applicant> findByName(String name);

    @Query("SELECT a from Applicant a where a.name LIKE %:name%" )
    List<Applicant> findByPartialName (@Param("name") String name);
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


//JPQL Query:

//Now this type of query lets you write more custom queries (like sql) which interact with
//entities , instead of tables. So it is more object-oriented as it interacts with SpringbBoot's
//ORM directly . So it works with any sort of DB , like MySQL , Postgres , as it is a generalised
//query (object-oriented) so works with everything.

//So it is more flexible than Query Derivation method . As you can customise your query to an
//extend which you can't do with the normal query derivation, as here I am writing a query to
//find name byu Partial name which is not possible in query derivation as we have to write
//the entire field name , so the entire name will be matched , not the partial name , so that
//you would need to write queries like %name , which is possible by SQL ,but you have to keep
//it database generic , so you used object-oriented JPQL.

//Here in order to write query by partial name , we have to write JPQL whose syntax is :
//@Query annotation, which indicates that you have to write a query , then within (" ") quotes
//you have to write you query, which is similar to SQL . but a little different , as SQL
//would indicate something like : SELECT * FROM applicant WHERE name like %name% ; but in
//JPQL we would say: SELECT a from Applicant a WHERE a.name LIKE %:name% . And we have to
//write it on top of a method , and this :name is very important as it indicates the variable
//in the query which is substitutable by the incoming method arg , as we are writing it on top
//of a method. Now to indicate which variable in the query is substitutable by which name
//of the arg, of the method , after writing the normal method , we give  @Param annotation
//inside the arg, and specify the name of the arg whose :name we have to search again within
//(" ") quotes, like @Param("name") and then specify the normal arg .
//It's just a normal method with @Query() defined on top of it and @Param written inside of it .

//Now it is ready to be used in service layer . Normally like the method name, i.e. calling
//by the obj of repository layer , calling like the normal function . So the obj of repository
//layer calling the normal function (query function) .