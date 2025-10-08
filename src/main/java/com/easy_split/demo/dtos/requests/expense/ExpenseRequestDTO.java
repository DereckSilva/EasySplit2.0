package com.easy_split.demo.dtos.requests.expense;

import com.easy_split.demo.validation.FindExpense;
import com.easy_split.demo.validation.FindPerson;
import lombok.Data;

@Data
public class ExpenseRequestDTO {

    @FindPerson
    private Integer payeeId;

    @FindExpense
    private Integer expenseId;
}
