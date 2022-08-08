package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import java.util.ArrayList;
import java.util.List;

public final class ListUtils {
    /* Общий метод для разделения списка на подсписки размером `n` каждый
         в Java с использованием `List.subList()` (конечный список может содержать меньше элементов)
         */
    public static <T> List<T>[] split(List<T> list, int n) {
        // получаем размер списка
        int size = list.size();

        // Вычислить общее количество разделов `m` размера `n` каждый
        int m = size / n;
        if (size % n != 0) {
            m++;
        }

        // создать `m` пустых списков и инициализировать их с помощью `List.subList()`
        List<T>[] partition = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            int fromIndex = i * n;
            int toIndex = (i * n + n < size) ? (i * n + n) : size;

            partition[i] = new ArrayList(list.subList(fromIndex, toIndex));
        }

        // возвращаем списки
        return partition;
    }
}
