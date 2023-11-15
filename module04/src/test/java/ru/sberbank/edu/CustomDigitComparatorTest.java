package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomDigitComparatorTest {
    private List<Integer> list;

    @BeforeEach
    public void initData() {
        this.list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
    }

    @Test
    public void whenCollectionIsSorted() {
        List<Integer> expect = List.of(2, 4, 6, 8, 1, 3, 5, 7, 9);
        list.sort(new CustomDigitComparator<>());
        assertArrayEquals(list.toArray(), expect.toArray());
    }

    @Test
    public void whenDuplicatesInCollection() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        List<Integer> expect = List.of(2, 4, 6, 8, 2, 4, 6, 8, 1, 3, 5, 7, 9, 1, 3, 5, 7, 9);
        list.sort(new CustomDigitComparator<>());
        assertArrayEquals(list.toArray(), expect.toArray());
    }

}