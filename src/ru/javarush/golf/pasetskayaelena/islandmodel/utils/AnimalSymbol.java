package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import ru.javarush.golf.pasetskayaelena.islandmodel.biotas.animals.AnimalType;

public final class AnimalSymbol {
    public static String getByType(AnimalType animalType) {
        switch (animalType) {
            case Wolf:
                return "🐺";

            case Boa:
                return "🐍";

            case Fox:
                return "🦊";

            case Bear:
                return "🐻";

            case Eagle:
                return "🦅";

            case Horse:
                return "🐎";

            case Deer:
                return "🦌";

            case Rabbit:
                return "🐇";

            case Mouse:
                return "🐁";

            case Goat:
                return "🐐";

            case Sheep:
                return "🐑";

            case Boar:
                return "🐗";

            case Buffalo:
                return "🐃";

            case Duck:
                return "🦆";

            case Caterpillar:
                return "🐛";

            default:
                throw new RuntimeException();

        }
    }
}
