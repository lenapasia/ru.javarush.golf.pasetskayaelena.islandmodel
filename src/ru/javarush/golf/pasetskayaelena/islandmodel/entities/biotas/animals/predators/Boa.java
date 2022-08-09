package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Boa extends Predator {

    public Boa(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Boa;
    }
}
