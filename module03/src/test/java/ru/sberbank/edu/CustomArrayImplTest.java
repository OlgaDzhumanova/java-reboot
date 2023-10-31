package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CustomArrayImplTest {
    private CustomArrayImpl<Integer> list;

    @BeforeEach
    public void initData() {
        list = new CustomArrayImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    void addToArray() {
        list.add(5);
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.indexOf(5)).isEqualTo(4);
    }

    @Test
    void addAllToArrayFromAnotherArray() {
        Integer[] items = {6, 7, 8, 9, 10};
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        assertThat(result.equals(list.addAll(items)));
    }

    @Test
    void addAllToArrayFromAnotherArrayByIndex() {
        Integer[] items = {6, 7, 8, 9, 10};
        List<Integer> result = List.of(1, 2, 6, 7, 8, 9, 10, 3, 4, 5);
        assertThat(result.equals(list.addAll(2, items)));
    }

    @Test
    void addAllToArrayFromCollection() {
        List<Integer> anotherList = List.of(1, 2, 3, 4, 5);
        List<Integer> result = List.of(1, 2, 6, 3, 4, 5, 1, 2, 6, 3, 4, 5);
        assertThat(result.equals(list.addAll(anotherList)));
    }

    @Test
    void addAllToArrayFromAnotherEmptyArray() {
        Integer[] items = {};
        assertThat(list.addAll(items)).isFalse();
    }

    @Test
    void addAllToArrayFromAnotherEmptyCollection() {
        List<Integer> items = List.of();
        assertThat(list.addAll(items)).isFalse();
    }

    @Test
    void checkIterator() {
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void whenAddThenSizeIncrease() {
        list.add(5);
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void whenAddAndGetByCorrectIndex() {
        list.add(4);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenSetNewValueByIndex() {
        int result = (int) list.set(0, 0);
        assertThat(list.get(0)).isEqualTo(0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void removeByIndex() {
        list.remove(2);
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(2)).isEqualTo(4);
    }

    @Test
    void removeByValue() {
        Integer num = 2;
        list.remove(num);
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(1)).isEqualTo(3);
    }

    @Test
    void whenIndexIsFound() {
        Integer index = list.indexOf(4);
        assertThat(index).isEqualTo(3);
    }

    @Test
    void whenIndexIsNotFound() {
        Integer index = list.indexOf(6);
        assertThat(index).isEqualTo(-1);
    }

    @Test
    void whenArrayIsReversed() {
        List<Integer> result = List.of(4, 3, 2, 1);
        list.reverse();
        assertThat(result.equals(list));
    }
}