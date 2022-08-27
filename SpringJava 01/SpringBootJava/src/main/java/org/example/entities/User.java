package org.example.entities;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String image;
    private String password;
    private String phoneNumber;
    private int age;
}
