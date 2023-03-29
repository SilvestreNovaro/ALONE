package com.alone.demo.repository;

import com.alone.demo.entities.Categories;
import com.alone.demo.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Products, Long> {



}
