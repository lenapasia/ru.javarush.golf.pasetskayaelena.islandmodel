package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(AnimalConfig animalConfig) {
        super(animalConfig);
    }
}
