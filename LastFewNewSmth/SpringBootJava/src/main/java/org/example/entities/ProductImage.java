package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblProductImage")
public class ProductImage {
    public ProductImage() {}
    public ProductImage(String name, int priority, Product product) {
        this.name = name;
        this.priority = priority;
        this.product = product;
    }
    public ProductImage(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int priority;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
}
