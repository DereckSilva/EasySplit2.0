package com.easy_split.demo.controllers;

import com.easy_split.demo.entities.User;
import com.easy_split.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String register(@RequestBody User user) {
        return "registrado com sucesso";
    }
}
