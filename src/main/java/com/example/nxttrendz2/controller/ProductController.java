package com.example.nxttrendz2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

import com.example.nxttrendz2.service.ProductJpaService;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;

@RestController
public class ProductController {

    @Autowired
    private ProductJpaService myProductJpaService;

    @GetMapping("/categories/products")
    public ArrayList<Product> getListOfProducts() {
        ArrayList<Product> listOfProducts = myProductJpaService.getListOfProducts();
        return listOfProducts;
    }

    @GetMapping("/categories/products/{productId}")
    public Product getProductByGivenId(@PathVariable int productId) {
        Product existingProduct = myProductJpaService.getProductByGivenId(productId);
        if (existingProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return existingProduct;
    }

    @PostMapping("/categories/products/")
    public Product addProductByGivenProduct(@RequestBody Product newProductData) {
        Product newAddedProduct = myProductJpaService.addProductByGivenProduct(newProductData);
        if (newAddedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return newAddedProduct;
    }

    @PutMapping("/categories/products/{productId}")
    public Product modifyProductByGivenId(@RequestBody Product productData, @PathVariable int productId) {
        Product modifiedProduct = myProductJpaService.modifyProductByGivenId(productId, productData);
        if (modifiedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return modifiedProduct;
    }

    @DeleteMapping("/categories/products/{productId}")
    public void deleteProductByGivenId(@PathVariable int productId) {
        Product deletedProduct = myProductJpaService.deleteProductByGivenId(productId);
        if (deletedProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/{productId}/category")
    public Category giveCategoryBasedOnProductId(@PathVariable int productId) {
        Category existingCategory= myProductJpaService.giveCategoryBasedOnProductId(productId);
        if(existingCategory == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return existingCategory;
    }
}