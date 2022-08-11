package ru.javarush.golf.pasetskayaelena.islandmodel.generators;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores.*;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators.*;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IslandGenerator {

    public Island generate(IslandConfig islandConfig) {
        AnimalGenerator animalGenerator = new AnimalGenerator(islandConfig);

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
                    biotas.add(new Plant(islandConfig.plantConfig));
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

                        biotas.add(animalGenerator.createByType(eachAnimalType));

                    }
                }
                locations.add(new Location(i, j, biotas));
            }
        }

        return new Island(locations);
    }


}
