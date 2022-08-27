package services;

import interfaces.IAnimal;

public class AnimalFactory {
    public static IAnimal buildAnimal() {
        return new DefaultAnimalImpl(new DefaultAnimalClothesImpl());
    }
}
