package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.PersonRequestDTO;
import com.easy_split.demo.dtos.requests.UserRequestDTO;
import com.easy_split.demo.dtos.requests.UserRequestUpdatedDTO;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.mappers.PersonMapper;
import com.easy_split.demo.mappers.UserMapper;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public PersonService personService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO user) {

        Map<String,Object> badReqRes = new HashMap<>();
        badReqRes.put("message", "User already exists");

        Map<String, Object> userFounded = new HashMap<>();
        userFounded.put("data", badReqRes);
        if (this.userService.getUserByEmail(user.email()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userFounded);

        PersonRequestDTO person = new PersonRequestDTO(user.name(), user.birthdate());
        Person newPerson        = this.personService.createPerson(PersonMapper.toEntity(person));
        User newUser            = this.userService.createUser(UserMapper.toEntity(user), newPerson);
        newPerson               = this.personService.persistUser(newUser, newPerson);

        Map<String, Object> mapperUserPerson = new HashMap<>();
        mapperUserPerson.put("user", UserMapper.toDTO(newUser));
        mapperUserPerson.put("person", PersonMapper.toDTO(newPerson));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUserPerson);
    }

    @PostMapping("/updated")
    public ResponseEntity updated(@RequestBody UserRequestUpdatedDTO user) {

        Map<String, Object> userNotFound = new HashMap<>();
        userNotFound.put("message", "User not found");

        if (this.userService.getUserById(user.id()).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound);

        User userFounded = UserMapper.toEntityUp(user);
        User userUpdated = this.userService.userUpdated(userFounded);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTOUp(userUpdated));
    }

    @PostMapping("/user/{id}")
    public ResponseEntity findUser(@PathVariable int id) {
        User user = this.userService.getUserById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(user));
    }
}
