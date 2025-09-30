package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Expense;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FindExpenseValidation implements ConstraintValidator<FindException, Integer> {

    @Autowired
    //ExpenseService expenseService;

    @Override
    public boolean isValid(Integer expenseId, ConstraintValidatorContext constraintValidatorContext) {
        if (expenseId == null) return false;
        //Optional<Expense> expense = this.expenseRepository.getExpenseById(expenseId);
        //if (expense.isEmpty()) return false;
        return true;
    }
}
