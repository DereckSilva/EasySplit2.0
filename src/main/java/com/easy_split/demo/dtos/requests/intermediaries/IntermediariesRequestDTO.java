package com.easy_split.demo.dtos.requests.intermediaries;

import com.easy_split.demo.validation.FindPerson;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IntermediariesRequestDTO {

    @FindPerson
    private Integer person;

    @NotNull(message = "Other value is null")
    private boolean otherValue;

    private Double price;

    @AssertTrue(message = "Other value is required. Case other value is true, price is required")
    public Boolean priceIsValid() {
        if (!otherValue) return false;
        return price != null;
    }
}
