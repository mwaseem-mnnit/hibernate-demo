package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.service.TesstEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test/entity")
public class TestEntityController {

    @Autowired
    private TesstEntityService tesstEntityService;

    @RequestMapping(value = "/save", method =  RequestMethod.GET)
    @Transactional
    public String testSave() {
        return tesstEntityService.testWithoutTransaction();
    }

}
