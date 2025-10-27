package com.easy_split.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id")
    private Expense expense;

    private Boolean intermediary;

    private LocalDateTime datePayment;

    @Column(nullable = false, scale = 2)
    private Double totalPaid;

    private Integer numberParcel;

    private Boolean paid;

    public Payments(Person person, Expense expense, Boolean intermediary, LocalDateTime datePayment, Double totalPaid, Integer numberParcel,  Boolean paid) {
        this.person = person;
        this.expense = expense;
        this.intermediary = intermediary;
        this.datePayment = datePayment;
        this.totalPaid = totalPaid;
        this.numberParcel = numberParcel;
        this.paid = paid;
    }
}
