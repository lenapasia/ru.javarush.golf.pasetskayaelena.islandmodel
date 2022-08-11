package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;

import java.util.Arrays;
import java.util.List;

public class PlantGenerationProcessor implements Runnable {

    private static final int plantsGenerationCount = 18;

    private final List<Location> locations;
    private final IslandConfig islandConfig;

    public PlantGenerationProcessor(List<Location> locations, IslandConfig islandConfig) {
        this.locations = locations;
        this.islandConfig = islandConfig;
    }

    @Override
    public void run() {
        List<String> t = locations.stream().map(location -> "[" + location.getX() + " " + location.getY() + "]").toList();
        String s = Arrays.toString(t.toArray(new String[]{}));
        //System.out.println("Процесс генерации растений для локаций " + s + " : " + LocalTime.now());
        Thread.currentThread().setName("P " + s);

        final int maxPlantsCount = islandConfig.plantConfig.maxQuantityAtLocation;

        for (Location location : locations) {
            int remainsToLimit = location.countPlants() + plantsGenerationCount - maxPlantsCount;
            if (remainsToLimit < 0) {
                for (int i = 0; i < plantsGenerationCount; i++) {
                    location.addBiota(new Plant(islandConfig.plantConfig));
                }
            } else {
                for (int i = 0; i < plantsGenerationCount - remainsToLimit; i++) {
                    location.addBiota(new Plant(islandConfig.plantConfig));
                }
            }
        }
    }
}

