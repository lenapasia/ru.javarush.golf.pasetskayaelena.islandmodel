package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores.Herbivore;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.predators.Predator;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class AnimalsEatingHandler {

    public static final int SATIETY_BORDER = 65;

    private final IslandConfig islandConfig;
    public final Island island;

    public AnimalsEatingHandler(IslandConfig islandConfig, Island island) {
        this.islandConfig = islandConfig;
        this.island = island;
    }

    public void processAnimalsEating(Location location) {
        List<Animal> animals = location.getAllAnimals();
        List<Animal> animalsToDelete = new ArrayList<>();
        List<Plant> plants = location.getAllPlants();
        List<Plant> plantsToDelete = new ArrayList<>();

        for (Biota eachBiota : location.getBiotas()) {
            if (eachBiota instanceof Animal) {
                Animal eachAnimal = (Animal) eachBiota;
                if (!animalsToDelete.contains(eachAnimal)) {
                    // если животное голодное
                    if (isHungry(eachAnimal)) {
                        // если травоядное
                        if (eachAnimal instanceof Herbivore) {
                            if (eachAnimal.canEatOtherAnimals()) {
                                if (plants.size() == 0 || Randomizer.rnd(0, 1) == 1) {
                                    predatorEat(animalsToDelete, animals, eachAnimal);
                                } else {
                                    herbivoreEat(plantsToDelete, plants, eachAnimal);
                                }
                            } else {
                                herbivoreEat(plantsToDelete, plants, eachAnimal);
                            }
                        } else if (eachAnimal instanceof Predator) {
                            // если хищник
                            predatorEat(animalsToDelete, animals, eachAnimal);
                        }
                    }

                    // Уменьшаем насыщение и животное умирает, если насыщение = 0
                    boolean alive = eachAnimal.decreaseSatiety();
                    if (!alive) {
                        animalsToDelete.add(eachAnimal);
                    }
                }
            }
        }
        location.removeBiotas(animalsToDelete);
        location.removeBiotas(plantsToDelete);
    }

    private boolean isHungry(Animal animal) {
        return animal.getSatiety() < SATIETY_BORDER;
    }

    private void herbivoreEat(List<Plant> plantsToDelete, List<Plant> plants, Animal animal) {
        // Травоядные едят растения
        if (plants.size() > 0) {
            // считаем сколько килограмм пищи необходимо для полного насыщения
            int requiredFoodInKgForFullSatiety = animal.getRequiredFoodForFullSatiety();
            if (requiredFoodInKgForFullSatiety > 0) {
                // считаем сколько растений необходимо для полного насыщения
                int requiredPlantsCount = (int) (requiredFoodInKgForFullSatiety / islandConfig.plantConfig.weight);
                // выбираем съедаемые растения
                int toIndex = requiredPlantsCount >= plants.size() ? plants.size() : requiredPlantsCount;
                List<Plant> requiredPlants = plants.subList(0, toIndex);
                // съедаем растения
                for (Plant requiredPlant : requiredPlants) {
                    animal.eat(requiredPlant);
                    plantsToDelete.add(requiredPlant);
                }
                plants.removeAll(requiredPlants);
            }
        }
    }

    private void predatorEat(List<Animal> animalToDelete, List<Animal> animals, Animal feedingAnimal) {
        // хищники едят других животных
        if (animals.size() > 0) {
            AnimalConfig animalConfig = islandConfig.animalTypeToConfig.get(feedingAnimal.getType());
            for (Animal animal : animals) {
                // пытается съесть животное, если оно не было удалено ранее и подходит по типу
                if (!animalToDelete.contains(animal) && feedingAnimal != animal && feedingAnimal.canEat(animal)) {
                    // съедает животное, если выпал шанс больше, чем в конфигурации для этого типа
                    if (feedingAnimal.tryToEat(animal)) {
                        feedingAnimal.eat(animal);
                        animalToDelete.add(animal);
                    }
                    break;
                }
            }
        }
    }
}