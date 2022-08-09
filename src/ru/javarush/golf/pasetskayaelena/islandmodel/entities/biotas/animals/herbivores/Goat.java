package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Goat extends Herbivore {

    public Goat(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Goat;
    }
}
