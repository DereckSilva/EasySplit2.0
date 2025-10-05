package com.easy_split.demo.services;

import com.easy_split.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;
}
