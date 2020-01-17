package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.repository.TestEntityRepository;
import com.example.hibernatedemo.service.TesstEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value="/test/entity")
public class TestEntityController {

    @Autowired
    private TesstEntityService tesstEntityService;

    @Autowired
    private TestEntityRepository testEntityRepository;

    @RequestMapping(value = "/save", method =  RequestMethod.GET)
    @Transactional
    public String testSave() {
        return tesstEntityService.testWithoutTransaction();
    }

    @RequestMapping(value = "/session/scope", method =  RequestMethod.GET)
    public String scopeTest(@RequestParam String name,
                            @RequestParam Integer id) {
        testEntityRepository.findByName(null);
        return tesstEntityService.testScope(name, id);
    }

    @GetMapping(produces = "multipart/byteranges")
    public ResponseEntity<Object> getMultipartContent() {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("name", "Jhon").header("custom-header", "headerValue");

        builder.part("bitOfFileContent", Arrays.copyOf("abcdefghijklmnop".getBytes(), 11), MediaType.TEXT_PLAIN)
                .header("file", "file1.txt").header(HttpHeaders.RANGE, "0-10");

        return ResponseEntity.ok().body(builder.build());
    }
}
