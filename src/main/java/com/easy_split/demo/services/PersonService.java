package com.easy_split.demo.services;

import com.easy_split.demo.controllers.error.FindUserException;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return this.personRepository.save(person);
    }

    public Person persistUser(User user, Person person) {
        person.setUser(user);
        return this.personRepository.save(person);
    }

    public Person getPersonById(Integer id) {
        Optional<Person> person =  this.personRepository.findById(id);
        if (person.isEmpty()) throw new FindUserException("O usuário não foi encontrado");
        return person.get();
    }

    @Transactional
    public Optional<Object> removePerson(Person person) {
        this.personRepository.delete(person);
        return Optional.empty();
    }
}
