package com.alone.demo.controller;


import com.alone.demo.entities.Categories;
import com.alone.demo.service.CategoriesService;
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

@RequestMapping("/categories")
public class CategoryController {

    private final CategoriesService categoriesService;

   @GetMapping("/list")
    public List<Categories> list() {
       return categoriesService.list();
   }


   @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody Categories categories){
       String title = categories.getTitle();
       Optional<Categories> optionalCategories = categoriesService.findByTitle(title);
       if(optionalCategories.isPresent()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title " + title + " is already on our registers");
       }
       categoriesService.add(categories);
       return ResponseEntity.status(HttpStatus.CREATED).body("Categorie added succesfully!");


   }

   @PutMapping("/modify/{id}")
    public ResponseEntity<?> update(@RequestBody Categories categories, @PathVariable Long id){
       Optional<Categories> categoriesOptional = categoriesService.findById(id);
       if(categoriesOptional.isPresent()){
           categoriesService.updateCategory(categories, id);
           return ResponseEntity.status(HttpStatus.CREATED).body("The category " + categories.getTitle()+ " has been updated!");
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The category " + categories + " with id " + id + " does not exist");
   }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> delete (@PathVariable Long id){
       Optional<Categories> categoriesOptional = categoriesService.findById(id);
       if(categoriesOptional.isPresent()){
           categoriesService.delete(id);
           return ResponseEntity.status(HttpStatus.OK).body("Category with id " + id + "deleted");
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Theres no category with the id " + id);
   }

}
