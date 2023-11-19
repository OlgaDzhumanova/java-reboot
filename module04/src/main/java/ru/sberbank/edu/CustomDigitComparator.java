package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator<Integer> implements Comparator<Integer> {
    /***
     * Сортирует коллекцию
     * Сначала выводит четные числа, потом нечетные
     * @param first the first object to be compared.
     * @param second the second object to be compared.
     * @return
     */
    @Override
    public int compare(Integer first, Integer second) {
        int firstRemainderOfDiv = (int)first % 2;
        int secondRemainderOfDiv = (int)second % 2;
        if (firstRemainderOfDiv == 0 && secondRemainderOfDiv != 0) {
            return -1;
        }
        if (firstRemainderOfDiv != 0 && secondRemainderOfDiv == 0) {
            return 1;
        }
        return 0;
    }
}
