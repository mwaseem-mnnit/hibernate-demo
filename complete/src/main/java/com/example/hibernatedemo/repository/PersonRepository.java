package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.multiplebagfetch.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT distinct p FROM Person p JOIN FETCH p.autos a")
    List<Person> findAll();
}
