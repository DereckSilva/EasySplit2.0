package com.easy_split.demo.controllers;

import com.easy_split.demo.dtos.requests.expense.AllExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.expense.ExpenseRequestDTO;
import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.mappers.ExpenseMapper;
import com.easy_split.demo.services.ExpenseConfigurationService;
import com.easy_split.demo.services.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    public ExpenseService expenseService;

    @Autowired
    public ExpenseConfigurationService configurationExpenseService;

    @PostMapping("/create")
    public String createExpense(@RequestBody @Valid CreateExpenseRequestDTO expenseRequestDTO){
        CreateExpenseDTO createExpense = this.configurationExpenseService.configExpense(expenseRequestDTO);
        Expense expense = ExpenseMapper.toEntity(createExpense);

        return "testee";
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllExpensesByPayeeId(@RequestBody @Valid AllExpenseRequestDTO payee) {
        Map<String, Object> expenses =  this.expenseService.getAllExpenses(payee.getPayeeId());
        return ResponseEntity.status(HttpStatus.OK).body(expenses);
    }

    @GetMapping("/one")
    public ResponseEntity<Map<String, Object>> getExpenseByPayeeByExpense(@RequestBody @Valid ExpenseRequestDTO expense) {
        Map<String, Object> findExpense = this.expenseService.getExpenseByPayeeId(expense.getPayeeId(), expense.getExpenseId());
        return ResponseEntity.status(HttpStatus.OK).body(findExpense);
    }

}
