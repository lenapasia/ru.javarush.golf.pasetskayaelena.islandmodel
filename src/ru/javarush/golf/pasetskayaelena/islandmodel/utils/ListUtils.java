package ru.javarush.golf.pasetskayaelena.islandmodel.utils;

import java.util.ArrayList;
import java.util.List;

public final class ListUtils {
    public static <T> List<T>[] split(List<T> list, int n) {
        int size = list.size();
        int m = size / n;
        if (size % n != 0) {
            m++;
        }
        List<T>[] partition = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            int fromIndex = i * n;
            int toIndex = (i * n + n < size) ? (i * n + n) : size;

            partition[i] = new ArrayList(list.subList(fromIndex, toIndex));
        }
        return partition;
    }
}
