package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.multiplebagfetch.Auto;
import com.example.hibernatedemo.entity.multiplebagfetch.Person;
import com.example.hibernatedemo.entity.multiplebagfetch.Tool;
import com.example.hibernatedemo.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value = "post")
    public @ResponseBody String post(@RequestParam String name, @RequestParam Integer autoSize, @RequestParam Integer toolSize) {
        Person person = new Person();
        person.setName(name);
        setAuto(person, autoSize, toolSize);
        personRepository.save(person);
        return "post";
    }

    @GetMapping("proper")
    public String readProper() {
        List<Person> persons = personRepository.findAll();
        Session session = (Session) entityManager.getDelegate();
        List<Auto> autos = new ArrayList<>();
        for (Person person : persons) {
            if(!CollectionUtils.isEmpty(person.getAutos())) {
                autos.addAll(person.getAutos());
            }
        }
        try{
            autos = session.createQuery("select distinct a from Auto a Join fetch a.tools " +
                    "where a in :autos", Auto.class)
                    .setParameter("autos", autos)
                    .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                    .getResultList();
            for (Auto auto : autos) {
                for (Tool tool : auto.getTools()) {
                    System.out.println("person: "+auto.getPerson().getName()+" auto: "+auto.getName()+" tool: "+tool.getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "read";
    }

    @GetMapping(value = "read")
    public String read() {
        List<Person> persons = personRepository.findAll();
        System.out.println(persons);
        for (Person person : persons) {
            for (Auto auto : person.getAutos()) {
                for (Tool tool : auto.getTools()) {
                    System.out.println("person: "+person.getName()+" auto: "+auto.getName()+" tool: "+tool.getName());
                }
            }
        }
        return "read";
    }
    private void setAuto(Person person, int autoSize, int toolSize) {
        List<Auto> autos = new ArrayList<>();
        for( int i=0; i< autoSize; i++) {
            Auto auto = new Auto();
            auto.setName(person.getName()+"_"+i);
            auto.setPerson(person);
            setTools(auto, toolSize);
            autos.add(auto);
        }
        person.setAutos(autos);
    }
    private void setTools(Auto auto, int toolSize) {
        List<Tool> tools = new ArrayList<>();
        for( int i=0;i<toolSize; i++) {
            Tool tool = new Tool();
            tool.setName(auto.getName()+"_"+i);
            tool.setAuto(auto);
            tools.add(tool);
        }
        auto.setTools(tools);
    }
}
