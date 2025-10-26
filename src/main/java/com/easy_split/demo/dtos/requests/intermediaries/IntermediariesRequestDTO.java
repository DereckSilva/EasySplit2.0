package com.easy_split.demo.dtos.requests.intermediaries;

import com.easy_split.demo.validation.FindPerson;
import com.easy_split.demo.validation.FindPersonValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntermediariesRequestDTO {

    private String person;

    @NotNull(message = "Other value is null")
    private boolean otherValue;

    private Double price;

    @AssertTrue(message = "Other value is required. Case other value is true, price is required")
    public Boolean priceIsValid() {
        if (!otherValue) return false;
        return price != null;
    }

    @AssertTrue(message = "Invalid person or person not found")
    public boolean personIsValid() {
        if (person == null) return false;
        FindPersonValidation findPersonValidation = new FindPersonValidation();
        return findPersonValidation.personIsValid(person);

    }
}
