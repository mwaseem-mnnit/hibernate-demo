package com.example.hibernatedemo.repository;

import com.example.hibernatedemo.entity.manyToOne.nonPrimaryKey.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
}
