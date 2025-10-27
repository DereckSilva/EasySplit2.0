package com.easy_split.demo.services;

import com.easy_split.demo.entities.Expense;
import com.easy_split.demo.entities.Payments;
import com.easy_split.demo.entities.Person;
import com.easy_split.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    public PaymentRepository paymentRepository;

    public Payments createPayment(Person person, Expense expense, Payments payments) {
        payments.setExpense(expense);
        payments.setPerson(person);
        return this.paymentRepository.save(payments);
    }

    public Optional<List<Payments>> findByPaid(Boolean paid) {
        return this.paymentRepository.findByPaid(paid);
    }
}
