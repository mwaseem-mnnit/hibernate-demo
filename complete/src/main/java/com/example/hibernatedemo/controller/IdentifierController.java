package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.identifier.UUIDGeneratorDemo;
import com.example.hibernatedemo.entity.identifier.UUIDIdentifierDemo;
import com.example.hibernatedemo.repository.identifier.UUIDGeneratorRepo;
import com.example.hibernatedemo.repository.identifier.UUIDIdentifierRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by mohd.waseem on 30/06/20.
 */
@RestController
@RequestMapping("identifier")
public class IdentifierController {
    private final UUIDIdentifierRepo identifierRepo;
    private final UUIDGeneratorRepo generatorRepo;

    public IdentifierController(UUIDIdentifierRepo identifierRepo, UUIDGeneratorRepo generatorRepo) {
        this.identifierRepo = identifierRepo;
        this.generatorRepo = generatorRepo;
    }

    @PostMapping("uuid_identifier_demo")
    public ResponseEntity<String> createUUIDID(@RequestParam String testColumn) {
        identifierRepo.save(UUIDIdentifierDemo.builder()
                .id(UUID.randomUUID())
                .testColumn(testColumn)
                .build());
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("uuid_identifier_demo")
    public ResponseEntity<UUIDIdentifierDemo> getUUIDID(@RequestParam String testColumn) {
        UUIDIdentifierDemo demo = identifierRepo.findByTestColumn(testColumn);
        return ResponseEntity.ok().body(demo);
    }

    @PostMapping("uuid_generator_demo")
    public ResponseEntity<String> createUUIDGenerator(@RequestParam String testColumn) {
        generatorRepo.save(UUIDGeneratorDemo.builder()
                .testColumn(testColumn)
                .build());
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("uuid_generator_demo")
    public ResponseEntity<UUIDGeneratorDemo> getUUIDGenerator(@RequestParam String testColumn) {
        UUIDGeneratorDemo demo = generatorRepo.findByTestColumn(testColumn);
        return ResponseEntity.ok().body(demo);
    }
}
