package com.example.nxttrendz2.repository;

import java.util.ArrayList;

import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.model.Category;

public interface ProductRepository {
    public ArrayList<Product> getListOfProducts();

    public Product getProductByGivenId(int productId);

    public Product addProductByGivenProduct(Product newProductData);

    public Product modifyProductByGivenId(int productId, Product productData);

    public Product deleteProductByGivenId(int productId);

    public Category giveCategoryBasedOnProductId(int productId);
}