package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;

public abstract class Animal extends Biota {
    private final AnimalConfig animalConfig;

    /**
     * Насыщение в %: 0-100. Когда = 0 - животное умирает.
     */
    private int satiety = 100;

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
        int requiredFoodForFullSatiety = (int) Math.round(animalConfig.foodSatiety * (100 - satiety) / 100);
        return requiredFoodForFullSatiety;
    }

    public Animal[] reproduce() {
        //TODO
        return null;
    }

    public DirectionType chooseMoveDirection(DirectionType[] availableDirections) {
        //TODO
        return null;
    }

    public int getSatiety() {
        return satiety;
    }

    public boolean decreaseSatiety() {
        if ((satiety - 10) < 0) {
            satiety = 0;
        } else {
            satiety -= 10;
        }
        return satiety > 0 ? true : false;
    }

    public void eat(Biota biota) {
        if (satiety >= 100) {
            throw new RuntimeException();
        }

        int eatingSatiety = (int) (100 * 1 * biota.getWeight() / animalConfig.foodSatiety);
        if ((satiety + eatingSatiety) < 100) {
            satiety = satiety + eatingSatiety;
        } else {
            satiety = 100;
        }
    }
}
