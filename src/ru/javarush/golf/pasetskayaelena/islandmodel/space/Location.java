package ru.javarush.golf.pasetskayaelena.islandmodel.space;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.plants.Plant;

import java.util.ArrayList;

public class Location {
    private final int x;
    private final int y;
    private final ArrayList<Biota> biotas;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<Biota> getBiotas() {
        return biotas;
    }

    public Location(int x, int y, ArrayList<Biota> biotas) {
        this.x = x;
        this.y = y;
        this.biotas = biotas;
    }

    public void addBiota(Biota biota) {
        biotas.add(biota);
    }

    public int countPlants() {
        int countPlants = 0;
        for (Biota biota : biotas) {
            if (biota instanceof Plant) {
                countPlants++;
            }
        }
        return countPlants;
    }
}
