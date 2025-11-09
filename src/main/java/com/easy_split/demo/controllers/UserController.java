package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.person_user.CreateUserRequestDTO;
import com.easy_split.demo.dtos.requests.person_user.PersonRequestDTO;
import com.easy_split.demo.dtos.requests.person_user.UpdatedUserDTO;
import com.easy_split.demo.dtos.response.UserResponseDTO;
import com.easy_split.demo.dtos.response.UserResponseUpdatedDTO;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.mappers.PersonMapper;
import com.easy_split.demo.mappers.ResponseMapper;
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
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody CreateUserRequestDTO user) {

        if (this.userService.getUserByEmail(user.getEmail()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMapper.toMap("O usuário já existe", new HashMap<>()));

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
    public ResponseEntity<UserResponseUpdatedDTO> updated(@Valid @RequestBody UpdatedUserDTO user) {

        User userFounded = UserMapper.toEntityUp(user);
        User userUpdated = this.userService.userUpdated(userFounded);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTOUp(userUpdated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable int id) {
        User user = this.userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable int id) {
       User user = this.userService.getUserById(id);
        this.userService.userRemoved(user);
        return ResponseMapper.toResponseEntity("User successfully deleted", HttpStatus.NO_CONTENT, new HashMap<>());
    }
}
