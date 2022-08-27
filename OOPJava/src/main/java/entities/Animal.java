package entities;

import interfaces.IAnimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Animal implements IAnimal {
    public Animal(int id, String name)
    {
        this.setId(id);
        this.setName(name);
    }

    private int id;
    private String name;

    public void say()
    {
        System.out.println("I am animal!");
    }
    @Override
    public String toString()
    {
        return "Hello, I am " + name + " with id - '" + id + "'!";
    }
}
