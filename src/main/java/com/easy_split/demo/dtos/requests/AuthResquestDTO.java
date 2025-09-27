package com.easy_split.demo.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthResquestDTO {

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password can't be empty")
    @Size(min = 8, max = 16)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one letter, one number, one special character, and be at least 8 characters long")
    private String password;

}
