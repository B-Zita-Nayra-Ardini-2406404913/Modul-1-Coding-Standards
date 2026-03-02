package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarWriteRepository {
    Car create(Car product);
    Car update(Car product);
    Car delete(String productId);
}