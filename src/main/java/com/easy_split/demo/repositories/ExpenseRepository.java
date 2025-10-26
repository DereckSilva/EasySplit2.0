package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    Expense findByPayeeId(int payeeId);

    Expense findByPayeeIdAndId(int payeeId, int expenseId);

    public Optional<Expense> findByBarcode(String barCode);
}
