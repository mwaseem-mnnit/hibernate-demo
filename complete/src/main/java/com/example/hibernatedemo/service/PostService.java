package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.PostDao;
import com.example.hibernatedemo.entity.readOnly.Post;
import org.hibernate.Session;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> getById(List<Integer> ids) {
        List<Post> posts = postDao.getById(ids);
        posts.forEach(System.out::println);
        System.out.println(entityManager.getFlushMode());
        printLoadedState(posts);
        return posts;
    }

    public List<Post> getByIdReadOnly(List<Integer> ids) {
        List<Post> posts = postDao.getByIdReadOnly(ids);
        posts.forEach(System.out::println);
        printLoadedState(posts);
        return posts;
    }

    private void printLoadedState(List<Post> posts) {
        org.hibernate.engine.spi.PersistenceContext persistenceContext = getHibernatePersistenceContext();
        for (Post post : posts) {
            EntityEntry entityEntry = persistenceContext.getEntry(post);
            System.out.println(entityEntry.getLoadedState());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<Post> getByTitles(List<String> titles) {
        List<Post> posts = postDao.getByTitle(titles);
        posts.forEach(System.out::println);
        System.out.println("flushMode: "+entityManager.getFlushMode());
        System.out.println("flushMode Hibernate: "+((Session)entityManager.getDelegate()).getHibernateFlushMode());
        Session session = (Session) entityManager.getDelegate();
        System.out.println("readOnly: "+session.isDefaultReadOnly());
        printLoadedState(posts);
        return posts;
    }

    private org.hibernate.engine.spi.PersistenceContext getHibernatePersistenceContext() {
        SharedSessionContractImplementor session = entityManager.unwrap(
                SharedSessionContractImplementor.class
        );
        return session.getPersistenceContext();
    }
}
