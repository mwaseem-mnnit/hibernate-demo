package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @EntityGraph(value = "products")
    Optional<Product> findById(Integer id);

    @EntityGraph(attributePaths = {"gifts", "suggestion", "relative"})
    Product findByName(Integer id);
}
