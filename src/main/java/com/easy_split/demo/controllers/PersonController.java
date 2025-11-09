package com.easy_split.demo.controllers;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.mappers.PersonMapper;
import com.easy_split.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findUser(@PathVariable int id) {
        Optional<Person> person = this.personService.getPersonById(id);

        Map<String, String> personNotFound = new HashMap<>();
        personNotFound.put("message", "Person not found");

        if (person.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personNotFound);
        return ResponseEntity.status(HttpStatus.FOUND).body(PersonMapper.toDTO(person.get()));
    }
}
