package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.manyToOne.nonPrimaryKey.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
