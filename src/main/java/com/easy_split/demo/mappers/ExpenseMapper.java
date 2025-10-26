package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.expense.CreateExpenseDTO;
import com.easy_split.demo.dtos.response.ExpenseResponseDTO;
import com.easy_split.demo.entities.Expense;

public class ExpenseMapper {

    public static Expense toEntity(CreateExpenseDTO expenseRequestDTO) {
        return new Expense(
                expenseRequestDTO.name(),
                expenseRequestDTO.price(),
                expenseRequestDTO.parcels(),
                expenseRequestDTO.intermediary(),
                expenseRequestDTO.maturity(),
                expenseRequestDTO.paid(),
                expenseRequestDTO.payee()
        );
    }

    public static ExpenseResponseDTO toDTO(Expense expense) {
        return new  ExpenseResponseDTO(
                expense.getName(),
                expense.getPrice(),
                expense.getParcels(),
                expense.getIntermediary(),
                expense.getMaturity(),
                expense.getPaid(),
                expense.getPayee(),
                expense.getIntermediaries(),
                expense.getPayments().stream().toList()
        );
    }

}
