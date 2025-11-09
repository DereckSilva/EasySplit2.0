package com.easy_split.demo.services;

import com.easy_split.demo.dtos.requests.expense.CreateExpenseDTO;
import com.easy_split.demo.dtos.requests.expense.CreateExpenseRequestDTO;
import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesDTO;
import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesRequestDTO;
import com.easy_split.demo.dtos.requests.payments.CreatePaymentRequestDTO;
import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.enums.BankCode;
import com.easy_split.demo.mappers.ExpenseMapper;
import com.easy_split.demo.mappers.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Service
public class ExpenseProcessingService {

    private final PersonService personService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Autowired
    public ExpenseProcessingService(PersonService personService, PaymentService paymentService, UserService userService) {
        this.personService = personService;
        this.paymentService = paymentService;
        this.userService = userService;
    }


    public CreateExpenseDTO configExpense(CreateExpenseRequestDTO createExpenseRequestDTO) {
        Person person = this.getPersonRelatedExpense(createExpenseRequestDTO.getPayee());
        return new CreateExpenseDTO(
                createExpenseRequestDTO.getName(),
                createExpenseRequestDTO.getPrice(),
                createExpenseRequestDTO.getParcels(),
                createExpenseRequestDTO.getIntermediary(),
                LocalDate.now().plusMonths(createExpenseRequestDTO.getParcels()),
                createExpenseRequestDTO.getPaid() != null,
                person,
                this.findAllIntermediaries(createExpenseRequestDTO),
                createExpenseRequestDTO.getBarCode()
        );
    }

    private List<IntermediariesRequestDTO> findAllIntermediaries(CreateExpenseRequestDTO createExpenseRequestDTO) {
        Integer totalIntermediaries = createExpenseRequestDTO.getIntermediaries().size();

        return createExpenseRequestDTO.getIntermediaries().stream().map(intermediariesDTO -> new IntermediariesRequestDTO(
                this.getPersonRelatedExpense(intermediariesDTO.getPerson()),
                !intermediariesDTO.priceIsValid() ? createExpenseRequestDTO.getPrice() / totalIntermediaries : intermediariesDTO.getPrice()
        )).toList();
    }


    public Payments configPayment(Person person, Expense expense, CreatePaymentRequestDTO createPaymentRequestDTO) {
        Payments payment = PaymentMapper.toEntity(createPaymentRequestDTO);
        return this.paymentService.createPayment(person, expense, payment);
    }

    public Person getPersonRelatedExpense(String person) {
        if (person.matches("\\d+")) return this.personService.getPersonById(Integer.parseInt(person)).get();
        return this.userService.getUserEmail(person).get().getPerson();
    }

    public boolean barCodeIsValid(String barCode, LocalDate maturity, Double price) {

        // realiza validacao para o banco de emissão do documento
        String bankCod = barCode.substring(0, 2);
        String code = Arrays.stream(BankCode.values()).filter(bankCode -> bankCode.toString().equals(bankCod)).toString();
        if (code.isEmpty()) return false;

        // validação para o tipo de moeda
        String coin = barCode.substring(3, 3);
        if (Integer.parseInt(coin) != 9) return false;

        // realiza validacao para o fator vencimento
        LocalDate date = LocalDate.of(2025, 2, 21);
        Long days = date.until(maturity, ChronoUnit.DAYS);
        String daysMaturity = barCode.substring(33, 36);
        if (days.intValue() != (Integer.parseInt(daysMaturity) - 1)) return false;

        // realiza validação para o valor do boleto
        String priceBarCode = barCode.substring(37, 46);
        String priceS = String.valueOf(price);
        return Integer.parseInt(priceBarCode) != Integer.parseInt(priceS.replace(",|.", ""));

    }

    private boolean grouDigitIsValid(String barCode) {

        //validar primeiro grupo

        // validar segundo grupo

        // validar terceiro grupo

        return true;
    }


}
