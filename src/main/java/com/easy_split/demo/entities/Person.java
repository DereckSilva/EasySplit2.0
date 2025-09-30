package com.easy_split.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthdate;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Person(String name, LocalDateTime birthDate) {
        this.name = name;
        this.birthdate = birthDate;
    }

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "payee", fetch = FetchType.LAZY)
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private Set<Payments> payments = new HashSet<>();

}
