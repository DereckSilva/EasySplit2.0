package com.easy_split.demo.services;

import com.easy_split.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public String personService() {
        return "hello do service";
    }
}
