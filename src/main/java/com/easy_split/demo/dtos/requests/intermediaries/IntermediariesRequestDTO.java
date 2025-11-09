package com.easy_split.demo.dtos.requests.intermediaries;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import com.easy_split.demo.validation.FindPersonValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;

@Data
public class IntermediariesRequestDTO {

    private final PersonService personService;
    private final UserService userService;

    private String person;

    @NotNull(message = "Other value is null")
    private boolean otherValue;

    private Double price;

    public IntermediariesRequestDTO(PersonService personService, UserService userService, Person person, Double price) {
        this.personService = personService;
        this.userService = userService;
        this.person = person.getUser().getEmail();
        this.price = price;
    }

    @AssertTrue(message = "Other value is required. Case other value is true, price is required")
    public Boolean priceIsValid() {
        if (!otherValue) return false;
        return price != null;
    }

    @AssertTrue(message = "Invalid person or person not found")
    public boolean personIsValid() {
        if (person == null) return false;
        FindPersonValidation findPersonValidation = new FindPersonValidation(personService,  userService);
        return findPersonValidation.personIsValid(person);

    }
}
