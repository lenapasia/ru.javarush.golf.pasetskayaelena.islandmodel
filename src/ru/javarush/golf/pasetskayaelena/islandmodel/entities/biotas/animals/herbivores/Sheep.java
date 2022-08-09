package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Sheep extends Herbivore {

    public Sheep(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Sheep;
    }
}
