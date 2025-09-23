package com.easy_split.demo.services;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public Person createPerson(Person person) {
        return this.personRepository.save(person);
    }

    public Person persistUser(User user, Person person) {
        person.setUser(user);
        return this.personRepository.save(person);
    }
}
