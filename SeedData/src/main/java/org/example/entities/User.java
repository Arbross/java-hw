package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    // @Size(min=8, max=50)
    // @NotBlank(message = "Email can't be empty.")
    private String email;
    @Column
    private String image;
    @Column
    // @Size(min=8, max=50)
    @NotBlank(message = "Password can't be empty.")
    private String password;
    @Column
    // @NotBlank(message = "Phone Number can't be empty.")
    private String phoneNumber;
    @Column
    // @Min(18)
    private int age;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles = new ArrayList<>();
}
