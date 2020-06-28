package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.UserDao;
import com.example.hibernatedemo.dto.UserDTO;
import com.example.hibernatedemo.dto.UserProjection;
import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DataSource dataSource;

//    @Transactional( propagation = Propagation.REQUIRED)
    public User getUserByName(String name) {
        return userDao.getByName(name);
    }

    public Integer getCount(Integer id) {
        return userRepository.getCount(id);
    }

    public User create( String name, boolean flag) {
        User user = null;
//        user.setEmail("");
        if( flag) {
            user = new User();
        }
        user.setName(name);
        user.setEmail(name+"@"+"abc.com");

//        return userRepository.save(user);
        return userDao.create(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testEntityState(String name) {
       User user = userDao.getByName(name);
//       user.setName("name-updated");
       List<Comment> comments = user.getComments();
       comments.get(0).setComment("updated");
       System.out.println("comments: "+user.getComments());
    }


    public List<UserDTO> getDTO(List<Integer> ids) {
        return userRepository.getUser(ids);
    }

    public List<UserProjection> getDTOProjection(List<Integer> ids) {
        return userRepository.getUserInterface(ids);
    }

    public void testEntityStateRepo(String name, String email) {
        User user = userRepository.findByName(name);
        user.setEmail("wmai-updated"+email);
        List<Comment> comments = user.getComments();
//        comments.get(0).setComment("updated");
        System.out.println("comments: "+user.getComments());
    }

    public void testDataSource(String name) {
        try{
            ResultSet rs = dataSource.getConnection().createStatement().executeQuery("select * from user where name = 'name'");
            System.out.println(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
