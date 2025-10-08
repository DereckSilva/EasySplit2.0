package com.easy_split.demo.dtos.requests.expense;

import com.easy_split.demo.validation.FindPerson;
import lombok.Data;

@Data
public class AllExpenseRequestDTO {

    @FindPerson
    private Integer payeeId;
}
