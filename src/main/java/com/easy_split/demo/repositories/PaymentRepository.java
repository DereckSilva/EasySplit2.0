package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payments, Integer> {
}
