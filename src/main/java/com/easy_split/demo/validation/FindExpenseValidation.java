package com.easy_split.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FindExpenseValidation implements ConstraintValidator<FindExpense, Integer> {

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
