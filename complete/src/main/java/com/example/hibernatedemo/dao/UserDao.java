package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class UserDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public User getByName(String name) {
        Session session = (Session) entityManager.getDelegate();
        try {
            Query query = session.createQuery("select u from User u where u.name = :name ");
            query.setParameter("name",name);
            return (User)  query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    public User create( User user) {
        Session session = (Session) entityManager.getDelegate();
        session.save(user);
        return user;
    }

    @Transactional
    public void testEntity(String name) {
        EntityManager e1 = entityManagerFactory.createEntityManager();
        e1.getTransaction().begin();
        try {
            Session session = (Session) entityManager.getDelegate();
            User user = session.get(User.class, 1);
            user.setEmail("email-updated");
            e1.flush();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
