package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.utils.Randomizer;

public abstract class Animal extends Biota {

    private static final int MIN_SATIETY = 0;
    private static final int MAX_SATIETY = 100;
    private static final int SATIETY_EXHAUSTION_STEP = 5;
    private static final int SATIETY_FOR_REPRODUCTION = 80;

    private final AnimalConfig animalConfig;

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

    /**
     * @return Сколько требуется килограмм пищи для полного насыщения
     */
    public int getRequiredFoodForFullSatiety() {
        return (int) Math.round(animalConfig.foodSatiety * (MAX_SATIETY - satiety) / 100);
    }

    public boolean isReadyToReproduce() {
        return satiety > SATIETY_FOR_REPRODUCTION;
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
        if ((satiety - SATIETY_EXHAUSTION_STEP) < MIN_SATIETY) {
            satiety = MIN_SATIETY;
        } else {
            satiety -= SATIETY_EXHAUSTION_STEP;
        }
        return satiety > MIN_SATIETY ? true : false;
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
}
