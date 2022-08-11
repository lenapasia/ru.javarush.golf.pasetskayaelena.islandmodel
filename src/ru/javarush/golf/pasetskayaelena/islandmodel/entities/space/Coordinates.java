package ru.javarush.golf.pasetskayaelena.islandmodel.entities.space;

public final class Coordinates {
    private  final int x;
    private  final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
