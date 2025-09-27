package com.easy_split.demo.dtos.requests;

import com.easy_split.demo.enums.Role;

import javax.swing.text.html.Option;
import java.util.Optional;

public record UserRequestUpdatedDTO(Integer id, Optional<String> email, Optional<String> password, Optional<Role> role) {
}
