package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.person_user.CreateUserRequestDTO;
import com.easy_split.demo.dtos.requests.person_user.PersonRequestDTO;
import com.easy_split.demo.dtos.requests.person_user.UpdatedUserDTO;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.mappers.PersonMapper;
import com.easy_split.demo.mappers.UserMapper;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PersonService personService;

    @Autowired
    public UserController(UserService userService, PersonService personService) {
        this.userService = userService;
        this.personService = personService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody CreateUserRequestDTO user) {


        Map<String,Object> badReqRes = new HashMap<>();
        badReqRes.put("message", "Users already exists");
        if (this.userService.getUserByEmail(user.getEmail()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badReqRes);

        PersonRequestDTO person = new PersonRequestDTO(user.getName(), user.getBirthdate());
        Person newPerson        = this.personService.createPerson(PersonMapper.toEntity(person));
        User newUser            = this.userService.createUser(UserMapper.toEntity(user), newPerson);
        newPerson               = this.personService.persistUser(newUser, newPerson);

        Map<String, Object> mapperUserPerson = new HashMap<>();
        mapperUserPerson.put("user", UserMapper.toDTO(newUser));
        mapperUserPerson.put("person", PersonMapper.toDTO(newPerson));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapperUserPerson);
    }

    @PostMapping("/updated")
    public ResponseEntity updated(@Valid @RequestBody UpdatedUserDTO user) {

        Map<String, Object> userNotFound = new HashMap<>();
        userNotFound.put("message", "User not found");

        if (this.userService.getUserById(user.getId()).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound);

        Map<String, Object> personNotFound = new HashMap<>();
        personNotFound.put("message", "Person not found");

        if (this.personService.getPersonById(user.getPerson_id()).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(personNotFound);

        User userFounded = UserMapper.toEntityUp(user);
        User userUpdated = this.userService.userUpdated(userFounded);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTOUp(userUpdated));
    }

    @GetMapping("/{id}")
    public ResponseEntity findUser(@PathVariable int id) {
        Optional<User> user = this.userService.getUserById(id);

        Map<String,Object> userNotFound = new HashMap<>();
        userNotFound.put("message", "User not found");

        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(user.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = this.userService.getUserById(id).get();
        this.userService.userRemoved(user);
        Map<String, Object> userRemoved = new HashMap<>();
        userRemoved.put("message", "User successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userRemoved);
    }
}
