package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

public abstract class Animal extends Biota {

    private static final int MIN_SATIETY = 0;
    private static final int MAX_SATIETY = 100;
    private static final int SATIETY_EXHAUSTION_STEP = 5;

    protected final AnimalConfig animalConfig;

    /**
     * Насыщение в %: 0-100. Когда = 0 - животное умирает.
     */
    private int satiety = MAX_SATIETY;

    public Animal(AnimalConfig animalConfig) {
        this.animalConfig = animalConfig;
    }

    @Override
    public double getWeight() {
        return animalConfig.weight;
    }

    public abstract AnimalType getType();

    public boolean hasOneOfTypes(AnimalType... type ) {
        for (AnimalType animalType : type) {
            if (this.getType() == animalType) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Сколько требуется килограмм пищи для полного насыщения
     */
    public int getRequiredFoodForFullSatiety() {
        return (int) Math.round(animalConfig.foodSatiety * (MAX_SATIETY - satiety) / 100);
    }

    /**
     * @return Выбор направления движения
     */
    public static DirectionType chooseMoveDirection(DirectionType[] availableDirections) {
        int randomIndex = (int) (Math.random() * availableDirections.length);
        return availableDirections[randomIndex];
    }

    public int chooseMoveSpeed() {
        return Randomizer.rnd(1, animalConfig.moveSpeed);
    }


    public int getSatiety() {
        return satiety;
    }

    public boolean decreaseSatiety() {
        return decreaseSatiety(SATIETY_EXHAUSTION_STEP);
    }

    public boolean decreaseSatiety(int value) {
        if ((satiety - value) < MIN_SATIETY) {
            satiety = MIN_SATIETY;
        } else {
            satiety -= value;
        }
        return satiety > MIN_SATIETY;
    }

    public void eat(Biota biota) {
        if (satiety >= MAX_SATIETY) {
            throw new RuntimeException("Нельзя съесть: насыщение = 100%");
        }

        int eatingSatiety = (int) (100 * biota.getWeight() / animalConfig.foodSatiety);
        if ((satiety + eatingSatiety) < MAX_SATIETY) {
            satiety = satiety + eatingSatiety;
        } else {
            satiety = MAX_SATIETY;
        }
    }

    public boolean canEat(Animal animal) {
        return animalConfig.eatingProbability.containsKey(animal.getType());
    }

    public boolean tryToEat(Animal animal) {
        int probability = animalConfig.eatingProbability.get(animal.getType());
        // съедает животное, если выпал шанс больше, чем в конфигурации для этого типа
        return Randomizer.rnd(0, 100) >= probability;
    }

    public boolean canEatOtherAnimals() {
        return animalConfig.eatingProbability != null && animalConfig.eatingProbability.size() > 0;
    }
}
