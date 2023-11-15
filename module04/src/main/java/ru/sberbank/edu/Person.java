package ru.sberbank.edu;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparator<Person> {
    private String name;
    private String city;
    private int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /***
     * Сравнивает текущий объект с переданным
     * @param o   the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age && Objects.equals(name.toLowerCase(), person.name.toLowerCase())
                && Objects.equals(city.toLowerCase(), person.city.toLowerCase());
    }

    /***
     * Возвращает сумму hashcode параметров объекта
     * @return
     */
    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode() + city.toLowerCase().hashCode() + Objects.hashCode(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    /***
     * Производит сортировку сначала по полю city
     * потом по полю name
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Person o1, Person o2) {
        int cityCompare = o1.getCity().compareTo(o2.getCity());
        if (cityCompare != 0) {
            return cityCompare;
        }
        return o1.getName().compareTo(o2.getName());
    }
}