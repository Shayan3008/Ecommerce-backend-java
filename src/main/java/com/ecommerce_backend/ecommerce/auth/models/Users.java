package com.ecommerce_backend.ecommerce.auth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS",schema = "ECOMMERCE")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USER_NAME")
    private String name;
    private String email;

    @Column(name = "HASHED_PASSWORD")
    private String hashedPassword;
}
