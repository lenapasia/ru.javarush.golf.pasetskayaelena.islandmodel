package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

public final class Randomizer {


    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
