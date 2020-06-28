package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey.FirstEntity;
import com.example.hibernatedemo.entity.oneToOne.nonPrimaryKey.SecondEntity;
import com.example.hibernatedemo.repository.FirstEntityRepo;
import com.example.hibernatedemo.repository.SecondEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohd.waseem on 09/06/20.
 */
@RestController
@RequestMapping(value="oneToOne/nonPrimary")
public class OneToOneNonPrimaryController {

    @Autowired
    private FirstEntityRepo firstEntityRepo;

    @Autowired
    private SecondEntityRepo secondEntityRepo;

    @PostMapping(value = "post")
    public @ResponseBody
    String post(@RequestParam String code) {
        FirstEntity f= new FirstEntity();
        f.setCode(code);
        SecondEntity s=new SecondEntity();
        s.setValue(code+"_value");
        f.setSecondEntity(s);
        s.setFirstEntity(f);
        firstEntityRepo.save(f);
        return "post";
    }

    @GetMapping(value = "read")
    public @ResponseBody
    String read(@RequestParam String code) {
        FirstEntity f=firstEntityRepo.findByCodeCustomNative(code);
        System.out.println(f.getSecondEntity());
        return "success";
    }
}
