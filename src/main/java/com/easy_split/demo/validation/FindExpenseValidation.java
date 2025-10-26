package com.easy_split.demo.validation;

import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.services.ExpenseService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FindExpenseValidation implements ConstraintValidator<FindExpense, String> {

    @Autowired
    ExpenseService expenseService;

    @Override
    public boolean isValid(String expenseIdent, ConstraintValidatorContext constraintValidatorContext) {
        return this.getExpense(expenseIdent);
    }

    public boolean expenseIsValid(String expenseIdent) {
        return this.getExpense(expenseIdent);
    }

    private boolean getExpense(String expenseIdent) {
        if (expenseIdent == null) return false;
        Optional<Expense> expense = this.expenseService.getExpense(expenseIdent);
        return expense.isEmpty();
    }
}
