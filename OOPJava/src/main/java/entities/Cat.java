package entities;

import lombok.Data;

@Data
public class Cat extends Animal {
    public Cat(int id, String name) {
        super(id, name);
    }

    @Override
    public void say()
    {
        System.out.println("I am dog!");
    }
}
