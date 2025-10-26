package com.easy_split.demo.services;

import com.easy_split.demo.dtos.requests.expense.CreateExpenseDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesDTO;
import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;
import com.easy_split.demo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseConfigurationService {

    @Autowired
    public PersonService personService;

    @Autowired PaymentService paymentService;

    public CreateExpenseDTO configExpense(CreateExpenseRequestDTO createExpenseRequestDTO) {
        Person person = this.personService.getPersonById(createExpenseRequestDTO.getPayee()).get();
        return new CreateExpenseDTO(
                createExpenseRequestDTO.getName(),
                createExpenseRequestDTO.getPrice(),
                createExpenseRequestDTO.getParcels(),
                createExpenseRequestDTO.getIntermediary(),
                LocalDate.now().plusMonths(createExpenseRequestDTO.getParcels()),
                createExpenseRequestDTO.getPaid() != null,
                person,
                this.findAllIntermediaries(createExpenseRequestDTO)
        );
    }

    private List<IntermediariesDTO> findAllIntermediaries(CreateExpenseRequestDTO createExpenseRequestDTO) {
        Integer totalIntermediaries = createExpenseRequestDTO.getIntermediaries().size();
        return createExpenseRequestDTO.getIntermediaries().stream().map(intermediariesDTO -> new IntermediariesDTO(
                this.personService.getPersonById(intermediariesDTO.getPerson()).get(),
                !intermediariesDTO.priceIsValid() ? createExpenseRequestDTO.getPrice() / totalIntermediaries : intermediariesDTO.getPrice()
        )).toList();
    }

    public


}
