package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.dao.UserDao;
import com.example.hibernatedemo.dto.UserDTO;
import com.example.hibernatedemo.dto.UserProjection;
import com.example.hibernatedemo.service.CommonService;
import com.example.hibernatedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/v1/")
public class EmployeeController {

    @Autowired
    private UserService userService;

    @Autowired
    CommonService commonService;

    @Autowired
    private UserDao userDao;
    // Get Token Info
    @RequestMapping(value = "/read/tokenInfo", method =  RequestMethod.GET)
    public String getTokenInfo() {
        return "token";
    }


    // List of all employees.
    @GetMapping(path = "/read/list")
    public List<String> getAllEmployees() {
        return Arrays.asList("t1", "t2");
    }

    @GetMapping(path = "/test/entity/state")
    @Transactional
    public void testEntityState(@RequestParam String name) {
        commonService.testEntityState(name);
    }

    @GetMapping(path = "/test/dto/projection")
    @Transactional
    public List<UserDTO> testDTOProjection(@RequestBody List<Integer> ids) {
        return userService.getDTO(ids);
    }

    @GetMapping(path = "/test/dto/projection/interface")
    @Transactional
    public List<UserProjection> testDTOProjectionInterface(@RequestBody List<Integer> ids) {
        return userService.getDTOProjection(ids);
    }

    @GetMapping(path = "/test/entity/state/repo")
    public void testEntityStateRepo(@RequestParam String name, @RequestParam String email) {
        commonService.testEntityStateRepo(name,email);
    }
    @GetMapping(path = "/test/entity/state/repo2")
    public void testEntityStateRepo2(@RequestParam String name, @RequestParam String email) {
        commonService.testEntity(name, email);
    }

    @GetMapping(path = "/test/datasource")
    public void testDataSource(@RequestParam String name) {
        commonService.testDataSource(name);
    }
}
