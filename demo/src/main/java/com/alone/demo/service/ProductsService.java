package com.alone.demo.service;


import com.alone.demo.entities.Products;
import com.alone.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductRepository productRepository;


    public Products add(Products products) {
        return productRepository.save(products);
    }

    public List<Products> list(){
        return productRepository.findAll();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }


    public void updateProduct(Products products, Long id){
        Optional<Products> productsOptional = productRepository.findById(id);
        if(productsOptional.isPresent()){
            Products existingProduct = productsOptional.get();
            if(products.getName() !=null && !products.getName().isEmpty()) existingProduct.setName(products.getName());
            if(products.getDescription() !=null && !products.getDescription().isEmpty()) existingProduct.setDescription(products.getDescription());
        }
    }



}
