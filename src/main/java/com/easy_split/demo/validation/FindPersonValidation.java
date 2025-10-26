package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Person;
import com.easy_split.demo.entities.User;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

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

    private boolean getPerson(String person) {
        if (person == null) return false;

        Optional<Person> foundedPerson = this.personService.getPersonById(Integer.parseInt(person));
        Optional<User> foundedUser     = this.userService.getUserById(Integer.parseInt(person));
        return foundedPerson.isEmpty() || foundedUser.isEmpty();
    }
}
