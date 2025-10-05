package com.easy_split.demo.repositories;

import com.easy_split.demo.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Expense, Integer> {
}
