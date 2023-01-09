package com.example.SpringTrySample.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("login") //configのloginPageで指定したurlを準備
    public String login(){
        return "login";
    }

    @GetMapping("loginsuccess") //configのdefaultsuccessUrlで指定したurlを準備
    public String hello(Authentication loginUser, Model model){

        /**
         * AuthenticationでログインUserの情報を扱うことができる
         */
        model.addAttribute("username", loginUser.getName());

        return "loginsuccess";
    }
}
