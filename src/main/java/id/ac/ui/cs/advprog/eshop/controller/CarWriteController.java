package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.FuelCar;
import id.ac.ui.cs.advprog.eshop.service.CarCommandService;
import id.ac.ui.cs.advprog.eshop.service.CarQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarWriteController {
    private final CarCommandService carCommandService;

    public CarWriteController(CarCommandService carCommandService) {
        this.carCommandService = carCommandService;
    }

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        Car car = new FuelCar();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute FuelCar car, Model model){
        carCommandService.create(car);
        return "redirect:listCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute FuelCar car, Model model) {
        carCommandService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carCommandService.deleteCarById(carId);
        return "redirect:listCar";
    }
}
