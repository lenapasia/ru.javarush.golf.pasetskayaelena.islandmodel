package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

public class Boar extends Herbivore {

    public Boar(AnimalConfig animalConfig) {
        super(animalConfig);
    }

    @Override
    public AnimalType getType() {
        return AnimalType.Boar;
    }
}
