package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tblProducts")
public class Product {
    public Product() {}
    public Product(int id, String name, double price, String description, Category category) {
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
    }
    public Product(String name, double price, String description, Category category) {
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 35, nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(length = 200)
    private String description;

    // NAVIGATION PROPERTIES

    @ManyToOne()
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;
}
