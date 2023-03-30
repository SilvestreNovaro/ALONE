package com.alone.demo.controller;


import com.alone.demo.entities.Products;
import com.alone.demo.repository.ProductRepository;
import com.alone.demo.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestControllerAdvice
@RestController
@Validated
@CrossOrigin

@RequestMapping("/products")
public class ProductController {

    private final ProductsService productsService;


    @GetMapping("/lists")
    public List<Products> List(){
        return productsService.list();
    }


    @PostMapping("/add")
    public ResponseEntity <?> add (@RequestBody Products products){
        String myProduct = products.getName();
        Optional<Products> optionalProducts = productsService.findByName(myProduct);
        if(optionalProducts.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product " + myProduct + " is already on our registers");
        }
        productsService.add(products);
       return ResponseEntity.status(HttpStatus.CREATED).body("Product added succesfully!");
    }

    @GetMapping("/FindProductById/{id}")
    public ResponseEntity<?> findProductById (@PathVariable Long id){
        Optional<Products> optionalProducts = productsService.findById(id);
        if(optionalProducts.isPresent()){

            return ResponseEntity.status(HttpStatus.OK).body("The id given is for the product " + optionalProducts.get().getName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The id given is not associated with any product");
    }

}
