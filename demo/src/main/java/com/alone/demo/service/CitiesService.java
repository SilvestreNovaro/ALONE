package com.alone.demo.service;

import com.alone.demo.entities.Cities;
import com.alone.demo.repository.CitiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CitiesService {
    private final CitiesRepository citiesRepository;

    public Cities add (Cities cities){
        return citiesRepository.save(cities);
    }

    public List<Cities> list (){
        return citiesRepository.findAll();
    }

    public void updateCity (Cities cities, Long id){
        Optional<Cities> citiesOptional = citiesRepository.findById(id);
        if(citiesOptional.isPresent()){
            Cities existingCity = citiesOptional.get();
            if(cities.getName()!=null && !cities.getName().isEmpty()) existingCity.setName(cities.getName());
            if(cities.getCountry()!=null && !cities.getCountry().isEmpty()) existingCity.setCountry(cities.getCountry());
            citiesRepository.save(existingCity);
        }
    }

    public void delete(Long id){
        citiesRepository.deleteById(id);
    }

    public Optional <Cities> findByName(String name){
        return citiesRepository.findByName(name);
    }

    public Optional<Cities> findById(Long id){
        return citiesRepository.findById(id);
    }

    public Optional<Cities> findByCountry(String country){
        return citiesRepository.findByCountry(country);
    }
}
