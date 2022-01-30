package application.controllers;

import application.exceptions.ExistsException;
import application.exceptions.NotFoundException;
import application.exceptions.WrongArgumentException;
import application.responses.FieldsFilter;
import application.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private FieldsFilter fieldsFilter;

    @GetMapping(value = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCarList(@RequestParam(value = "fields", required = false) Set<String> fields) {
        return ResponseEntity.ok(fieldsFilter.filter(carService.findAll(), fields));
    }

    @PostMapping("/api/cars")
    public ResponseEntity<String> addCar(@RequestParam(value = "licenseNumber") String licenseNumber,
                                         @RequestParam(value = "brand") String brand,
                                         @RequestParam(value = "model", required = false) String carModel,
                                         @RequestParam(value = "color", required = false) String color,
                                         @RequestParam(value = "year", required = false, defaultValue = "0") int year)
            throws ExistsException, WrongArgumentException {
        return new ResponseEntity<>(carService.addCar(licenseNumber, brand, carModel, color, year), HttpStatus.OK);
    }

    @PutMapping("/api/cars/{id}")
    public ResponseEntity<String> updateCar(@PathVariable long id,
                                            @RequestParam(value = "licenseNumber") String licenseNumber,
                                            @RequestParam(value = "brand") String brand,
                                            @RequestParam(value = "model", required = false) String carModel,
                                            @RequestParam(value = "color", required = false) String color,
                                            @RequestParam(value = "year", required = false, defaultValue = "0") int year)
            throws WrongArgumentException, NotFoundException {
        return new ResponseEntity<>(carService.updateCar(id, licenseNumber, brand, carModel, color, year),
                HttpStatus.OK);

    }

    @DeleteMapping("/api/cars/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) throws NotFoundException {
        return new ResponseEntity<>(carService.deleteCar(id), HttpStatus.OK);
    }
}
