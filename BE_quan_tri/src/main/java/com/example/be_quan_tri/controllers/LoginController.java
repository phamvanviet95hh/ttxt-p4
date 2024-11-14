package com.example.be_quan_tri.controllers;

import com.example.be_quan_tri.dtos.UserLoginRequest;
import com.example.be_quan_tri.dtos.UserLoginResponse;
import com.example.be_quan_tri.services.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return loginService.login(userLoginRequest);
    }
}