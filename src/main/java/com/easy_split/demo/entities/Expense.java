package com.easy_split.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, scale = 2)
    private Double price;

    @Column(nullable = false)
    private Integer parcels;

    @Column(nullable = false)
    private Boolean intermediary;

    @Column(nullable = false)
    private Date maturity;

    @Column(nullable = false)
    private Boolean paid;

    @Column(nullable = false)
    private LocalDateTime datePayment;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    private List<String> intermediarys;

    @ManyToOne
    private Person payee;

}
