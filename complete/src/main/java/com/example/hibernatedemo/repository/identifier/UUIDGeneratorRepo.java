package com.example.hibernatedemo.repository.identifier;

import com.example.hibernatedemo.entity.identifier.UUIDGeneratorDemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by mohd.waseem on 30/06/20.
 */
public interface UUIDGeneratorRepo extends JpaRepository<UUIDGeneratorDemo, UUID> {
    UUIDGeneratorDemo findByTestColumn(String testColumn);
}
