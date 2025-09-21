package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.UserRequestDTO;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.mappers.UserMapper;
import com.easy_split.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequestDTO user) {
        if (this.userService.getUserByEmail(user.email()) != null) return ResponseEntity.badRequest().build();

        User newUser = this.userService.createUser(UserMapper.toEntity(user));
        return ResponseEntity.ok(UserMapper.toDTO(newUser));
    }
}
