package com.easy_split.demo.mappers;

import com.easy_split.demo.dtos.requests.payments.CreatePaymentRequestDTO;
import com.easy_split.demo.entities.Payments;

public class PaymentMapper {

    public static Payments toEntity(CreatePaymentRequestDTO createPaymentRequestDTO) {
        return new Payments();
    }
}
