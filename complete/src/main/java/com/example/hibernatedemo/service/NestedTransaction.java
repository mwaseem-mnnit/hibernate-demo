package com.example.hibernatedemo.service;

import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohd.waseem on 30/01/20.
 */
@Service
public class NestedTransaction {
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String requiresNew(String name, String comment) {
        User user = createUser(name, comment);
        userRepository.save(user);
        return "SUCCESS";
    }



    public User createUser(String name, String comment) {
        User user = new User();
        user.setEmail(name);
        user.setName(name);
        Comment comment1 = new Comment();
        comment1.setComment(comment+"1");
        comment1.setUser(user);
        Comment comment2 = new Comment();
        comment2.setComment(comment + "2");
        comment2.setUser(user);
        List<Comment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        user.setComments(list);
        return user;
    }
}
