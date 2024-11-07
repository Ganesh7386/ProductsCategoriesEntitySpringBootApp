package com.example.nxttrendz2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.nxttrendz2.service.CategoryJpaService;
import com.example.nxttrendz2.model.Category;

@RestController
public class CategoryController {

    @Autowired
    private CategoryJpaService myCategoryJpaService;

    @GetMapping("/greet")
    public String greetUser() {
        return "Hello , The Java Developer";
    }

    @GetMapping("/categories")
    public ArrayList<Category> getListOfCategories() {
        ArrayList<Category> listOfCategories = myCategoryJpaService.getListOfCategories();
        return listOfCategories;
    }

    @PostMapping("/categories")
    public Category addCategoryByGivenData(@RequestBody Category newCategoryData) {
        Category newAddedcategoryData = myCategoryJpaService.addCategoryByGivenData(newCategoryData);
        if (newAddedcategoryData == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return newAddedcategoryData;
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategoryBasedOnGivenId(@PathVariable int categoryId) {
        Category existingCategory = myCategoryJpaService.getCategoryBasedOnGivenId(categoryId);
        if (existingCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return existingCategory;
    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategoryBasedOnGivenId(@PathVariable int categoryId, @RequestBody Category categoryData) {
        Category updatedCategory = myCategoryJpaService.updateCategoryBasedOnGivenId(categoryId, categoryData);
        if (updatedCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return updatedCategory;
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategoryByGivenId(@PathVariable int categoryId) {
        Category deletedCategory = myCategoryJpaService.deleteCategoryByGivenId(categoryId);
        if (deletedCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}