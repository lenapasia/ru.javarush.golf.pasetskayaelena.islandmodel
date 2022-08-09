package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Eagle extends Predator {
    public Eagle(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Eagle;
    }
}
