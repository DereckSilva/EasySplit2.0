package com.easy_split.demo.dtos.requests.payments;

import com.easy_split.demo.validation.FindExpense;
import com.easy_split.demo.validation.FindPerson;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePaymentRequestDTO {

    @FindPerson
    private Integer person_id;

    @FindExpense
    private Integer expense_id;

    private Boolean intermediary;

    @NotNull(message = "Total paid is required")
    private Double totalPaid;

    private Integer parcelNumber;
}
