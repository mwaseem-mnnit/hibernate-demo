package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.TestEntityDao;
import com.example.hibernatedemo.entity.TestEntity;
import com.example.hibernatedemo.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TesstEntityService {

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Autowired
    private TestEntityDao testEntityDao;

    @Transactional
    public String testWithoutTransaction() {

        System.out.print("begin");
        TestEntity testEntity = new TestEntity();
        testEntity.setEmail("email3");
        testEntity.setName("name3");
        testEntity = testEntityRepository.save(testEntity);
        System.out.println(testEntity);
        testWithTransaction();
        System.out.print("dfewfws");
        return "success";
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public String testWithTransaction() {
        System.out.print("begin");
        TestEntity testEntity = new TestEntity();
        testEntity.setEmail("email4");
        testEntity.setName("name4");
        testEntity = testEntityRepository.save(testEntity);
        System.out.println(testEntity);
        Integer num = Integer.parseInt("cat");
        System.out.println(num);
        return "success";
    }

    @Transactional
    public String testScope(String name, Integer id) {
        testEntityDao.testPersistenceContextScope(id, name);
        return "SUCCESS";
    }
}
