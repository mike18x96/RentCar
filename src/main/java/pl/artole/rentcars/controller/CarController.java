package pl.artole.rentcars.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.artole.rentcars.dto.CarDto;
import pl.artole.rentcars.service.CarService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/rentCar")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<CarDto> getAll() {
        log.info("getAll");
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") Long id) {
        log.info("getCarById: [{}]", id);
        var result = carService.findCarById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto newCar) {
        log.info("trying to create new Car: [{}]", newCar);
        var body = carService.createNewCar(newCar);

        return ResponseEntity.created(URI.create("/rentCar/cars/" + body.id())).body(body);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        log.info("trying to delete car by id: [{}]", id);
        boolean deleted = carService.deleteCarById(id);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PutMapping("/cars/{id}")
    public ResponseEntity<CarDto> replaceCar(@PathVariable("id") Long id, @RequestBody CarDto objectToReplace) {
        log.info("trying to replace Car: [{}]", objectToReplace);
        Optional<CarDto> carOptional = carService.findCarById(id);
        if (!carOptional.isPresent())
            return ResponseEntity.notFound().build();

        var newBodyCar = carService.updateCar(objectToReplace);

        return ResponseEntity.created(URI.create("/rentCar/cars/" + newBodyCar.id())).body(newBodyCar);
    }

    @PatchMapping("/cars/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable("id") Long id, @RequestBody CarDto objectToUpdate) {
        log.info("trying to update Car: [{}]", objectToUpdate);
        Optional<CarDto> carOptional = carService.findCarById(id);
        if (!carOptional.isPresent())
            return ResponseEntity.notFound().build();

        var newSpecCar = carService.updateCar(objectToUpdate);

        return ResponseEntity.created(URI.create("/rentCar/cars/" + newSpecCar.id())).body(newSpecCar);
    }

}
