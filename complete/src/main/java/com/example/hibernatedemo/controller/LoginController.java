package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.entity.mapping.error.Login;
import com.example.hibernatedemo.entity.mapping.error.LoginInfo;
import com.example.hibernatedemo.repository.LoginInfoRepository;
import com.example.hibernatedemo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="login")
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginInfoRepository loginInfoRepository;

    @PostMapping(value = "post")
    public @ResponseBody String post(@RequestParam String name) {
        Login login = new Login();
        login.setName(name);
        setLoginInfo(login);
        loginRepository.save(login);
        return "post";
    }
    @GetMapping(value = "read")
    public String read(@RequestParam Integer id) {
        Login login = loginRepository.findById(id).get();
        System.out.println(login.getName()+" "+login.getLoginInfo().getName());
        return "read";
    }

    @GetMapping(value = "read/info")
    public String readInfo(@RequestParam Integer id) {
        LoginInfo loginInfo = loginInfoRepository.findById(id).get();
        System.out.println(loginInfo.getName()+" "+loginInfo.getLogin().getName());
        return "read";
    }
    private void setLoginInfo(Login login) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(login.getName()+"_1");
        loginInfo.setLogin(login);
        login.setLoginInfo(loginInfo);
    }
}
