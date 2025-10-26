package com.easy_split.demo.dtos.requests.payments;

import com.easy_split.demo.validation.FindExpense;
import com.easy_split.demo.validation.FindPerson;
import com.easy_split.demo.validation.FindPersonValidation;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePaymentRequestDTO {

    private String person;

    private Integer expense_id;

    private Boolean isImmediatePayment;

    private Boolean intermediary;

    @NotNull(message = "Total paid is required")
    private Double totalPaid;

    private Integer parcelNumber;

    @AssertTrue
    public boolean isImmediatePaymentValid() {
        if (isImmediatePayment == null) return false;
        if (isImmediatePayment && (person == null || expense_id == null)) return false;
        FindPersonValidation findPersonValidation = new FindPersonValidation();
        return findPersonValidation.personIsValid(person);
    }
}
