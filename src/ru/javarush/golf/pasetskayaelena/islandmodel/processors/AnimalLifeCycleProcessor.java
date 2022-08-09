package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.herbivores.Herbivore;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AnimalLifeCycleProcessor implements Runnable {
    private final List<Location> locations;
    private final IslandConfig islandConfig;

    public AnimalLifeCycleProcessor(List<Location> locations, IslandConfig islandConfig) {
        this.locations = locations;
        this.islandConfig = islandConfig;
    }

    @Override
    public void run() {
        List<String> t = locations.stream().map(location -> "[" + location.getX() + " " + location.getY() + "]").toList();
        String s = Arrays.toString(t.toArray(new String[]{}));
        System.out.println("Процесс жизненного цикла животных для локаций " + s + " : " + LocalTime.now());
        Thread.currentThread().setName("A " + s);

        for (Location location : locations) {
            List<Animal> animalsToDelete = new ArrayList<>();
            List<Plant> plantsToDelete = new ArrayList<>();
            List<Plant> plants = location.getAllPlants();
            for (Biota eachBiota : location.getBiotas()) {
                if (eachBiota instanceof Animal) {
                    Animal eachAnimal = (Animal) eachBiota;

                    if (eachAnimal instanceof Herbivore) {
                        // Травоядные едят растения
                        int requiredFoodInKgForFullSatiety = eachAnimal.getRequiredFoodForFullSatiety();
                        int requiredPlantsCount = (int) (requiredFoodInKgForFullSatiety / islandConfig.plantConfig.weight);
                        int toIndex = requiredPlantsCount >= plants.size() ? plants.size() : requiredPlantsCount + 1;
                        List<Plant> requiredPlants = plants.subList(0, toIndex);
                        for (Plant requiredPlant : requiredPlants) {
                           eachAnimal.eat(requiredPlant);
                           plantsToDelete.add(requiredPlant);
                        }


                        //TODO добавить логику для некоторых травоядных есть не только растения
                    }

                    // Уменьшаем насыщение и убиваем животное, если насыщение = 0
                    boolean alive = eachAnimal.decreaseSatiety();
                    if (alive == false) {
                        animalsToDelete.add(eachAnimal);
                    }
                }
            }
            location.getBiotas().removeAll(animalsToDelete);
            location.getBiotas().removeAll(plantsToDelete);
        }
    }
}
