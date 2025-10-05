package com.easy_split.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeValidation.class})
public @interface Age {
    String message() default "Age of user is required and need be less than 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
