package ru.javarush.golf.pasetskayaelena.islandmodel.configs;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

import java.util.Map;

public class IslandConfig {


    /**
     * Длительность такта симуляции (в секундах)
     */
    public long simulationCycleDuration;

    /**
     * ширина острова
     */
    public int width;

    /**
     * высота острова
     */
    public int height;

    /**
     * Конфигурация растения
     */
    public BiotaConfig plantConfig;

    /**
     * Конфигурация животных
     */
    public Map<AnimalType, AnimalConfig> animalTypeToConfig;

}
