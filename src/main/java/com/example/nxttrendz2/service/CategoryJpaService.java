package com.example.nxttrendz2.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.CategoryRepository;
import com.example.nxttrendz2.model.Category;

@Service
public class CategoryJpaService implements CategoryRepository {

    @Autowired
    private CategoryJpaRepository myCategoryJpaRepository;

    @Override
    public ArrayList<Category> getListOfCategories() {
        List<Category> categoryList = myCategoryJpaRepository.findAll();
        ArrayList<Category> listOfCategories = new ArrayList<>(categoryList);
        return listOfCategories;
    }

    @Override
    public Category addCategoryByGivenData(Category newCategoryData) {
        if (newCategoryData.getName() == null || newCategoryData.getDescription() == null) {
            return null;
        }
        Category newAddedCategory = myCategoryJpaRepository.save(newCategoryData);
        return newAddedCategory;
    }

    @Override
    public Category getCategoryBasedOnGivenId(int categoryId) {
        Category existingCategory = myCategoryJpaRepository.findById(categoryId).get();
        if (existingCategory == null) {
            return null;
        }
        return existingCategory;
    }

    @Override
    public Category updateCategoryBasedOnGivenId(int categoryId, Category categoryData) {
        Category existingCategory = myCategoryJpaRepository.findById(categoryId).get();
        if (existingCategory == null) {
            return null;
        }
        if (categoryData.getName() != null) {
            existingCategory.setName(categoryData.getName());
        }
        if (categoryData.getDescription() != null) {
            existingCategory.setDescription(categoryData.getDescription());
        }
        Category modifiedCategory = myCategoryJpaRepository.save(existingCategory);
        return modifiedCategory;
    }

    @Override
    public Category deleteCategoryByGivenId(int categoryId) {
        Category existingCategory = myCategoryJpaRepository.findById(categoryId).get();
        if (existingCategory == null) {
            return null;
        }
        myCategoryJpaRepository.deleteById(categoryId);
        return existingCategory;
    }

}