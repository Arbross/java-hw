package org.example.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "tblOrder")
public class Order {
    public Order() {}

    public Order(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;

    @OneToMany(mappedBy="order")
    private Set<OrderStatus> orderStatuses;

    @OneToMany(mappedBy="order")
    private Set<OrderItem> orderItems;
}
