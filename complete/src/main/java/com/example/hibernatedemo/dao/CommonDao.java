package com.example.hibernatedemo.dao;

import com.example.hibernatedemo.entity.Consignment;
import com.example.hibernatedemo.entity.ConsignmentGraph;
import com.example.hibernatedemo.entity.Vas1;
import com.example.hibernatedemo.entity.Vas2;
import com.example.hibernatedemo.entity.named.query.DepartmentEntity;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public DepartmentEntity getDepartment(Integer id, String name) {
        Session session = (Session) entityManager.getDelegate();
        Filter filter = session.enableFilter("deptFilter");
        filter.setParameter("name", name);
        return (DepartmentEntity) session.getNamedQuery(DepartmentEntity.GET_DEPARTMENT_BY_ID)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Transactional
    public Consignment save(Consignment consignment) {
        entityManager.persist(consignment);
        return consignment;
    }

    @Transactional
    public Vas1 save(Vas1 vas1) {
        entityManager.persist(vas1);
        return vas1;
    }

    @Transactional
    public Vas2 save(Vas2 vas2) {
        entityManager.persist(vas2);
        return vas2;
    }

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

    public ConsignmentGraph getGraphFetchModeJoin(Long id) {
        Session session = (Session) entityManager.getDelegate();
        ConsignmentGraph consignmentGraph = session.createQuery("select cg From ConsignmentGraph cg Where cg.id = :id ", ConsignmentGraph.class)
                .setParameter("id", id).getSingleResult();
        return consignmentGraph;
    }

    public List<ConsignmentGraph> getGraphsFetchModeJoin(List<Long> ids) {
        Session session = (Session) entityManager.getDelegate();
        List<ConsignmentGraph> consignmentGraphs = session.createQuery("select cg From ConsignmentGraph cg Where cg.id IN :ids ", ConsignmentGraph.class)
                .setParameter("ids", ids).getResultList();
        return consignmentGraphs;
    }

    public List<ConsignmentGraph> getGraphsQueryFetch(List<Long> ids) {
        Session session = (Session) entityManager.getDelegate();
        List<ConsignmentGraph> consignmentGraphs = session.createQuery("select cg From ConsignmentGraph cg join fetch cg.vas1 join fetch cg.vas2 " +
                " Where cg.id IN :ids AND cg.vas1.isActive = :active1 AND cg.vas2.isActive = :active2 ", ConsignmentGraph.class)
                .setParameter("ids", ids)
                .setParameter("active1", true)
                .setParameter("active2", true).setMaxResults(5).getResultList();
        return consignmentGraphs;
    }

    @Transactional
    public ConsignmentGraph getGraph(Long id) {
        EntityGraph<ConsignmentGraph> graph = entityManager.createEntityGraph(ConsignmentGraph.class);
        graph.addAttributeNodes("vas1", "vas2");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        return entityManager.find(ConsignmentGraph.class, id, properties);
    }

    @Transactional
    public List<ConsignmentGraph> getMultipleGraph(List<Long> ids) {
        return entityManager.createQuery("SELECT CG FROM ConsignmentGraph CG WHERE CG.id IN :ids", ConsignmentGraph.class)
                .setParameter("ids", ids)
                .setHint("javax.persistence.fetchgraph", entityManager.createEntityGraph(ConsignmentGraph.class))
                .getResultList();
    }
}
