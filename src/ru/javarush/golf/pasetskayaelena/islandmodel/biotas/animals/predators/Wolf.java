package ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

public class Wolf extends Predator {

    @Override
    public AnimalType getType() {
        return AnimalType.Wolf;
    }
}
