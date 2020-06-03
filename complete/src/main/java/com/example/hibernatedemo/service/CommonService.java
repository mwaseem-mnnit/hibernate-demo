package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.CommonDao;
import com.example.hibernatedemo.dao.UserDao;
import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.entity.named.query.DepartmentEntity;
import com.example.hibernatedemo.repository.UserRepository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Service
public class CommonService {

    @Autowired
    private CommonDao commonDao;

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

    @Autowired
    private NestedTransaction nestedTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String nestedTransaction(String name, String comment) {
        String response = nestedTransaction.requiresNew(name+"1", comment+"1");
        System.out.println(response);
        commonMethod(name, comment, true);
        return "SUCCESS";
    }

    public String getDepartment(Integer id, String name) {
        DepartmentEntity entity=commonDao.getDepartment(id, name);
        return "success";
    }

    @Transactional
    public int testVersionUpdateOnQuery(int id) {
        Session session = (Session) entityManager.getDelegate();
        Query<Integer> query = session.createQuery("Update User U Set U.name = :name, U.version=U.version+1 WHERE U.id=:id");
        query.setParameter("id", id);
        query.setParameter("name", "name-"+System.currentTimeMillis());
//        return query.setLockMode(LockModeType.WRITE).executeUpdate();
        return query.executeUpdate();
    }

    @Transactional
    public int testVersionOnEntityLoad(int id) {
        Session session = (Session) entityManager.getDelegate();
        User user = session.get(User.class, id);
        user.setEmail(user.getEmail()+id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

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
        if(name != null)
        throw new RuntimeException("exception");
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

    public Function<String, Function<String, User>> getUser() {
      return name -> email -> {
          User user = userRepository.findByName(name);
          userService.testEntityStateRepo(name , email);
          return user;
      };
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
