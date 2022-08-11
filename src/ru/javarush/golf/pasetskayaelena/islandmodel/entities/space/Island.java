package ru.javarush.golf.pasetskayaelena.islandmodel.entities.space;

import java.util.ArrayList;

public class Island {
    private final ArrayList<Location> locations;

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public Island(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public Location findLocation(int x, int y) {
        for (Location location : locations) {
            if (location.getX() == x && location.getY() == y) {
                return location;
            }
        }
        return null;
    }
}
