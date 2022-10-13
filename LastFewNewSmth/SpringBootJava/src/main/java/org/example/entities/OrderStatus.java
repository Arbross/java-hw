package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tblOrderStatus")
public class OrderStatus {
    public OrderStatus() {}

    public OrderStatus(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = true)
    private Order order;
}
