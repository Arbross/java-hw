package services;

import interfaces.IAnimal;
import interfaces.IAnimalClothes;

public class DefaultAnimalImpl implements IAnimal {
    private IAnimalClothes animalClothes;
    public DefaultAnimalImpl(final IAnimalClothes animalClothesImpl) {
        animalClothes = animalClothesImpl;
    }

    public void say()
    {
        System.out.println("Say, i am " + animalClothes.putOn() + " this clothes!");
    }
}
