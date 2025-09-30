package com.easy_split.demo.dtos.requests;

import com.easy_split.demo.validation.FindPerson;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntermediariesRequestDTO {

    @FindPerson
    private Integer person_id;

    @NotNull(message = "Price can't be null")
    private Double price;
}
