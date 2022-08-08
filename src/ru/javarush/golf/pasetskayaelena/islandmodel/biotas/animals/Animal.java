package ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.Biota;

public abstract class Animal extends Biota {

    /**
     * Насыщение в %: 0-100. Когда = 0 - животное умирает.
     */
    private int satiety = 100;

    public abstract AnimalType getType();

    public void eat(Biota biota) {
        //TODO
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
}
