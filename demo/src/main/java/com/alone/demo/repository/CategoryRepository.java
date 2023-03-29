package com.alone.demo.repository;

import com.alone.demo.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Categories, Long> {

    @Query("select c from Categories c where c.title = ?1")
    public Optional<Categories> findByTitle(String title);
}
