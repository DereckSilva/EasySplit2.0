package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.services.PersonService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FindPersonValidation implements ConstraintValidator<FindPerson, Integer> {

    @Autowired
    PersonService personService;

    @Override
    public boolean isValid(Integer personId, ConstraintValidatorContext constraintValidatorContext) {
        if (personId == null) return false;
        Optional<Person> person = this.personService.getPersonById(personId);
        if (person.isEmpty()) return false;
        return true;
    }
}
