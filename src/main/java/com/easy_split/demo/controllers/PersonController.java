package com.easy_split.demo.controllers;

import com.easy_split.demo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("{id}")
    public String helloWorld(@PathVariable int id) {
        return "id: " + id;
    }

}
