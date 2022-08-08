package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.space.Location;

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
            for (Biota eachBiota : location.getBiotas()) {
                if (eachBiota instanceof Animal) {
                    Animal animal = (Animal) eachBiota;
                    boolean alive = animal.decreaseSatiety();
                    if (alive == false) {
                        animalsToDelete.add(animal);
                    }
                }
            }
            location.getBiotas().removeAll(animalsToDelete);
        }
    }
}
