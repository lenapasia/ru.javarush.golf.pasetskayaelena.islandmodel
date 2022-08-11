package ru.javarush.golf.pasetskayaelena.islandmodel.generators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores.*;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators.*;

public final class AnimalGenerator {

    private final IslandConfig islandConfig;

    public AnimalGenerator(IslandConfig islandConfig) {
        this.islandConfig = islandConfig;
    }

    public Animal createByType(AnimalType animalType) {
        AnimalConfig animalConfig = islandConfig.animalTypeToConfig.get(animalType);
        switch (animalType) {
            case Wolf -> {return new Wolf(animalConfig);}
            case Boa -> {return new Boa(animalConfig);}
            case Fox -> {return new Fox(animalConfig);}
            case Bear -> {return new Bear(animalConfig);}
            case Eagle -> {return new Eagle(animalConfig);}
            case Horse -> {return new Horse(animalConfig);}
            case Deer -> {return new Deer(animalConfig);}
            case Rabbit -> {return new Rabbit(animalConfig);}
            case Mouse -> {return new Mouse(animalConfig);}
            case Goat -> {return new Goat(animalConfig);}
            case Sheep -> {return new Sheep(animalConfig);}
            case Boar -> {return new Boar(animalConfig);}
            case Buffalo -> {return new Buffalo(animalConfig);}
            case Duck -> {return new Duck(animalConfig);}
            case Caterpillar -> {return new Caterpillar(animalConfig);}
            default -> throw new RuntimeException();
        }
    }
}
