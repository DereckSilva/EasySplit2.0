package com.easy_split.demo.dtos.requests;

import com.easy_split.demo.enums.Role;

import java.util.Date;

public record UserRequestDTO(String email, String password, Role role, String name, Date birthdate) {
}
