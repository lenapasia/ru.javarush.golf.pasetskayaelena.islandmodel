package ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

public class Bear extends Predator {
    @Override
    public AnimalType getType() {
        return AnimalType.Bear;
    }
}
