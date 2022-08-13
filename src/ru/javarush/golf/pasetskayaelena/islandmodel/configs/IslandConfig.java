package ru.javarush.golf.pasetskayaelena.islandmodel.configs;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

import java.util.Map;

public class IslandConfig {

    public long simulationCycleDuration;

    public int width;

    public int height;

    public BiotaConfig plantConfig;

    public Map<AnimalType, AnimalConfig> animalTypeToConfig;

}