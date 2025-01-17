package ru.sberbank.edu.service.repository;

import ru.sberbank.edu.model.Car;

import java.util.Set;

public interface CarRepository extends Repository<Car, String> {
    Set<Car> findByModel(String model);
}
