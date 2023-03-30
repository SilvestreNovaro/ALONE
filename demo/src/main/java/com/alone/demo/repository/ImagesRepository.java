package com.alone.demo.repository;


import com.alone.demo.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}
