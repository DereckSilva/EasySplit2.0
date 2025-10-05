package com.easy_split.demo.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExpenseRequestDTO {


    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description can't be null")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Parcels is required")
    private Integer parcels;

    private Boolean intermediary;

    @Valid
    private IntermediariesRequestDTO intermediaries;

    @AssertTrue(message = "People who's shared this expense is required and intermediary needs of value")
    public boolean isIntermediariesValid() {
        if (intermediary == null) return false;
        if (intermediary && intermediaries == null) return false;
        return true;
    }
}
