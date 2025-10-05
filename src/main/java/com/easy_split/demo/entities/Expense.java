package com.easy_split.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Date maturity;

    @Column(nullable = false)
    private Boolean paid;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    // change here
    private List<String> intermediaries;

    @ManyToOne
    private Person payee;

    @OneToMany(mappedBy = "expense", fetch = FetchType.LAZY)
    private Set<Payments> payments = new HashSet<>();
}
