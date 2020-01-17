package com.example.hibernatedemo.service;

import com.example.hibernatedemo.config.HibernateDemoTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HibernateDemoTestConfig.class})
public class TestService {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.getUserByName("name");
    }
}
