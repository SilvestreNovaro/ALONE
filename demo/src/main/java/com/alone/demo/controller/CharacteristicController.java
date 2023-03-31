package com.alone.demo.controller;


import com.alone.demo.entities.Characteristics;
import com.alone.demo.service.CharacteristicsService;
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

@RequestMapping("/characteristics")
public class CharacteristicController {

    private final CharacteristicsService characteristicsService;

    @GetMapping("/list")
    public List<Characteristics> list (){
        return characteristicsService.list();
    }


    @PostMapping("/add")
    public ResponseEntity <?> add (@RequestBody Characteristics characteristics){
      //  String name = characteristics.getName();
        Optional<Characteristics> optionalCharacteristics = characteristicsService.findByName(characteristics.getName());
        if(optionalCharacteristics.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Characteristic with the name  " + characteristics.getName() + " is already on our registers");
        }
        characteristicsService.add(characteristics);
        return ResponseEntity.status(HttpStatus.CREATED).body("Characteristic added succesfully!");

    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<?> update (@RequestBody Characteristics characteristics, @PathVariable Long id){
        Optional <Characteristics> sameNameCharacteristic = characteristicsService.findByName(characteristics.getName());
        Optional<Characteristics> characteristicsOptional = characteristicsService.findById(id);

        if(sameNameCharacteristic.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Characteristic with the name  " + characteristics.getName() + " is already on our registers");
        }
        if(characteristicsOptional.isPresent()){
           // if(sameNameCharacteristic.isEmpty())
            characteristicsService.updateCharacteristic(characteristics,id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Characteristic updated succesfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Characteristic with the id  " + id + " does not exist on our registers");
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional <Characteristics> optionalCharacteristics = characteristicsService.findById(id);
        if(optionalCharacteristics.isPresent()){
            characteristicsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Characteristic with id " + id + " deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theres no characteristic with the id " + id);
    }
}
