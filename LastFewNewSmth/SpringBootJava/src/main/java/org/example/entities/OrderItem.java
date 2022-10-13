package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblOrderItem")
public class OrderItem {
    public OrderItem() {}

    public OrderItem(int price, int count) {
        this.price = price;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int price;
    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = true)
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = true)
    private Order order;
}
