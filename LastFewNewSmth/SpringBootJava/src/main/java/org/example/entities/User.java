package org.example.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tblUser")
public class User implements Serializable {
    private static final long serialVersionUID = -5384119134627967830L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String email;
    @Column
    private String image;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private int age;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy="cartItemId.user")
    private Set<CartItem> cart;

    @OneToMany(mappedBy="user")
    private Set<Order> orders;
}
