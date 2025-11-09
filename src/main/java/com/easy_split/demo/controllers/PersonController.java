package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.response.PersonResponseDTO;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.mappers.PersonMapper;
import com.easy_split.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> findUser(@PathVariable int id) {
        Person person = this.personService.getPersonById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(PersonMapper.toDTO(person));
    }
}
