package com.easy_split.demo.dtos.response;

import com.easy_split.demo.enums.Role;

public record UserResponseUpdatedDTO(Integer id, String email, Role role) {
}
