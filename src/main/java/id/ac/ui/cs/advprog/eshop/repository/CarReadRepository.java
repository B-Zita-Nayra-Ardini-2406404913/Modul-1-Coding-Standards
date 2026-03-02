package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;

public interface CarReadRepository {
    Car findById(String productId);
    Iterator<Car> findAll();
}