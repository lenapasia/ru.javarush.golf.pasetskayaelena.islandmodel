package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores.Herbivore;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Coordinates;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.generators.AnimalGenerator;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.DirectionUtils;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AnimalLifeCycleProcessor implements Runnable {
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
            DirectionType[] availableDirections = DirectionUtils.getAvailableDirections(location.getX(), location.getY(),
                    islandConfig.width, islandConfig.height);

            List<Animal> animalsToDelete = new ArrayList<>();
            List<Plant> plantsToDelete = new ArrayList<>();
            List<Plant> plants = location.getAllPlants();

            for (Biota eachBiota : location.getBiotas()) {
                if (eachBiota instanceof Animal) {
                    Animal eachAnimal = (Animal) eachBiota;

                    if (eachAnimal instanceof Herbivore) {
                        herbivoreEat(plantsToDelete, plants, eachAnimal);
                    }

                    //TODO добавить логику для хищников есть других животных

                    // Уменьшаем насыщение и убиваем животное, если насыщение = 0
                    boolean alive = eachAnimal.decreaseSatiety();
                    if (alive == false) {
                        animalsToDelete.add(eachAnimal);
                    } else {
                        moveAnimal(location, availableDirections, eachAnimal);
                    }
                }
            }
            location.removeBiotas(animalsToDelete);
            location.removeBiotas(plantsToDelete);

            reproduceAnimals(location);
        }
    }

    private void herbivoreEat(List<Plant> plantsToDelete, List<Plant> plants, Animal eachAnimal) {
        // Травоядные едят растения
        if (plants.size() > 0) {
            int requiredFoodInKgForFullSatiety = eachAnimal.getRequiredFoodForFullSatiety();
            if (requiredFoodInKgForFullSatiety > 0) {
                int requiredPlantsCount = (int) (requiredFoodInKgForFullSatiety / islandConfig.plantConfig.weight);
                int toIndex = requiredPlantsCount >= plants.size() ? plants.size() : requiredPlantsCount;
                List<Plant> requiredPlants = plants.subList(0, toIndex);
                for (Plant requiredPlant : requiredPlants) {
                    eachAnimal.eat(requiredPlant);
                    plantsToDelete.add(requiredPlant);
                }
                plants.removeAll(requiredPlants);
            }
        }

        //TODO добавить логику для некоторых травоядных есть не только растения
    }

    private void moveAnimal(Location location, DirectionType[] availableDirections, Animal eachAnimal) {
        // перемещение животных
        boolean needToMove = Randomizer.rnd(0, 1) == 1;
        if (needToMove) {
            int randomMoveSpeed = eachAnimal.chooseMoveSpeed();
            DirectionType randomMoveDirection = Animal.chooseMoveDirection(availableDirections);
            Coordinates coordinates = DirectionUtils.calculateCoordinates(
                    location.getX(), location.getY(), randomMoveDirection, randomMoveSpeed, islandConfig.width, islandConfig.height);
            Location newLocation = island.findLocation(coordinates.getX(), coordinates.getY());
            int countAnimalsByType = location.countAnimalsByType(eachAnimal.getType());
            int freeSlots = islandConfig.animalTypeToConfig.get(eachAnimal.getType()).maxQuantityAtLocation - countAnimalsByType;
            if (freeSlots > 0) {
                location.removeBiota(eachAnimal);
                newLocation.addBiota(eachAnimal);
            }
        }
    }

    private void reproduceAnimals(Location location) {
        // животное размножается
        AnimalGenerator animalGenerator = new AnimalGenerator(islandConfig);
        for (AnimalType animalType : AnimalType.values()) {
            int countAnimalsByType = location.countAnimalsByTypeThatReadyForReproduction(animalType);
            int pairsAnimalsByType = countAnimalsByType / 2;
            int babies = pairsAnimalsByType * islandConfig.animalTypeToConfig.get(animalType).maxBabiesQuantity;
            int freeSlots = islandConfig.animalTypeToConfig.get(animalType).maxQuantityAtLocation - countAnimalsByType;
            int newBabies = 0;
            if (babies <= freeSlots) {
                newBabies = babies;
            } else {
                newBabies = freeSlots;
            }
            for (int i = 0; i < newBabies; i++) {
                Animal animal = animalGenerator.createByType(animalType);
                location.addBiota(animal);
            }
        }
    }

}
