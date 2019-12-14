package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.CommonDao;
import com.example.hibernatedemo.entity.Consignment;
import com.example.hibernatedemo.entity.ConsignmentGraph;
import com.example.hibernatedemo.entity.Vas1;
import com.example.hibernatedemo.entity.Vas2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ConsignmentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private VasService vasService;

    public void save(String cnote, String clientCode) {
        Consignment consignment = new Consignment();
        consignment.setCnote(cnote);
        consignment.setClientCode(clientCode);
        consignment.setActive(true);
        consignment = commonDao.save(consignment);

        Vas1 vas1 = new Vas1();
        vas1.setCnBookId(consignment.getId());
        vas1.setActive(true);
        vas1.setChargePerUnit(BigDecimal.ONE);

        Vas2 vas2 = new Vas2();
        vas2.setCnBookId(consignment.getId());
        vas2.setActive(true);
        vas2.setChargePerUnit(BigDecimal.TEN);
        commonDao.save(vas1);
        commonDao.save(vas2);
    }

    public void preProcessAndSave(String cnote) {
        Consignment consignment = commonDao.ConsignmentBook(cnote);
        Vas1 vas1 = commonDao.getVas1(consignment.getId());
        Vas2 vas2 = commonDao.getVas2(consignment.getId());
        System.out.println("CS: "+vas1+" "+vas2);
        doSomething(consignment.getId());
    }

    void doSomething(Long cnBookId) {
        vasService.vas(cnBookId);
    }

    void doSomethingElse() {
        vasService.vas2();
    }

    public void testGraph(Long id) {
        ConsignmentGraph graph = commonDao.getGraph(id);
        System.out.println("Graph: "+graph);
    }

    public void testMultipleGraph(List<Long> ids) {
        List<ConsignmentGraph> graphs = commonDao.getMultipleGraph(ids);
        System.out.println("Graph: "+graphs);
    }

    public void testFetchModeJoin(Long id) {
        ConsignmentGraph graph = commonDao.getGraphFetchModeJoin(id);
        System.out.println("Graph: "+graph);
    }

    public void testListFetchModeJoin(List<Long> ids) {
        List<ConsignmentGraph> graphs = commonDao.getGraphsFetchModeJoin(ids);
        System.out.println("Graph: "+graphs);
    }

    public void testListQueryFetch(List<Long> ids) {
        List<ConsignmentGraph> graphs = commonDao.getGraphsQueryFetch(ids);
        System.out.println("Graph: "+graphs);
    }
}
