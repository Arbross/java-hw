package entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {
    public Category(String name, String image)
    {
        this.setName(name);
        this.setImage(image);
    }
    public Category(int id, String name, String image)
    {
        this.setId(id);
        this.setName(name);
        this.setImage(image);
    }
    public Category() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 5000, nullable = false)
    private String image;
}
