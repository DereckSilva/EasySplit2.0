package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.services.PersonService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FindPersonValidation implements ConstraintValidator<FindPerson, String> {

    @Autowired
    PersonService personService;

    @Override
    public boolean isValid(String person, ConstraintValidatorContext constraintValidatorContext) {
        if (person == null) return false;
        Optional<Person> foundedPerson = this.personService.getPersonByEmailOrId(person);
        if (foundedPerson.isEmpty()) return false;
        return true;
    }
}
