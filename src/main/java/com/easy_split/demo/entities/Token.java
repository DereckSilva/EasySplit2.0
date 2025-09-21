package com.easy_split.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String token;
    private LocalDateTime expiration;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
