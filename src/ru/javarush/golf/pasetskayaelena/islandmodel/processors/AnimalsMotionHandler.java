package ru.javarush.golf.pasetskayaelena.islandmodel.processors;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.Animal;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.MotionRequest;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.MotionRequestStatus;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Coordinates;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Island;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Location;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.DirectionUtils;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalsMotionHandler {

    private final IslandConfig islandConfig;
    public final Island island;

    public AnimalsMotionHandler(IslandConfig islandConfig, Island island) {
        this.islandConfig = islandConfig;
        this.island = island;
    }

    public void processAnimalsMotion(Location location) {
        // stage 1
        initiateMotionRequests(location);

        // stage 2
        processOfMotionRequests(location);

        // stage 3
        completeMotionRequests(location);
    }

    private void initiateMotionRequests(Location location) {
        DirectionType[] availableDirections = DirectionUtils.getAvailableDirections(location.getX(), location.getY(),
                islandConfig.width, islandConfig.height);

        // создание запросов на исходящее движение из текущей локации
        for (Biota eachBiota : location.getBiotas()) {
            if (eachBiota instanceof Animal) {
                Animal eachAnimal = (Animal) eachBiota;
                createAnimalMotionRequest(location, availableDirections, eachAnimal);
            }
        }
    }

    private void createAnimalMotionRequest(Location location, DirectionType[] availableDirections, Animal eachAnimal) {
        // перемещение животных
        boolean needToMove = Randomizer.rnd(0, 1) == 1;
        if (needToMove &&
                !location.hasMotionRequestsForAnimal(eachAnimal, MotionRequestStatus.Outcoming, MotionRequestStatus.Approved)) {
            int randomMoveSpeed = eachAnimal.chooseMoveSpeed();
            DirectionType randomMoveDirection = Animal.chooseMoveDirection(availableDirections);
            Coordinates coordinates = DirectionUtils.calculateCoordinates(location.getX(), location.getY(),
                    randomMoveDirection, randomMoveSpeed, islandConfig.width, islandConfig.height);
            Location toLocation = island.findLocation(coordinates.getX(), coordinates.getY());
            synchronized (location.getMotionRequests()) {
                location.addMotionRequest(eachAnimal, new MotionRequest(toLocation, MotionRequestStatus.Outcoming));
            }
            synchronized (toLocation.getMotionRequests()) {
                toLocation.addMotionRequest(eachAnimal, new MotionRequest(location, MotionRequestStatus.Incoming));
            }
        }
    }

    private void processOfMotionRequests(Location location) {
        synchronized (location.getMotionRequests()) {
            Map<Animal, MotionRequest> removingRequests = new HashMap<>();
            for (Map.Entry<Animal, MotionRequest> eachEntry : location.getMotionRequests().entrySet()) {
                Animal eachAnimal = eachEntry.getKey();
                MotionRequest status = eachEntry.getValue();
                Location fromLocation = status.getLocation();
                int countAnimalsByType = location.countAnimalsByType(eachAnimal.getType());
                int freeSlots = islandConfig.animalTypeToConfig.get(eachAnimal.getType()).maxQuantityAtLocation - countAnimalsByType;
                if (status.getStatus() == MotionRequestStatus.Incoming) {
                    removingRequests.put(eachAnimal, status);
                    if (freeSlots > 0) {
                        location.addBiota(eachAnimal);
                        fromLocation.setStatusOfMotionRequest(eachAnimal, new MotionRequest(location, MotionRequestStatus.Approved));
                    } else {
                        fromLocation.setStatusOfMotionRequest(eachAnimal, new MotionRequest(location, MotionRequestStatus.Declined));
                    }
                }
            }
            for (Map.Entry<Animal, MotionRequest> request : removingRequests.entrySet()) {
                location.deleteMotionRequest(request.getKey());
            }
        }
    }

    private void completeMotionRequests(Location location) {
        Map<Animal, MotionRequest> removingRequests = new HashMap<>();
        synchronized (location.getMotionRequests()) {
            for (Map.Entry<Animal, MotionRequest> eachEntry : location.getMotionRequests().entrySet()) {
                Animal eachAnimal = eachEntry.getKey();
                MotionRequest status = eachEntry.getValue();
                if (status.getStatus() == MotionRequestStatus.Approved) {
                    location.removeBiota(eachAnimal);
                    removingRequests.put(eachAnimal, status);
                } else if (status.getStatus() == MotionRequestStatus.Declined) {
                    removingRequests.put(eachAnimal, status);
                }
            }
            for (Map.Entry<Animal, MotionRequest> request : removingRequests.entrySet()) {
                location.deleteMotionRequest(request.getKey());
            }
        }
    }
}
