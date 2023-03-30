package com.alone.demo.repository;

import com.alone.demo.entities.Categories;
import com.alone.demo.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository <Products, Long> {


 @Query("select p from Products p where p.name = ?1")
    public Optional<Products> findByName(String name);




}
