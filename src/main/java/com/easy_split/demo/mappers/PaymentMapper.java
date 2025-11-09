package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.payments.CreatePaymentRequestDTO;
import com.easy_split.demo.entities.Payments;

public class PaymentMapper {

    private PaymentMapper() {
        throw new IllegalStateException("Classe utilitária, não pode ser instanciada.");
    }

    public static Payments toEntity(CreatePaymentRequestDTO createPaymentRequestDTO) {
        return new Payments();
    }
}
