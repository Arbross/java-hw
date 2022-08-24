package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tblRoles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 35, nullable = false)
    private String name;

    // NAVIGATION PROPERTIES

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
