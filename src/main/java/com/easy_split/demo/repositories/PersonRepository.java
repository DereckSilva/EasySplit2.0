package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Integer> {

    public Optional<Person> findByEmail(String email);

    public Optional<Person> findByEmailOrId(String identifier);
}
