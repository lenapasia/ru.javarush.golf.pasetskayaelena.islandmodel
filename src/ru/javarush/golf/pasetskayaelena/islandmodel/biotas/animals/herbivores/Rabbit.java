package ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.herbivores;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

public class Rabbit extends Herbivore {

    @Override
    public AnimalType getType() {
        return AnimalType.Rabbit;
    }
}