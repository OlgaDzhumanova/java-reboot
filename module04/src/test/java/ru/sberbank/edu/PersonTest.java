package ru.sberbank.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PersonTest {
    private Set<Person> people;

    @BeforeEach
    public void initData() {
        this.people = new HashSet<>();
        people.add(new Person("Masha", "Moscow", 23));
        people.add(new Person("Olga", "Tashkent", 34));
        people.add(new Person("Sam", "London", 18));
        people.add(new Person("Alex", "New-York", 33));
        people.add(new Person("Ben", "New-York", 33));
    }

    @Test
    public void whenEqualsIsTrue() {
        Person person = new Person("Masha", "Moscow", 23);
        assertTrue(people.contains(person));
    }

    @Test
    public void whenEqualsIsFalse() {
        Person person = new Person("Maria", "Moscow", 23);
        assertFalse(people.contains(person));
    }

    @Test
    public void whenHashCodeEqual() {
        Person expect = new Person("Olga", "Tashkent", 34);
        Person result = new Person("OlGa", "TashkEnt", 34);
        assertEquals(expect.hashCode(), result.hashCode());
    }

    @Test
    public void whenHashCodeNotEqual() {
        Person expect = new Person("Alex", "New-York", 33);
        Person result = new Person("Alex", "NeW-York", 32);
        assertNotEquals(expect.hashCode(), result.hashCode());
    }
}