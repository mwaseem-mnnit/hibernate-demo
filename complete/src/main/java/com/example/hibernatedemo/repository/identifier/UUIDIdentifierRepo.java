package com.example.hibernatedemo.repository.identifier;

import com.example.hibernatedemo.entity.identifier.UUIDIdentifierDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by mohd.waseem on 30/06/20.
 */
public interface UUIDIdentifierRepo extends JpaRepository<UUIDIdentifierDemo, UUID> {
    UUIDIdentifierDemo findByTestColumn(String testColumn);
}
