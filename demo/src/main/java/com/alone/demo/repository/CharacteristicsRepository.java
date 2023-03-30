package com.alone.demo.repository;

import com.alone.demo.entities.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicsRepository extends JpaRepository<Characteristics, Long> {
}
