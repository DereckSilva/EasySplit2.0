package com.easy_split.demo.dtos.requests.person_user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonRequestDTO {

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Birthdate is required")
    @NotEmpty(message = "Birthdate can't be empty")
    private LocalDateTime birthdate;

    public PersonRequestDTO (String name, LocalDateTime birthdate) {
        this.name      = name;
        this.birthdate = birthdate;
    }
}
