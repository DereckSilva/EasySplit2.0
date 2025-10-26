package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
