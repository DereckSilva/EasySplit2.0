package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payments, Integer> {

    Optional<List<Payments>> findByPaid(Boolean paid);
}
