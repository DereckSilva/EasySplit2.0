package com.easy_split.demo.services;

import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        expense.setPayee(expense.getPayee());
        return this.expenseRepository.save(expense);
    }

    public Optional<Expense> getExpense(String identifier) {
        if (identifier.matches("\\d+")) return this.expenseRepository.findById(Integer.parseInt(identifier));
        return this.expenseRepository.findByBarcode(identifier);
    }

    public Map<String, Object> getAllExpenses(int personId) {
        Expense expenses = this.expenseRepository.findByPayeeId(personId);

        Map<String, Object> objExpense = new HashMap<>();
        objExpense.put("expenses", expenses);
        objExpense.put("countPayments", expenses.getPayments().stream().toList().size());
        return objExpense;
    }

    public Map<String, Object> getExpenseByPayeeId(int payeeId, int expenseId) {
        Expense expense = this.expenseRepository.findByPayeeIdAndId(payeeId, expenseId);

        Map<String, Object> objExpense = new HashMap<>();
        objExpense.put("expense", expense);
        objExpense.put("payments",  expense.getPayments());
        return objExpense;
    }
}
