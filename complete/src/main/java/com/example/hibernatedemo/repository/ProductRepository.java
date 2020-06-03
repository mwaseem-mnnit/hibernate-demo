package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @EntityGraph(value = "products")
    Optional<Product> findById(Integer id);

    @EntityGraph(attributePaths = {"gifts", "suggestion", "relative"})
    Product findByName(Integer id);

    @Query("Select p from Product p where p.name=:name ")
    Product doSomething(@Param("name") String name);

    @Modifying
    @Query("update Product p set p.category=:cat where p.id=:id")
    int updateCateById(@Param("cat") String cat, @Param("id") Integer id);
}
