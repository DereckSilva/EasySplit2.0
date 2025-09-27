package com.easy_split.demo.services;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    public Optional<Person> getPersonById(Integer id) {
        return this.personRepository.findById(id);
    }

    @Transactional
    public Optional removePerson(Person person) {
        this.personRepository.delete(person);
        return Optional.empty();
    }
}
