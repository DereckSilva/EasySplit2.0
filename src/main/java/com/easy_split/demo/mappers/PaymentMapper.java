package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.payments.CreatePaymentRequestDTO;
import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import com.easy_split.demo.entities.Person;

import java.time.LocalDateTime;

public class PaymentMapper {

    private PaymentMapper() {
        throw new IllegalStateException("Classe utilitária, não pode ser instanciada.");
    }

    public static Payments toEntity(Person person, Expense expense, CreatePaymentRequestDTO createPaymentRequestDTO) {
        return new Payments(person,
                expense,
                createPaymentRequestDTO.getIntermediary(),
                LocalDateTime.now(),
                createPaymentRequestDTO.getTotalPaid(),
                createPaymentRequestDTO.getParcelNumber(),
                createPaymentRequestDTO.isImmediatePaymentValid());
    }
}
