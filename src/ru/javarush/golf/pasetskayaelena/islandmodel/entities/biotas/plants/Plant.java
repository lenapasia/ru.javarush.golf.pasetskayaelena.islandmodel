package ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants;

import ru.javarush.golf.pasetskayaelena.islandmodel.configs.AnimalConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.configs.BiotaConfig;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;

public class Plant extends Biota {
    private final BiotaConfig config;
    public Plant(BiotaConfig config) {
        this.config = config;
    }
    @Override
    public double getWeight() {
        return config.weight;
    }
}
