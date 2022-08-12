package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import ru.javarush.golf.pasetskayaelena.islandmodel.entities.motion.DirectionType;
import ru.javarush.golf.pasetskayaelena.islandmodel.entities.space.Coordinates;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class DirectionUtils {
    public static DirectionType[] getAvailableDirections(int x, int y, int islandWidth, int islandHeight) {
        List<DirectionType> directions = Arrays.stream(DirectionType.values()).collect(Collectors.toList());

        if (x == 0) {
            directions.remove(DirectionType.Up);
        }

        if (x == islandHeight - 1) {
            directions.remove(DirectionType.Down);
        }

        if (y == islandWidth - 1) {
            directions.remove(DirectionType.Right);
        }

        if (y == 0) {
            directions.remove(DirectionType.Left);
        }

        return directions.toArray(new DirectionType[0]);
    }

    public static Coordinates calculateCoordinates(int x, int y, DirectionType direction, int speed, int islandWidth, int islandHeight) {
        int newX = x;
        int newY = y;
        if (direction == DirectionType.Up) {
            newX = x - speed;
            if (newX < 0) {
                newX = 0;
            }
        } else if (direction == DirectionType.Down) {
            newX = x + speed;
            if (newX >= islandHeight) {
                newX = islandHeight - 1;
            }
        } else if (direction == DirectionType.Right) {
            newY = y + speed;
            if (newY >= islandWidth) {
                newY = islandWidth - 1;
            }
        } else if (direction == DirectionType.Left) {
            newY = y - speed;
            if (newY < 0) {
                newY = 0;
            }
        }
        return new Coordinates(newX, newY);
    }
}
