package com.easy_split.demo.entities;

import com.easy_split.demo.dtos.requests.intermediaries.IntermediariesDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, scale = 2)
    private Double price;

    @Column(nullable = false)
    private Integer parcels;

    @Column(nullable = false)
    private Boolean intermediary;

    @Column(nullable = false)
    private LocalDate maturity;

    @Column(nullable = false)
    private Boolean paid;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private List<IntermediariesDTO> intermediaries;

    @ManyToOne
    private Person payee;

    @OneToMany(mappedBy = "expense", fetch = FetchType.LAZY)
    private Set<Payments> payments = new HashSet<>();


    public Expense(String name, Double price, Integer parcels, Boolean intermediary, LocalDate maturity, Boolean paid, Person payee) {
        this.name = name;
        this.price = price;
        this.parcels = parcels;
        this.intermediary = intermediary;
        this.maturity = maturity;
        this.paid = paid;
        this.payee = payee;
    }
}
