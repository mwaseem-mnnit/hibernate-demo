package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityRepository extends CrudRepository<TestEntity, Integer> {

    TestEntity findByName(String name);
}
