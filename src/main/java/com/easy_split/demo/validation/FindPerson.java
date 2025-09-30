package com.easy_split.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FindPersonValidation.class})
public @interface FindPerson {
    String message() default "That person don't exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
