package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.multiplebagfetch.Auto;
import com.example.hibernatedemo.entity.multiplebagfetch.Person;
import com.example.hibernatedemo.entity.multiplebagfetch.Tool;
import com.example.hibernatedemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "post")
    public @ResponseBody String post(@RequestParam String name) {
        Person person = new Person();
        person.setName(name);
        setAuto(person);
        personRepository.save(person);
        return "post";
    }
    @GetMapping(value = "read")
    public String read() {
        List<Person> persons = personRepository.findAll();
        System.out.println(persons);
        return "read";
    }
    private void setAuto(Person person) {
        List<Auto> autos = new ArrayList<>();
//        Set<Auto> autos = new HashSet<>();
        Auto auto1 = new Auto();
        auto1.setName(person.getName()+"_1");
        auto1.setPerson(person);
        setTools(auto1);
        Auto auto2 = new Auto();
        auto2.setName(person.getName()+"_2");
        auto2.setPerson(person);
        setTools(auto2);
        autos.add(auto1);
        autos.add(auto2);
        person.setAutos(autos);
    }
    private void setTools(Auto auto) {
        List<Tool> tools = new ArrayList<>();
//        Set<Tool> tools = new HashSet<>();
        Tool tool1 = new Tool();
        tool1.setName(auto.getName()+"_1");
        tool1.setAuto(auto);
        Tool tool2 = new Tool();
        tool2.setName(auto.getName()+"_2");
        tool2.setAuto(auto);
        tools.add(tool1);
        tools.add(tool2);
        auto.setTools(tools);
    }
}
