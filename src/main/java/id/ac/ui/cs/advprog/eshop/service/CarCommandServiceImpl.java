package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.InMemoryCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCommandServiceImpl implements CarCommandService{
    @Autowired
    private InMemoryCarRepository inMemoryCarRepository;

    @Override
    public Car create(Car car) {
        inMemoryCarRepository.create(car);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        inMemoryCarRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        inMemoryCarRepository.delete(carId);
    }
}
