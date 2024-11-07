package com.example.nxttrendz2.repository;

import java.util.ArrayList;

import com.example.nxttrendz2.model.Category;

public interface CategoryRepository {
    public ArrayList<Category> getListOfCategories();

    public Category addCategoryByGivenData(Category newCategoryData);

    public Category getCategoryBasedOnGivenId(int categoryId);

    public Category updateCategoryBasedOnGivenId(int categoryId, Category categoryData);

    public Category deleteCategoryByGivenId(int categoryId);
}