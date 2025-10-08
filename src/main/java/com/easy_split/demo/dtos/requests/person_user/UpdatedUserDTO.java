package com.easy_split.demo.dtos.requests.person_user;

import com.easy_split.demo.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UpdatedUserDTO {

    @NotNull(message = "ID is required")
    private Integer id;

    @NotNull(message = "Email is required")
    @Email(message = "The email informed is invalid")
    private String email;

    private Role role;

    @Min(value = 8, message = "The number of characters needs must be equal or more than 8")
    @Max(value = 16, message = "The number of characters needs must be equal or less than 16")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one letter, one number, one special character, and be at least 8 characters long")
    private String password;

    private Integer person_id;
}
