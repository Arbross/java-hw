package org.example.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tblCartItem")
public class CartItem {
    public CartItem() {}

    public CartItem(int count) {
        this.count = count;
    }

    @EmbeddedId
    private CartItemId cartItemId;

    @Column
    private int count;
}
