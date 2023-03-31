package com.alone.demo.repository;


import com.alone.demo.entities.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CitiesRepository extends JpaRepository <Cities, Long> {

    @Query("select c from Cities c where c.name = ?1")
    public Optional<Cities> findByName(String name);

    @Query("select c from Cities c where c.country = ?1")
    public Optional<Cities> findByCountry(String name);
}
