package ru.javarush.golf.pasetskayaelena.islandmodel.configs;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.animals.AnimalType;

import java.util.Map;

public class AnimalConfig extends BiotaConfig {

    /**
     * Скорость перемещения, не более чем, клеток за ход
     */
    public int moveSpeed;

    /**
     * Сколько килограммов пищи нужно животному для полного насыщения
     */
    public double foodSatiety;

    /**
     * Максимальное количество детенышей у каждого вида
     */
    public int maxBabiesQuantity;

    /**
     * C какой вероятностью (в процентах: 0% - 100%) животное съедает "пищу", если они находятся на одной клетке
     */
    public Map<AnimalType, Integer> eatingProbability;



}
