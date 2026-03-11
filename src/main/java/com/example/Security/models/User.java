package com.example.Security.models;

// Code written by Dimitri Liubomudrov

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"user\"")
public class User {

    @Setter
    @Getter
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotEmpty
    @Column(name = "username")
    @Size(min = 2, max = 50, message = "Name too short or too long")
    private String username;

    @Setter
    @Getter
    @NotEmpty
    @Column(name = "email")
    @Email
    private String email;


    @Setter
    @Getter
    @Column(name = "password")
    private String password;

    public User() {}

    public User(Long id, String username, String email, String password) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public User(String username, String email) {

        this.username = username;
        this.email = email;

    }


    public User(Long id, String username, String email) {

        this.id = id;
        this.username = username;
        this.email = email;
    }
}
