package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name="tblProduct")
public class Product implements Serializable {
    public Product() {}

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String description;

    @OneToMany(mappedBy="cartItemId.product")
    private Set<CartItem> items;

    @OneToMany(mappedBy="product")
    private Set<ProductImage> images;

    @OneToMany(mappedBy="product")
    private Set<OrderItem> orderItems;
}
