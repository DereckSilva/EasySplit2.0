package com.easy_split.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class AgeValidation implements ConstraintValidator<Age, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime birthdate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (birthdate == null) return false;
        if ((currentDateTime.getYear() - birthdate.getYear()) < 18) return false;
        return true;
    }
}
