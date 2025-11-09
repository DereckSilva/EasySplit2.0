package com.easy_split.demo.dtos.requests.expense;

import com.easy_split.demo.dtos.requests.payments.CreatePaymentRequestDTO;
import com.easy_split.demo.services.ExpenseService;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import lombok.Data;

@Data
public class UpdateExpenseRequestDTO extends CreatePaymentRequestDTO {

    private final PersonService personService;
    private final UserService userService;
    private final ExpenseService expenseService;

    public UpdateExpenseRequestDTO(PersonService personService, UserService userService, ExpenseService expenseService) {
        super(personService, userService, expenseService);
        this.personService = personService;
        this.userService = userService;
        this.expenseService = expenseService;
    }

}
