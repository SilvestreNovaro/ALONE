package com.alone.demo.controller;

import com.alone.demo.entities.Cities;
import com.alone.demo.service.CitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestControllerAdvice
@RestController
@Validated
@CrossOrigin
@RequestMapping("/cities")
public class CityController {

    private final CitiesService citiesService;

    @GetMapping("list")
    public List<Cities> list() {
        return citiesService.list();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Cities cities) {
        Optional<Cities> optionalCities = citiesService.findByName(cities.getName());
        Optional<Cities> lookingSameCountry = citiesService.findByCountry(cities.getCountry());
        if (optionalCities.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The city " + cities.getName() + " already exists on our registers");
        }
        if (lookingSameCountry.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The country" + cities.getCountry() + " already exists on our registers");
        }
        citiesService.add(cities);
        return ResponseEntity.status(HttpStatus.CREATED).body("City added succesfully!");
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> updateCity(@RequestBody Cities cities, @PathVariable Long id) {
        Optional<Cities> optionalCities = citiesService.findById(id);
        if (optionalCities.isPresent()) {
            Optional<Cities> sameNameCity = citiesService.findByName(cities.getName());
            if (sameNameCity.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The city " + cities.getName() + " already exists on our registers");
        }
        Optional<Cities> citiesOptional = citiesService.findByCountry(cities.getCountry());
        if (citiesOptional.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The country " + cities.getCountry() + " already exists on our registers");
        citiesService.updateCity(cities, id);
        return ResponseEntity.status(HttpStatus.CREATED).body("City updated succesfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        Optional<Cities> optionalCities = citiesService.findById(id);
        if(optionalCities.isPresent()) {
            citiesService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("City with id " + id + " deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theres no city with the id " + id);
    }
}
