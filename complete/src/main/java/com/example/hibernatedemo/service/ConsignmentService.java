package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.CommonDao;
import com.example.hibernatedemo.entity.Consignment;
import com.example.hibernatedemo.entity.ConsignmentGraph;
import com.example.hibernatedemo.entity.Vas1;
import com.example.hibernatedemo.entity.Vas2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsignmentService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private VasService vasService;

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
        doSomething(graph.getId());
        doSomethingElse();
    }
}
