package ru.javarush.golf.pasetskayaelena.islandmodel.configs;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

import java.util.Map;

public class AnimalConfig extends BiotaConfig {

    public int moveSpeed;

    public double foodSatiety;

    public int maxBabiesQuantity;

    public Map<AnimalType, Integer> eatingProbability;



}
