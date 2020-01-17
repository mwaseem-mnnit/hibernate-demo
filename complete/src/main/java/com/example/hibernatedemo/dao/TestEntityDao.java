package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.TestEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TestEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void testPersistenceContextScope(Integer id, String name) {
        Session session = (Session) entityManager.getDelegate();
        TestEntity testEntity = session.createQuery("select t from TestEntity t Where id = :id", TestEntity.class)
                .setParameter("id", id).getSingleResult();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+ " "+session.hashCode());
            System.out.println(name+ " id: "+testEntity.getId()+" email: "+testEntity.getEmail());
        }
    }
}
