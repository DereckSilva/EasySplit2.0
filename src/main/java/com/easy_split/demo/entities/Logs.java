package com.easy_split.demo.entities;

import com.easy_split.demo.enums.Action;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @CreationTimestamp
    private LocalDateTime created_at;
    private Action action;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
