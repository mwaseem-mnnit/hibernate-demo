package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.Comment;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public Comment getById(Integer id) {
        Session session = (Session) entityManager.getDelegate();
        try {
            Query query = session.createQuery("select c from Comment c where c.id = :id ");
            query.setParameter("id",id);
            return (Comment)  query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }
    public Comment create( Comment comment) {
        Session session = (Session) entityManager.getDelegate();
        session.save(comment);
        return comment;
    }
}
