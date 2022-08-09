package ru.javarush.golf.pasetskayaelena.islandmodel.entities.space;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.Biota;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.biotas.plants.Plant;

import java.util.ArrayList;
import java.util.List;

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

    public List<Plant> getAllPlants() {
        List<Plant> plants = new ArrayList<>();
        for (Biota biota : biotas) {
            if (biota instanceof Plant) {
                plants.add((Plant) biota);
            }
        }
        return plants;
    }
}

