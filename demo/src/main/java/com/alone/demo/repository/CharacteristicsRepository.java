package com.alone.demo.repository;

import com.alone.demo.entities.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CharacteristicsRepository extends JpaRepository<Characteristics, Long> {

    @Query("select c from Characteristics c where c.name = ?1")
    public Optional<Characteristics> findByName(String name);
}
