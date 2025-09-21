package com.easy_split.demo.dtos.requests;

import com.easy_split.demo.enums.Role;

public record UserRequestDTO(String email, String password, Role role) {
}
