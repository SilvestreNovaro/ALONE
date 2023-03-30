package com.alone.demo.service;


import com.alone.demo.entities.Characteristics;
import com.alone.demo.repository.CharacteristicsRepository;
import com.alone.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CharacteristicsService {

    private final CharacteristicsRepository characteristicsRepository;


    public Characteristics add (Characteristics characteristics){
        return characteristicsRepository.save(characteristics);
    }

    public List<Characteristics> list(){
        return characteristicsRepository.findAll();
    }

    public void delete(Long id){
        characteristicsRepository.deleteById(id);
    }

    public Optional<Characteristics> findByName(String name){
        return characteristicsRepository.findByName(name);
    }

    public Optional <Characteristics> findById(Long id){
        return characteristicsRepository.findById(id);
    }

    public void updateCharacteristic(Characteristics characteristics, Long id){
        Optional<Characteristics> characteristicsOptional = characteristicsRepository.findById(id);
        if(characteristicsOptional.isPresent()){
            Characteristics existingCharacteristic = characteristicsOptional.get();
            if(characteristics.getName() !=null && !characteristics.getName().isEmpty()) existingCharacteristic.setName(characteristics.getName());
            if(characteristics.getIcon() !=null && !characteristics.getIcon().isEmpty()) existingCharacteristic.setIcon(characteristics.getIcon());
            characteristicsRepository.save(existingCharacteristic);
        }
    }
}
