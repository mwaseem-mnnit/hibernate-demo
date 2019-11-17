package com.example.hibernatedemo.service;

import com.example.hibernatedemo.dao.CommonDao;
import com.example.hibernatedemo.entity.Vas1;
import com.example.hibernatedemo.entity.Vas2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VasService {
    @Autowired
    private CommonDao commonDao;

    public void vas(Long cnBookId) {
        Vas1 vas1 = commonDao.getVas1(cnBookId);
        Vas2 vas2 = commonDao.getVas2(cnBookId);
        System.out.println("VS: "+vas1+" "+vas2);
    }

    public void vas2() {
        Vas1 vas1 = commonDao.getVas1(new BigDecimal("10.0"));
        Vas2 vas2 = commonDao.getVas2(new BigDecimal("10.0"));
        System.out.println("VS: "+vas1+" "+vas2);
    }
}
