package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "get")
    public String getPost(@RequestBody List<Integer> ids) {
        postService.getById(ids);
        return "done";
    }

    @PostMapping(value = "get/read")
    public String getPostReadOnly(@RequestBody List<Integer> ids) {
        postService.getByIdReadOnly(ids);
        return "done";
    }

    @PostMapping(value = "get/title")
    public String getPostTitle(@RequestBody List<String> titles) {
        postService.getByTitles(titles);
        return "done";
    }
}
