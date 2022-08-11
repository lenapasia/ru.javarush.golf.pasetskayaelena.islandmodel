package ru.javarush.golf.pasetskayaelena.islandmodel.entities.space;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final int x;
    private final int y;
    private final ArrayList<Biota> biotas;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location(int x, int y, ArrayList<Biota> biotas) {
        this.x = x;
        this.y = y;
        this.biotas = biotas;
    }

    public List<Biota> getBiotas() {
        synchronized (biotas) {
            return List.copyOf(biotas);
        }
    }

    public void addBiota(Biota biota) {
        synchronized (biotas) {
            biotas.add(biota);
        }
    }

    public void removeBiotas(List<? extends Biota> removingBiotas) {
        synchronized (biotas) {
            biotas.removeAll(removingBiotas);
        }
    }

    public void removeBiota(Biota biota) {
        synchronized (biotas) {
            biotas.remove(biota);
        }
    }

    public int countPlants() {
        synchronized (biotas) {
            int countPlants = 0;
            for (Biota biota : biotas) {
                if (biota instanceof Plant) {
                    countPlants++;
                }
            }
            return countPlants;
        }
    }

    public int countAnimalsByType(AnimalType animalType) {
        synchronized (biotas) {
            int countAnimalsByType = 0;
            for (Biota biota : biotas) {
                if ( !(biota instanceof Plant) && ((Animal)biota).getType() == animalType ) {
                    countAnimalsByType++;
                }
            }
            return countAnimalsByType;
        }
    }

    public int countAnimalsByTypeThatReadyForReproduction(AnimalType animalType) {
        synchronized (biotas) {
            int countAnimalsByType = 0;
            for (Biota biota : biotas) {
                if ( !(biota instanceof Plant) && ((Animal)biota).getType() == animalType && ((Animal)biota).isReadyToReproduce() ) {
                    countAnimalsByType++;
                }
            }
            return countAnimalsByType;
        }
    }

    public List<Plant> getAllPlants() {
        synchronized (biotas) {
            List<Plant> plants = new ArrayList<>();
            for (Biota biota : biotas) {
                if (biota instanceof Plant) {
                    plants.add((Plant) biota);
                }
            }
            return plants;
        }
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

