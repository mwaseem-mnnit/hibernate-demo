package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/func/")
public class FunctionalController {

    @Autowired
    private CommonService commonService;

    @GetMapping(path = "/test/entity/state/repo")
    public User testEntityStateRepo(@RequestParam String name, @RequestParam String email) {
        return commonService.getUser().apply(name).apply(email);
    }
}
