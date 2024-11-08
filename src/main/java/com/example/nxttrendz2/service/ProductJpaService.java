package com.example.nxttrendz2.service;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.nxttrendz2.repository.ProductJpaRepository;
import com.example.nxttrendz2.repository.ProductRepository;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    private ProductJpaRepository myProductJpaRepository;

    @Autowired
    private CategoryJpaService myCategoryJpaService;

    @Override
    public ArrayList<Product> getListOfProducts() {
        List<Product> productsList = myProductJpaRepository.findAll();
        ArrayList<Product> listOfProducts = new ArrayList<>(productsList);
        return listOfProducts;
    }

    @Override
    public Product getProductByGivenId(int productId) {
        Product existingProduct = myProductJpaRepository.findById(productId).get();
        if (existingProduct == null) {
            return null;
        }
        return existingProduct;
    }

    @Override
    public Product addProductByGivenProduct(Product newProductData) {
        Category categoryData = newProductData.getCategory();
        int categoryId = categoryData.getId();
        Category existingCategory = myCategoryJpaService.getCategoryBasedOnGivenId(categoryId);
        if (existingCategory == null) {
            return null;
        }
        newProductData.setCategory(existingCategory);
        Product newAddedProduct = myProductJpaRepository.save(newProductData);
        return newAddedProduct;
    }

    @Override
    public Product modifyProductByGivenId(int productId, Product productData) {
        Product existingProduct = myProductJpaRepository.findById(productId).get();
        if (existingProduct == null) {
            return null;
        }
        if (productData.getName() != null) {
            existingProduct.setName(productData.getName());
        }
        if (productData.getDescription() != null) {
            existingProduct.setDescription(productData.getDescription());
        }
        Double price = productData.getPrice();
        if (price != null) {
            existingProduct.setPrice(price);
        }
        if (productData.getCategory() != null) {
            Category categoryData = productData.getCategory();
            int categoryId = categoryData.getId();
            Category existingCategory = myCategoryJpaService.getCategoryBasedOnGivenId(categoryId);
            existingProduct.setCategory(existingCategory);
        }
        return existingProduct;
    }

    @Override
    public Product deleteProductByGivenId(int productId) {
        Product existingProduct = myProductJpaRepository.findById(productId).get();
        if (existingProduct == null) {
            return null;
        }
        myProductJpaRepository.deleteById(productId);
        return existingProduct;
    }

    @Override
    public Category giveCategoryBasedOnProductId(int productId) {
        Product existingProduct = myProductJpaRepository.findById(productId).get();
        if (existingProduct == null) {
            return null;
        }
        Category existingCategory = existingProduct.getCategory();
        return existingCategory;
    }

    @Override
    public ArrayList<Product> getListOfProductsByCategoryId(int categoryId) {
        List<Product> listOfProducts = myProductJpaRepository.findByCategory_Id(categoryId);
        ArrayList<Product> listOfProductsBelongToGivenCategoryId = new ArrayList<>(listOfProducts);
        return listOfProductsBelongToGivenCategoryId;
    }

    @Override
    public ArrayList<String> getProductNamesByGivenCategoryId(int categoryId) {
        List<String> listOfProductsFromDb = myProductJpaRepository.getProductNamesByCategoryId(categoryId);
        ArrayList<String> listOfProducts = new ArrayList<>(listOfProductsFromDb);
        return listOfProducts;
    }

}