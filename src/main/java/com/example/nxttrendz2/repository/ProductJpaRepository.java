package com.example.nxttrendz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.*;

import com.example.nxttrendz2.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p where p.category.id = :categoryId")
    List<Product> getListOfProductsBycategoryId(@Param("categoryId") Integer categoryId);

    @Query("SELECT p.name FROM Product p WHERE p.category.id = :categoryId")
    List<String> getProductNamesByCategoryId(@Param("categoryId") Integer categoryId);

    @Query("SELECT COUNT(p) from Product p WHERE p.category.id = :categoryId")
    long getCountOfProductsByCategoryId(@Param("categoryId") int categoryId);
}