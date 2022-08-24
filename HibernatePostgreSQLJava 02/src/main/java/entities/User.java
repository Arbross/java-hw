package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tblUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 45, nullable = false)
    private String UserName;
    @Column(length = 55, nullable = false)
    private String Email;
    @Column(length = 30, nullable = false)
    private String PasswordHash;

    // NAVIGATION PROPERTIES

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "tblUserRole",
            joinColumns = { @JoinColumn(name = "roleId") },
            inverseJoinColumns = { @JoinColumn(name = "userId") }
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;
}
