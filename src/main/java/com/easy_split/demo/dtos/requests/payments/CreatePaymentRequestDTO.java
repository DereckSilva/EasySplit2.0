package com.easy_split.demo.dtos.requests.payments;

import com.easy_split.demo.services.ExpenseService;
import com.easy_split.demo.services.PersonService;
import com.easy_split.demo.services.UserService;
import com.easy_split.demo.validation.FindExpenseValidation;
import com.easy_split.demo.validation.FindPersonValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePaymentRequestDTO {

    private final PersonService personService;
    private final UserService userService;
    private final ExpenseService expenseService;

    private String person;

    private String expense;

    private Boolean isImmediatePayment;

    private Boolean intermediary;

    @NotNull(message = "Total paid is required")
    private Double totalPaid;

    private Integer parcelNumber;

    public CreatePaymentRequestDTO(PersonService personService, UserService userService, ExpenseService expenseService) {
        this.personService = personService;
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @AssertTrue
    public boolean isImmediatePaymentValid() {
        if (isImmediatePayment == null) return false;
        if (isImmediatePayment && (person == null || expense == null)) return false;
        FindPersonValidation findPersonValidation   = new FindPersonValidation(personService,  userService);
        FindExpenseValidation findExpenseValidation = new FindExpenseValidation(expenseService);

        boolean personValid  = findPersonValidation.personIsValid(person);
        boolean expenseValid = findExpenseValidation.expenseIsValid(expense);

        return personValid && expenseValid;
    }
}
