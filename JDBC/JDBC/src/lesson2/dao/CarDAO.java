package lesson2.dao;

import lesson2.entity.Car;

import java.util.List;

public interface CarDAO {
    void add(Car car);
    List<Car> getAll();
    Car getById(long id);
    void updatePrice(long id, int price);
    void delete(String mark);
}
