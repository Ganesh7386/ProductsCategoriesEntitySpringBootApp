package com.example.nxttrendz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.*;

import com.example.nxttrendz2.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_Id(Integer categoryId);

    @Query("SELECT p.name FROM Product p WHERE p.category.id = :categoryId")
    List<String> getProductNamesByCategoryId(@Param("categoryId") Integer categoryId);
}