package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.AnimalGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

import java.util.Arrays;
import java.util.List;

public final class AnimalLifeCycleProcessor implements Runnable {

    private static final int SATIETY_FOR_REPRODUCTION = 94;
    private final List<Location> locations;
    private final IslandConfig islandConfig;
    public final Island island;

    public AnimalLifeCycleProcessor(List<Location> locations, IslandConfig islandConfig, Island island) {
        this.locations = locations;
        this.islandConfig = islandConfig;
        this.island = island;
    }

    @Override
    public void run() {
        List<String> t = locations.stream().map(location -> "[" + location.getX() + " " + location.getY() + "]").toList();
        String s = Arrays.toString(t.toArray(new String[]{}));
        //System.out.println("Процесс жизненного цикла животных для локаций " + s + " : " + LocalTime.now());
        Thread.currentThread().setName("A " + s);

        for (Location location : locations) {
            processAnimalsOnLocation(location);
        }
    }

    private void processAnimalsOnLocation(Location location) {
        new AnimalsEatingHandler(islandConfig, island).processAnimalsEating(location);

        new AnimalsMotionHandler(islandConfig, island).processAnimalsMotion(location);

        reproduceAnimals(location);
    }

    private void reproduceAnimals(Location location) {
        // животное размножается
        AnimalGenerator animalGenerator = new AnimalGenerator(islandConfig);
        {
            for (AnimalType animalType : AnimalType.values()) {
                int countAnimalsByTypeForReproduce = location.countAnimalsByTypeThatReadyForReproduction(animalType, SATIETY_FOR_REPRODUCTION);
                int countAnimalsByType = location.countAnimalsByType(animalType);
                int pairsAnimalsByType = countAnimalsByTypeForReproduce / 2;
                int babies = pairsAnimalsByType * Randomizer.rnd(1, islandConfig.animalTypeToConfig.get(animalType).maxBabiesQuantity);
                int freeSlots = islandConfig.animalTypeToConfig.get(animalType).maxQuantityAtLocation - countAnimalsByType;
                int newBabies;
                if (babies <= freeSlots) {
                    newBabies = babies;
                } else {
                    newBabies = freeSlots;
                }

                for (int i = 0; i < newBabies; i++) {
                    Animal animal = animalGenerator.createByType(animalType);
                    animal.decreaseSatiety(40);
                    location.addBiota(animal);
                }
            }
        }
    }
}