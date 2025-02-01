package com.example.demo.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer user_id;

    @NotBlank(message = "Username not provided.")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer skillpoints = 0;

}