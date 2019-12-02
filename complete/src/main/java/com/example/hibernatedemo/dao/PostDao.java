package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.readOnly.Post;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> getById(List<Integer> ids) {
        Session session = (Session) entityManager.getDelegate();
        return session.createQuery("select p from Post p where id IN :ids", Post.class)
                .setParameter("ids", ids).getResultList();
    }

    public List<Post> getByIdReadOnly(List<Integer> ids) {
        Session session = (Session) entityManager.getDelegate();
        return session.createQuery("select p from Post p where id IN :ids", Post.class)
                .setHint(QueryHints.HINT_READONLY, true)
                .setParameter("ids", ids).getResultList();
    }

    @Transactional
    public List<Post> getByTitle(List<String> titles) {
        Session session = (Session) entityManager.getDelegate();
        return session.createQuery("select p from Post p where title IN :titles", Post.class)
                .setParameter("titles", titles).getResultList();
    }
}
