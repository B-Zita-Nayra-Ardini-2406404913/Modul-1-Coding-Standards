package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarReadController {
    private final CarQueryService carQueryService;

    public CarReadController(CarQueryService carQueryService) {
        this.carQueryService = carQueryService;
    }

    @GetMapping("/listCar")
    public String carListPage(Model model){
        List<Car> allCars = carQueryService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carQueryService.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }
}
