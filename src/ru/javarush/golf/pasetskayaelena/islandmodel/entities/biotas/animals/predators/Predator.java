package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;

public abstract class Predator extends Animal {
    public Predator(AnimalConfig animalConfig) {
        super(animalConfig);
    }
}