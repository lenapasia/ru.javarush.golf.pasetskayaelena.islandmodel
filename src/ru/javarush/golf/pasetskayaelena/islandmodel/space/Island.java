package ru.javarush.golf.pasetskayaelena.islandmodel.space;

import java.util.ArrayList;

public class Island {
    private final ArrayList<Location> locations;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public Island(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
