package ru.javarush.golf.pasetskayaelena.islandmodel.entities.space;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.MotionRequest;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.MotionRequestStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Location {
    private final int x;
    private final int y;
    private final ArrayList< Biota> biotas;

    private volatile Map<Animal, MotionRequest> motionRequests = new ConcurrentHashMap<>();

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

            return countAnimalsByTypeThatReadyForReproduction(animalType, 0);
        }

    public int countAnimalsByTypeThatReadyForReproduction(AnimalType animalType, int minReproductionSatiety) {
        synchronized (biotas) {
            int countAnimalsByType = 0;
            for (Biota biota : biotas) {
                if (biota instanceof Animal) {
                    Animal animal = (Animal) biota;
                    if (animal.getType() == animalType && animal.getSatiety() >= minReproductionSatiety) {
                        countAnimalsByType++;
                    }
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

    public List<Animal> getAllAnimals() {
        synchronized (biotas) {
            List<Animal> animals = new ArrayList<>();
            for (Biota biota : biotas) {
                if (biota instanceof Animal){
                    animals.add((Animal) biota);
                }
            }
            return animals;
        }
    }

    public Map<Animal, MotionRequest> getMotionRequests() {
        return motionRequests;
    }

    public void addMotionRequest(Animal animal, MotionRequest request) {
        motionRequests.put(animal, request);
    }

    public void deleteMotionRequest(Animal animal) {
        motionRequests.remove(animal);
    }

    public void setStatusOfMotionRequest(Animal animal, MotionRequest request) {
        motionRequests.replace(animal, request);
    }

    public boolean hasMotionRequestsForAnimal(Animal animal, MotionRequestStatus... status) {
        if (motionRequests.containsKey(animal)) {
            for (MotionRequestStatus motionRequestStatus : status) {
                if (motionRequests.get(animal).getStatus() == motionRequestStatus) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}