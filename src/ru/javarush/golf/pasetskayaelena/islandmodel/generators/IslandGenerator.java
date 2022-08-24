package ru.javarush.golf.pasetskayaelena.islandmodel.generators;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;

import java.util.*;

public final class IslandGenerator {

    private final IslandConfig islandConfig;
    private final AnimalGenerator animalGenerator;

    public IslandGenerator(IslandConfig islandConfig) {
        this.islandConfig = islandConfig;
        this.animalGenerator = new AnimalGenerator(islandConfig);
    }

    public Island generate() {
        ArrayList<PreLocation> preLocations = new ArrayList<>();
        for (int i = 0; i < islandConfig.height; i++) {
            for (int j = 0; j < islandConfig.width; j++) {
                preLocations.add(new PreLocation(i, j));
            }
        }

        generatePlants(preLocations);
        generateAnimals(preLocations);

        ArrayList<Location> locations = new ArrayList<>();
        for (PreLocation preLocation : preLocations) {
            Location location = preLocation.generateLocation();
            locations.add(location);
        }

        return new Island(locations);
    }

    private void generatePlants(ArrayList<PreLocation> preLocations) {
        for (int i = 0; i < islandConfig.plantConfig.startQuantity; i++) {
            Plant plant = new Plant(islandConfig.plantConfig);

            ArrayList<PreLocation> availableLocations = new ArrayList<>(preLocations);
            PreLocation randomLocation = null;
            while (randomLocation == null && availableLocations.size() > 0) {
                randomLocation = getRandom(availableLocations);
                int plantsCount = randomLocation.countPlants();
                if (plantsCount >= islandConfig.plantConfig.maxQuantityAtLocation) {
                    availableLocations.remove(randomLocation);
                    randomLocation = null;
                }
            }

            if (randomLocation == null) {
                System.out.println("Has no space for generating plants");
                break;
            } else {
                randomLocation.addPlant(plant);
            }
        }
    }

    private void generateAnimals(ArrayList<PreLocation> preLocations) {
        for (Map.Entry<AnimalType, AnimalConfig> entry : islandConfig.animalTypeToConfig.entrySet()) {
            AnimalType animalType = entry.getKey();
            AnimalConfig animalConfig = entry.getValue();

            for (int i = 0; i < animalConfig.startQuantity; i++) {
                Animal animal = animalGenerator.createByType(animalType);

                ArrayList<PreLocation> availableLocations = new ArrayList<>(preLocations);
                PreLocation randomLocation = null;
                while (randomLocation == null && availableLocations.size() > 0) {
                    randomLocation = getRandom(availableLocations);
                    int animalsCount = randomLocation.countAnimalsByType(animalType);
                    if (animalsCount >= animalConfig.maxQuantityAtLocation) {
                        availableLocations.remove(randomLocation);
                        randomLocation = null;
                    }
                }

                if (randomLocation == null) {
                    System.out.println("Has no space for generating animals of type " + animalType);
                    break;
                } else {
                    randomLocation.addAnimal(animal);
                }
            }
        }
    }

    private <T> T getRandom(ArrayList<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    private final class PreLocation {
        private final int x;
        private final int y;
        private final List<Plant> plants;
        private final Map<AnimalType, List<Animal>> typeToAnimals;

        public PreLocation(int x, int y) {
            this.x = x;
            this.y = y;
            plants = new ArrayList<>();
            typeToAnimals = new HashMap<>();
        }

        public int countPlants() {
            return plants.size();
        }

        public void addPlant(Plant plant) {
            plants.add(plant);
        }

        public int countAnimalsByType(AnimalType animalType) {
            List<Animal> animals = typeToAnimals.get(animalType);
            return animals != null ? animals.size() : 0;
        }

        public void addAnimal(Animal animal) {
            List<Animal> animals = typeToAnimals.get(animal.getType());
            if (animals == null) {
                animals = new ArrayList<>();
                typeToAnimals.put(animal.getType(), animals);
            }
            animals.add(animal);
        }

        public Location generateLocation() {
            List<Biota> biotas = new ArrayList<>();
            biotas.addAll(plants);
            for (Map.Entry<AnimalType, List<Animal>> eachEntry : typeToAnimals.entrySet()) {
                biotas.addAll(eachEntry.getValue());
            }

            return new Location(x, y, biotas);
        }
    }
}