package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Wolf extends Predator {

    public Wolf(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Wolf;
    }
}
