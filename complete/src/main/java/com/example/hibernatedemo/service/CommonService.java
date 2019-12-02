package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.UserDao;
import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;



@Service
public class CommonService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentService commentService;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public void commonMethod(String name, String comment, boolean flag) {
        User user = new User();
        user.setEmail(name);
        user.setName(name);
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setUser(user);
        Comment comment2 = new Comment();
        comment2.setComment(comment + "1");
        comment2.setUser(user);
        List<Comment> list = new ArrayList<>();
        list.add(comment1);
        list.add(comment2);
        user.setComments(list);
        userRepository.save(user);
    }

    public User get(String name) {
        User user = userDao.getByName(name);
        return user;
    }

    public void process(User user) {
        Comment comment = user.getComments().get(0);
        System.out.println(comment);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void testEntityState(String name) {
        User user = userDao.getByName(name);
        userService.testEntityState(name);
        commentService.getCommentById(user.getComments().get(0).getId());
        System.out.println("user: "+user);
    }

    @Transactional
    public void testEntityStateRepo(String name,String email) {
        User user = userRepository.findByName(name);
        userService.testEntityStateRepo(name, email);
        System.out.println("user: "+user);
    }

    public void testEntity(String name, String email) {
        User user = userRepository.findByName(name);
        user.setEmail(email);
        userRepository.save(user);
    }

    public void testDataSource(String name) {
        User user = userRepository.findByName(name);
        userService.testDataSource(name);
        System.out.println(user);
    }
}
