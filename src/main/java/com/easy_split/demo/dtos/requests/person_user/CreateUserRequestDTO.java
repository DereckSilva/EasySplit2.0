package com.easy_split.demo.dtos.requests.person_user;

import com.easy_split.demo.validation.Age;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserRequestDTO {

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

    @NotNull(message = "Role is required")
    @Pattern(regexp = "ADMIN|USER", message = "Role must be ADMIN or USER")
    private String role;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @Age
    private LocalDateTime birthdate;

    public CreateUserRequestDTO(String email, String password, String role) {
        this.email    = email;
        this.password = password;
        this.role     = role;
    }
}
