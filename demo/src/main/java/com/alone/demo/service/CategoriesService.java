package com.alone.demo.service;


import com.alone.demo.entities.Categories;
import com.alone.demo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    public Categories add(Categories categories){
        return categoryRepository.save(categories);
    }


    public List <Categories> list(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Categories categories, Long id){
        Optional <Categories> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Categories existingCategory = optionalCategory.get();
            if(categories.getTitle() !=null && !categories.getTitle().isEmpty()) existingCategory.setTitle(categories.getTitle());
            if(categories.getDescription()!=null && !categories.getDescription().isEmpty()) existingCategory.setDescription(categories.getDescription());
            if(categories.getUrlImage()!=null && !categories.getUrlImage().isEmpty()) existingCategory.setUrlImage(categories.getUrlImage());
            categoryRepository.save(existingCategory);
        }
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public Optional<Categories> findByTitle (String title){
        return categoryRepository.findByTitle(title);
    }

    public Optional<Categories> findById(Long id) {
        return categoryRepository.findById(id);
    }



}
