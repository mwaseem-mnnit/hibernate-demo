package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.service.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/cn")
public class ConsignmentController {

    @Autowired
    private ConsignmentService consignmentService;

    @RequestMapping(value = "/test", method =  RequestMethod.GET)
    public String testCn(@RequestParam String cnote) {
        consignmentService.preProcessAndSave(cnote);
        return "success";
    }

    @RequestMapping(value = "/test/graph", method =  RequestMethod.GET)
    public String testGraph(@RequestParam Long id) {
        consignmentService.testGraph(id);
        return "success";
    }
}
