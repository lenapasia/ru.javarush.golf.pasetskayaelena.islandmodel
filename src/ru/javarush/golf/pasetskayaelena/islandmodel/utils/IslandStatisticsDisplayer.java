package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.AnimalSymbol;

import java.util.*;

public final class IslandStatisticsDisplayer {

    public void display(Island island) {
        System.out.println("---STATISTICS---");
        int totalPlantsCount = 0;
        Map<AnimalType, Integer> animalTypeToTotalCount = new HashMap();
        for (Location location : island.getLocations()) {
            int plantsCount = 0;
            Map<AnimalType, Integer> animalTypeToCount = new HashMap();
            for (Biota eachBiota : location.getBiotas()) {
                if (eachBiota instanceof Plant) {
                    plantsCount++;
                    totalPlantsCount++;
                } else if (eachBiota instanceof Animal) {
                    Animal eachAnimal = (Animal) eachBiota;

                    Integer animalsCount = animalTypeToCount.get(eachAnimal.getType());
                    if (animalsCount == null) {
                        animalsCount = 0;
                    }
                    animalsCount++;
                    animalTypeToCount.put(eachAnimal.getType(), animalsCount);

                    Integer animalsTotalCount = animalTypeToTotalCount.get(eachAnimal.getType());
                    if (animalsTotalCount == null) {
                        animalsTotalCount = 0;
                    }
                    animalsTotalCount++;
                    animalTypeToTotalCount.put(eachAnimal.getType(), animalsTotalCount);
                }
            }
            System.out.print("[" + location.getX() + "," + location.getY() + "] " + "🌿=" + plantsCount);
            for (Map.Entry<AnimalType, Integer> eachEntry : animalTypeToCount.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).toList()) {
                AnimalType eachAnimalType = eachEntry.getKey();
                int animalsCount = eachEntry.getValue();
                System.out.print(" " + AnimalSymbol.getByType(eachAnimalType) + "=" + animalsCount);
            }
            System.out.println();
        }

        System.out.print("TOTAL: 🌿=" + totalPlantsCount);
        for (Map.Entry<AnimalType, Integer> eachEntry : animalTypeToTotalCount.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).toList()) {
            AnimalType eachAnimalType = eachEntry.getKey();
            int animalsCount = eachEntry.getValue();
            System.out.print(" " + AnimalSymbol.getByType(eachAnimalType) + "=" + animalsCount);
        }
        System.out.println();
    }
}

