package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    Expense findByExpenseByPayeeId(int payeeId);

    Expense findByPayeeIdAndExpenseId(int payeeId, int expenseId);
}
