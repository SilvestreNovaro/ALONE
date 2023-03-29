package com.alone.demo.controller;


import com.alone.demo.entities.Products;
import com.alone.demo.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
@RestController
@Validated
@CrossOrigin

@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping("/list")
    public List<Products> list(){
        return productsService.list();
    }


}
