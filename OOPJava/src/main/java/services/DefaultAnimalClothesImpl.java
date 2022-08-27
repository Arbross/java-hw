package services;

import interfaces.IAnimal;
import interfaces.IAnimalClothes;
import lombok.Data;

@Data
public class DefaultAnimalClothesImpl implements IAnimalClothes {
    public String putOn()
    {
        return "Put On";
    }
}
