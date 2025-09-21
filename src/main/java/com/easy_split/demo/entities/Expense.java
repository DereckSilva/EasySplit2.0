package com.easy_split.demo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer parcels;
    private Boolean intermediary;
    private Date maturity;
    private Boolean paid;
    private LocalDateTime datePayment;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private List<String> intermediarys;

    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private Person payee;

}
