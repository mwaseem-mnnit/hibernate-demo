package com.example.hibernatedemo.service;

import com.example.hibernatedemo.config.HibernateDemoTestConfig;
import com.example.hibernatedemo.exceptionhandler.DemoExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

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

    @Test
    public void testExceptiohHandler() {
        DemoExceptionHandler handler = new DemoExceptionHandler();
        WebRequest request = Mockito.mock(ServletWebRequest.class);
        Mockito.when(((ServletWebRequest) request).getRequest()).thenReturn(null);
        handler.handle(new DataIntegrityViolationException("test"), request);
    }
}
