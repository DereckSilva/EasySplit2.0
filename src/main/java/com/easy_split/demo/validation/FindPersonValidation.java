package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

public class FindPersonValidation implements ConstraintValidator<FindPerson, String> {

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String person, ConstraintValidatorContext constraintValidatorContext) {
        return this.getPerson(person);
    }

    public boolean personIsValid(String person) {
        return this.getPerson(person);
    }

    protected boolean getPerson(String person) {
        if (person == null) return false;

        if (person.matches("\\d+")) return !Objects.isNull(this.personService.getPersonById(Integer.parseInt(person)));
        return !Objects.isNull(this.userService.getUserEmail(person));
    }
}
