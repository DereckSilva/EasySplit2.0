package com.easy_split.demo.dtos.requests.expense;

import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;
import com.easy_split.demo.validation.FindPerson;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateExpenseRequestDTO {


    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name can't be null")
    private String name;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Parcels is required")
    private Integer parcels;

    private Boolean intermediary;

    private LocalDate maturity;

    private Boolean paid;

    @FindPerson
    private Integer payeeId;

    @Valid
    private List<IntermediariesRequestDTO> intermediaries;

    @AssertTrue(message = "People who's shared this expense is required and intermediary needs of value")
    public boolean isIntermediariesValid() {
        if (intermediary == null) return false;
        if (intermediary && intermediaries == null) return false;
        return true;
    }
}
