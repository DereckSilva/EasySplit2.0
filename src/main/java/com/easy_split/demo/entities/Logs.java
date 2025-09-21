package com.easy_split.demo.entities;

import com.easy_split.demo.enums.Action;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
