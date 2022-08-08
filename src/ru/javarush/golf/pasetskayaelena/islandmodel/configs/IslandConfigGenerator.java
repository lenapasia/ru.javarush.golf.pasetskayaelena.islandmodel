package ru.javarush.golf.pasetskayaelena.islandmodel.configs;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class IslandConfigGenerator {
    public IslandConfig generate() {
        IslandConfig islandConfig = new IslandConfig();
        islandConfig.simulationCycleDuration = 1;
        islandConfig.width = 2; // 20
        islandConfig.height = 5;// 100
        islandConfig.plantConfig = generatePlant();
        islandConfig.animalTypeToConfig = generateAnimals();

        return islandConfig;
    }

    private BiotaConfig generatePlant() {
        BiotaConfig plantConfig = new BiotaConfig();
        plantConfig.weight = 1;
        plantConfig.maxQuantityAtLocation = 200;
        plantConfig.startQuantity = 500;

        return plantConfig;
    }

    private Map<AnimalType, AnimalConfig> generateAnimals() {
        Map<AnimalType, AnimalConfig> animalTypeToConfig = new HashMap<>();

        // predators
        animalTypeToConfig.put(AnimalType.Wolf, generateWolf());
        animalTypeToConfig.put(AnimalType.Boa, generateBoa());
        animalTypeToConfig.put(AnimalType.Fox, generateFox());
        animalTypeToConfig.put(AnimalType.Bear, generateBear());
        animalTypeToConfig.put(AnimalType.Eagle, generateEagle());

        // herbivores
        animalTypeToConfig.put(AnimalType.Horse, generateHorse());
        animalTypeToConfig.put(AnimalType.Deer, generateDeer());
        animalTypeToConfig.put(AnimalType.Rabbit, generateRabbit());
        animalTypeToConfig.put(AnimalType.Mouse, generateMouse());
        animalTypeToConfig.put(AnimalType.Goat, generateGoat());
        animalTypeToConfig.put(AnimalType.Sheep, generateSheep());
        animalTypeToConfig.put(AnimalType.Boar, generateBoar());
        animalTypeToConfig.put(AnimalType.Buffalo, generateBuffalo());
        animalTypeToConfig.put(AnimalType.Duck, generateDuck());
        animalTypeToConfig.put(AnimalType.Caterpillar, generateCaterpillar());

        return animalTypeToConfig;
    }

    private AnimalConfig generateWolf() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 50;
        animalConfig.maxQuantityAtLocation = 30;
        animalConfig.startQuantity = 70;
        animalConfig.maxBabiesQuantity = 5;

        return animalConfig;
    }

    private AnimalConfig generateBoa() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 15;
        animalConfig.maxQuantityAtLocation = 30;
        animalConfig.startQuantity = 40;
        animalConfig.maxBabiesQuantity = 30;

        return animalConfig;
    }

    private AnimalConfig generateFox() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 8;
        animalConfig.maxQuantityAtLocation = 30;
        animalConfig.startQuantity = 50;

        return animalConfig;
    }

    private AnimalConfig generateBear() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 500;
        animalConfig.maxQuantityAtLocation = 5;
        animalConfig.startQuantity = 30;

        return animalConfig;
    }

    private AnimalConfig generateEagle() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 6;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 30;

        return animalConfig;
    }

    private AnimalConfig generateHorse() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 400;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 60;

        return animalConfig;
    }

    private AnimalConfig generateDeer() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 300;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 20;

        return animalConfig;
    }

    private AnimalConfig generateRabbit() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 2;
        animalConfig.maxQuantityAtLocation = 150;
        animalConfig.startQuantity = 50;

        return animalConfig;
    }

    private AnimalConfig generateMouse() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 0.05;
        animalConfig.maxQuantityAtLocation = 500;
        animalConfig.startQuantity = 30;

        return animalConfig;
    }

    private AnimalConfig generateGoat() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 60;
        animalConfig.maxQuantityAtLocation = 140;
        animalConfig.startQuantity = 40;

        return animalConfig;
    }

    private AnimalConfig generateSheep() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 70;
        animalConfig.maxQuantityAtLocation = 140;
        animalConfig.startQuantity = 20;

        return animalConfig;
    }

    private AnimalConfig generateBoar() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 400;
        animalConfig.maxQuantityAtLocation = 50;
        animalConfig.startQuantity = 30;

        return animalConfig;
    }

    private AnimalConfig generateBuffalo() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 700;
        animalConfig.maxQuantityAtLocation = 10;
        animalConfig.startQuantity = 70;

        return animalConfig;
    }

    private AnimalConfig generateDuck() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 1;
        animalConfig.maxQuantityAtLocation = 200;
        animalConfig.startQuantity = 40;

        return animalConfig;
    }

    private AnimalConfig generateCaterpillar() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 0.01;
        animalConfig.maxQuantityAtLocation = 1000;
        animalConfig.startQuantity = 50;

        return animalConfig;
    }
}



