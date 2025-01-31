package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer skillpoints = 0;

}