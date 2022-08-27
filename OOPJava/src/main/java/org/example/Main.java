package org.example;

import entities.Animal;
import entities.Cat;
import entities.Dog;
import interfaces.IAnimal;
import services.AnimalFactory;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Scanner in = new Scanner(System.in, "UTF-8");

        // Declaring classes
        Animal animal = new Animal(1, "C#");
        IAnimal dog = new Dog(1, "Dog");
        IAnimal cat = new Cat(1, "Cat");

        // Encapsulation usage : setters & getters

        // Invoking inherited classes and using polymorphism
        List<IAnimal> animals = Arrays.asList(animal, dog, cat);
        for (var item : animals) {
            item.say();
        }

        // Dependency injection
        IAnimal animalT = AnimalFactory.buildAnimal();
        animalT.say();
    }
}