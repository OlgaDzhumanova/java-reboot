package ru.sberbank.edu;

import java.util.*;

public class CustomArrayImpl<T> implements CustomArray {

    private int size;
    private int modCount;
    private T[] contains;

    public final static int CONTAINER_INCREASE = 10;

    public CustomArrayImpl() {
        this.contains = (T[]) new Object[CONTAINER_INCREASE];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(Object item) {
        ensureCapacity(size * CONTAINER_INCREASE);
        contains[size++] = (T) item;
        modCount++;
        return true;
    }

    @Override
    public boolean addAll(Object[] items) {
        if (items.length == 0) {
            return false;
        }
        for (int i = 0; i < items.length; i++) {
            add(items[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection items) {
        if (items.size() == 0) {
            return false;
        }
        for (Object item : items) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        int checkIndex = Objects.checkIndex(index, size);
        modCount++;
        int itemsLength = items.length;
        if (itemsLength == 0 || checkIndex != index) {
            return false;
        }
        if (itemsLength > contains.length - size) {
            ensureCapacity(contains.length + itemsLength);
        }
        int numMoved = size - index;
        if (numMoved > 0) {
            System.arraycopy(contains, index, contains, index + itemsLength, numMoved);
        }
        System.arraycopy(items, 0, contains, index, itemsLength);
        size += itemsLength;
        return true;
    }

    @Override
    public Object get(int index) {
        int checkIndex = Objects.checkIndex(index, size);
        return checkIndex == index ? contains[index] : null;
    }

    @Override
    public Object set(int index, Object item) {
        T OldItem = null;
        int checkIndex = Objects.checkIndex(index, size);
        if (checkIndex == index) {
            OldItem = contains[index];
            contains[index] = (T) item;
        }
        return OldItem;
    }

    @Override
    public void remove(int index) {
        int checkIndex = Objects.checkIndex(index, size);
        if (checkIndex == index) {
            System.arraycopy(contains, index + 1, contains, index, size - index - 1);
            contains[size - 1] = null;
            size--;
            modCount--;
        }
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean contains(Object item) {
        int index = indexOf(item);
        return index == -1 ? false : true;
    }

    @Override
    public int indexOf(Object item) {
        int index = -1;
        if (item == null || size == 0) {
            return index;
        }
        for (int i = 0; i < size; i++) {
            if (contains[i].equals(item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (contains.length == size) {
            contains = Arrays.copyOf(contains, newElementsCount);
        }
    }

    @Override
    public int getCapacity() {
        return contains.length;
    }

    @Override
    public void reverse() {
        Object[] newContains = new Object[size];
        int index = 0;
        if (size > 1) {
            for (int i = size - 1; i >= 0; i--) {
                newContains[index++] = contains[i];
            }
        }
        contains = (T[]) Arrays.copyOf(newContains, size);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(contains, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(contains);
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int count = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return contains[count++];
            }

        };
    }
}