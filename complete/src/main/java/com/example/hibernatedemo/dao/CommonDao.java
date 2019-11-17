package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.Consignment;
import com.example.hibernatedemo.entity.ConsignmentGraph;
import com.example.hibernatedemo.entity.Vas1;
import com.example.hibernatedemo.entity.Vas2;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Vas1 getVas1(Long cnBookId) {
        Session session = (Session) entityManager.getDelegate();
        return session.byNaturalId(Vas1.class).using("cnBookId", cnBookId).using("isActive", true).load();
    }

    @Transactional
    public Vas2 getVas2(Long cnBookId) {
        Session session = (Session) entityManager.getDelegate();
        return session.byNaturalId(Vas2.class).using("cnBookId", cnBookId).using("isActive", true).load();
    }
    @Transactional
    public Consignment ConsignmentBook(String cnote) {
        Session session = (Session) entityManager.getDelegate();
        return session.byNaturalId(Consignment.class).using("cnote", cnote).using("isActive", true).load();
    }

    @Transactional
    public Vas1 getVas1(BigDecimal chargePerUnit) {
        Session session = (Session) entityManager.getDelegate();
        return (Vas1) session.createQuery("select v from Vas1 v where v.chargePerUnit = :chargePerUnit").setParameter("chargePerUnit", chargePerUnit
        ).uniqueResult();
    }

    @Transactional
    public Vas2 getVas2(BigDecimal chargePerUnit) {
        Session session = (Session) entityManager.getDelegate();
        return (Vas2) session.  createQuery("select v from Vas2 v where v.chargePerUnit = :chargePerUnit").setParameter("chargePerUnit", chargePerUnit
        ).uniqueResult();
    }

    @Transactional
    public Consignment ConsignmentBookCC(String cc) {
        Session session = (Session) entityManager.getDelegate();
        return (Consignment) session.createQuery("select c from Consignment where c.clientCode = :cc").setParameter("cc", cc).uniqueResult();
    }

    @Transactional
    public ConsignmentGraph getGraph(Long id) {
        EntityGraph<ConsignmentGraph> graph = entityManager.createEntityGraph(ConsignmentGraph.class);
        graph.addAttributeNodes("vas1", "vas2");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(ConsignmentGraph.class, id, properties);
    }
}
