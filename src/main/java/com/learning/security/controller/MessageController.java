package com.learning.security.controller;

import com.learning.security.util.DataUtil;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MessageController {

    @GetMapping("/customLoginPage")
    public String loginPage(){
        return "loginPage.html";
    }

    @GetMapping("/")
    public String successPage(Authentication authentication) {
        return "successPage.html";
    }

}
