package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.service.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/cn")
public class ConsignmentController {

    @Autowired
    private ConsignmentService consignmentService;

    @RequestMapping(value = "/save", method =  RequestMethod.GET)
    public String testSave(@RequestParam String cnote, @RequestParam String clientCode) {
        consignmentService.save(cnote, clientCode);
        return "success";
    }

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

    @RequestMapping(value = "/test/multiple/graph", method =  RequestMethod.POST)
    public String testGraph(@RequestBody List<Long> ids) {
        consignmentService.testMultipleGraph(ids);
        return "success";
    }

    @RequestMapping(value = "/test/graph/join", method =  RequestMethod.GET)
    public String testGraphJoin(@RequestParam Long id) {
        consignmentService.testFetchModeJoin(id);
        return "success";
    }

    @RequestMapping(value = "/test/graphs/join", method =  RequestMethod.POST)
    public String testGraphsJoin(@RequestBody List<Long> ids) {
        consignmentService.testListFetchModeJoin(ids);
        return "success";
    }

    @RequestMapping(value = "/test/graphs/query/fetch", method =  RequestMethod.POST)
    public String testGraphsQueryFetch(@RequestBody List<Long> ids) {
        consignmentService.testListQueryFetch(ids);
        return "success";
    }
}
