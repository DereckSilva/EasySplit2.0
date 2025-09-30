package com.easy_split.demo.dtos.requests;

import com.easy_split.demo.validation.FindException;
import com.easy_split.demo.validation.FindPerson;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntermediaryRequestDTO {

    @FindPerson
    private Integer person_id;

    @NotNull(message = "Price can't be null")
    private Double price;
}
