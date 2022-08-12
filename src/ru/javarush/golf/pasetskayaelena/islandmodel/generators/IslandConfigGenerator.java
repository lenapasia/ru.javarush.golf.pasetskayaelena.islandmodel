package ru.javarush.golf.pasetskayaelena.islandmodel.generators;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.BiotaConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.IslandConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

import java.util.HashMap;
import java.util.Map;

public class IslandConfigGenerator {
    public IslandConfig generate() {
        IslandConfig islandConfig = new IslandConfig();
        islandConfig.simulationCycleDuration = 1;
        islandConfig.width = 20; // 20
        islandConfig.height = 100; // 100
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
        animalConfig.foodSatiety = 8;
        animalConfig.moveSpeed = 3;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Horse, 10);
            put(AnimalType.Deer, 15);
            put(AnimalType.Rabbit, 60);
            put(AnimalType.Mouse, 80);
            put(AnimalType.Goat, 60);
            put(AnimalType.Sheep, 70);
            put(AnimalType.Boar, 15);
            put(AnimalType.Buffalo, 10);
            put(AnimalType.Duck, 40);
        }};

        return animalConfig;
    }

    private AnimalConfig generateBoa() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 15;
        animalConfig.maxQuantityAtLocation = 30;
        animalConfig.startQuantity = 40;
        animalConfig.maxBabiesQuantity = 30;
        animalConfig.foodSatiety = 3;
        animalConfig.moveSpeed = 1;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Fox, 15);
            put(AnimalType.Rabbit, 20);
            put(AnimalType.Mouse, 40);
            put(AnimalType.Duck, 10);
        }};

        return animalConfig;
    }

    private AnimalConfig generateFox() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 8;
        animalConfig.maxQuantityAtLocation = 30;
        animalConfig.maxBabiesQuantity = 7;
        animalConfig.startQuantity = 50;
        animalConfig.foodSatiety = 2;
        animalConfig.moveSpeed = 2;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Rabbit, 70);
            put(AnimalType.Mouse, 90);
            put(AnimalType.Duck, 60);
            put(AnimalType.Caterpillar, 40);
        }};

        return animalConfig;
    }

    private AnimalConfig generateBear() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 500;
        animalConfig.maxQuantityAtLocation = 5;
        animalConfig.startQuantity = 30;
        animalConfig.foodSatiety = 80;
        animalConfig.moveSpeed = 2;
        animalConfig.maxBabiesQuantity = 2;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Boa, 80);
            put(AnimalType.Horse, 40);
            put(AnimalType.Deer, 80);
            put(AnimalType.Rabbit, 80);
            put(AnimalType.Mouse, 90);
            put(AnimalType.Goat, 70);
            put(AnimalType.Sheep, 70);
            put(AnimalType.Boar, 50);
            put(AnimalType.Buffalo, 20);
            put(AnimalType.Duck, 10);
        }};

        return animalConfig;
    }

    private AnimalConfig generateEagle() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 6;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 40;
        animalConfig.foodSatiety = 1;
        animalConfig.moveSpeed = 3;
        animalConfig.maxBabiesQuantity = 2;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Fox, 10);
            put(AnimalType.Rabbit, 90);
            put(AnimalType.Mouse, 90);
            put(AnimalType.Duck, 80);
        }};

        return animalConfig;
    }

    private AnimalConfig generateHorse() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 400;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 120;
        animalConfig.foodSatiety = 60;
        animalConfig.moveSpeed = 5;
        animalConfig.maxBabiesQuantity = 1;

        return animalConfig;
    }

    private AnimalConfig generateDeer() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 300;
        animalConfig.maxQuantityAtLocation = 20;
        animalConfig.startQuantity = 20;
        animalConfig.foodSatiety = 50;
        animalConfig.moveSpeed = 4;
        animalConfig.maxBabiesQuantity = 1;

        return animalConfig;
    }

    private AnimalConfig generateRabbit() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 2;
        animalConfig.maxQuantityAtLocation = 150;
        animalConfig.startQuantity = 100;
        animalConfig.foodSatiety = 0.45;
        animalConfig.moveSpeed = 2;
        animalConfig.maxBabiesQuantity = 4;

        return animalConfig;
    }

    private AnimalConfig generateMouse() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 0.05;
        animalConfig.maxQuantityAtLocation = 500;
        animalConfig.startQuantity = 150;
        animalConfig.foodSatiety = 0.01;
        animalConfig.moveSpeed = 1;
        animalConfig.maxBabiesQuantity = 12;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Caterpillar, 90);
        }};

        return animalConfig;
    }

    private AnimalConfig generateGoat() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 60;
        animalConfig.maxQuantityAtLocation = 140;
        animalConfig.startQuantity = 40;
        animalConfig.foodSatiety = 10;
        animalConfig.moveSpeed = 3;
        animalConfig.maxBabiesQuantity = 3;

        return animalConfig;
    }

    private AnimalConfig generateSheep() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 70;
        animalConfig.maxQuantityAtLocation = 140;
        animalConfig.startQuantity = 40;
        animalConfig.foodSatiety = 15;
        animalConfig.moveSpeed = 3;
        animalConfig.maxBabiesQuantity = 2;

        return animalConfig;
    }

    private AnimalConfig generateBoar() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 400;
        animalConfig.maxQuantityAtLocation = 50;
        animalConfig.startQuantity = 30;
        animalConfig.foodSatiety = 50;
        animalConfig.moveSpeed = 2;
        animalConfig.maxBabiesQuantity = 9;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Mouse, 50);
            put(AnimalType.Caterpillar, 90);
        }};

        return animalConfig;
    }

    private AnimalConfig generateBuffalo() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 700;
        animalConfig.maxQuantityAtLocation = 10;
        animalConfig.startQuantity = 70;
        animalConfig.foodSatiety = 100;
        animalConfig.moveSpeed = 3;
        animalConfig.maxBabiesQuantity = 1;

        return animalConfig;
    }

    private AnimalConfig generateDuck() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 1;
        animalConfig.maxQuantityAtLocation = 200;
        animalConfig.startQuantity = 75;
        animalConfig.foodSatiety = 0.15;
        animalConfig.moveSpeed = 4;
        animalConfig.maxBabiesQuantity = 6;

        animalConfig.eatingProbability = new HashMap<>() {{
            put(AnimalType.Caterpillar, 90);
        }};

        return animalConfig;
    }

    private AnimalConfig generateCaterpillar() {
        AnimalConfig animalConfig = new AnimalConfig();
        animalConfig.weight = 0.01;
        animalConfig.maxQuantityAtLocation = 1000;
        animalConfig.startQuantity = 500;
        animalConfig.foodSatiety = 0;
        animalConfig.moveSpeed = 0;
        animalConfig.maxBabiesQuantity = 6;

        return animalConfig;
    }
}