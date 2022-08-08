package ru.javarush.golf.pasetskayaelena.islandmodel.space;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.herbivores.*;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.predators.*;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.AnimalSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IslandGenerator {

    public Island generate(IslandConfig islandConfig) {
        ArrayList<Location> locations = new ArrayList<>();
        //сгенерировать локации согласно конфигурации

        int remainingPlants = islandConfig.plantConfig.startQuantity;
        Map<AnimalType, Integer> animalTypeToRemainingCount = new HashMap<>();
        for (Map.Entry<AnimalType, AnimalConfig> eachEntry : islandConfig.animalTypeToConfig.entrySet()) {
            AnimalType eachAnimalType = eachEntry.getKey();
            AnimalConfig eachAnimalConfig = eachEntry.getValue();
            animalTypeToRemainingCount.put(eachAnimalType, eachAnimalConfig.startQuantity);
        }

        for (int i = 0; i < islandConfig.height; i++) {
            for (int j = 0; j < islandConfig.width; j++) {
                int plantsCount = 0;
                if (remainingPlants > 0) {
                    int tmpPlants = remainingPlants - islandConfig.plantConfig.maxQuantityAtLocation;
                    if (tmpPlants >= 0) {
                        plantsCount = islandConfig.plantConfig.maxQuantityAtLocation;
                    } else {
                        plantsCount = remainingPlants;
                    }
                    remainingPlants = tmpPlants;
                }

                ArrayList<Biota> biotas = new ArrayList<>();
                for (int k = 0; k < plantsCount; k++) {
                    biotas.add(new Plant());
                }

                for (Map.Entry<AnimalType, AnimalConfig> eachEntry : islandConfig.animalTypeToConfig.entrySet()) {
                    AnimalType eachAnimalType = eachEntry.getKey();
                    AnimalConfig eachAnimalConfig = eachEntry.getValue();
                    int remainingAnimals = animalTypeToRemainingCount.get(eachAnimalType);
                    int animalsCount = 0;
                    if (remainingAnimals > 0) {
                        int tmpAnimalsCount = remainingAnimals - eachAnimalConfig.maxQuantityAtLocation;
                        if (tmpAnimalsCount >= 0) {
                            animalsCount = eachAnimalConfig.maxQuantityAtLocation;
                        } else {
                            animalsCount = remainingAnimals;
                        }
                        animalTypeToRemainingCount.put(eachAnimalType, tmpAnimalsCount);
                    }
//                    System.out.println("[" + i + "," + j + "] " + "🌿=" + plantsCount + " " + AnimalSymbol.getByType(eachAnimalType) + "=" + animalsCount);

                    for (int n = 0; n < animalsCount; n++) {

                        biotas.add(createByType(eachAnimalType));

                    }

                }

                locations.add(new Location(i, j, biotas));
            }
        }

        return new Island(locations);
    }

    private Animal createByType(AnimalType animalType) {
        switch (animalType) {
            case Wolf -> {return (Animal) new Wolf();}
            case Boa -> {return (Animal) new Boa();}
            case Fox -> {return (Animal) new Fox();}
            case Bear -> {return (Animal) new Bear();}
            case Eagle -> {return (Animal) new Eagle();}
            case Horse -> {return (Animal) new Horse();}
            case Deer -> {return (Animal) new Deer();}
            case Rabbit -> {return (Animal) new Rabbit();}
            case Mouse -> {return (Animal) new Mouse();}
            case Goat -> {return (Animal) new Goat();}
            case Sheep -> {return (Animal) new Sheep();}
            case Boar -> {return (Animal) new Boar();}
            case Buffalo -> {return (Animal) new Buffalo();}
            case Duck -> {return (Animal) new Duck();}
            case Caterpillar -> {return (Animal) new Caterpillar();}
            default -> throw new RuntimeException();
        }
    }
}
