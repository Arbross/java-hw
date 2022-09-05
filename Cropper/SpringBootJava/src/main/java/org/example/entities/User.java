package org.example.entities;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {
    private int id;
    @Size(min=8, max=50)
    @NotBlank(message = "Email can't be empty.")
    private String email;
    private String image;
    @Size(min=8, max=50)
    @NotBlank(message = "Password can't be empty.")
    private String password;
    @Size(min=8, max=15)
    @NotBlank(message = "Phone Number can't be empty.")
    private String phoneNumber;
    @Min(18)
    private int age;
}
