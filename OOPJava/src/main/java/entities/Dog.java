package entities;

import lombok.Data;

@Data
public class Dog extends Animal {
    public Dog(int id, String name) {
        super(id, name);
    }

    @Override
    public void say()
    {
        System.out.println("I am dog!");
    }
}
